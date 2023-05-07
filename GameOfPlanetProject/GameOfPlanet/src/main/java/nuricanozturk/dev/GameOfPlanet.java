package nuricanozturk.dev;

import nuricanozturk.dev.entity.BlackHole;
import nuricanozturk.dev.entity.InitGameContext;
import nuricanozturk.dev.generator.name.NameType;
import project.gameengine.base.Action;
import project.gameengine.base.Game;
import project.gameengine.base.GameContext;
import project.gameengine.base.Player;

import java.util.List;

import static nuricanozturk.dev.factory.SpaceshipFactory.createSpaceships;
import static nuricanozturk.dev.generator.name.NameGeneratorFactory.createName;
import static nuricanozturk.dev.util.Constants.MAX_PLAYER;
import static nuricanozturk.dev.util.Constants.MIN_PLAYER;

public class GameOfPlanet implements Game
{
    private final int m_turnCount;
    private GameContext m_gameContext;
    private int m_currentTurn;
    private boolean isOver; // default false

    public GameOfPlanet(int turnCount)
    {
        m_turnCount = turnCount;
        m_currentTurn = 0;
    }

    @Override
    public boolean isOver()
    {
        return isOver;
    }

    @Override
    public void init(List<Player> players)
    {
        var blackhole = new BlackHole(createName(NameType.BlackHole, 1));
        var galaxy = blackhole.explode();

        m_gameContext = new InitGameContext(createSpaceships(), galaxy, galaxy.getPlanets());

        // Each planet create own market and each market creates own commodities
        ((InitGameContext) m_gameContext).init(players);
        System.exit(1);
    }

    @Override
    public GameContext getContext()
    {
        return m_gameContext;
    }

    @Override
    public void update(Action action)
    {
        if (m_currentTurn == m_turnCount - 1)
            isOver = true;

        if (!isOver())
        {
            m_currentTurn++;
            updateMarkets();
            //.....
        }
    }

    private void updateMarkets()
    {
        ((InitGameContext) getContext()).updateItems();
    }

    @Override
    public int minimumPlayerCount()
    {
        return MIN_PLAYER;
    }

    @Override
    public int maximumPlayerCount()
    {
        return MAX_PLAYER;
    }
}
