package nuricanozturk.dev;

import jdk.jshell.Snippet;
import nuricanozturk.dev.action.BuyFuel;
import nuricanozturk.dev.action.BuyItem;
import nuricanozturk.dev.action.PlanTravelling;
import nuricanozturk.dev.action.SoldItem;
import nuricanozturk.dev.entity.BlackHole;
import nuricanozturk.dev.entity.InitGameContext;
import nuricanozturk.dev.generator.name.NameType;
import project.gameengine.base.Action;
import project.gameengine.base.Game;
import project.gameengine.base.GameContext;
import project.gameengine.base.Player;

import java.util.Arrays;
import java.util.List;

import static nuricanozturk.dev.factory.SpaceshipFactory.createSpaceships;
import static nuricanozturk.dev.generator.name.NameGeneratorFactory.createName;
import static nuricanozturk.dev.util.Constants.MAX_PLAYER;
import static nuricanozturk.dev.util.Constants.MIN_PLAYER;

public class GameOfPlanet implements Game
{
    private final int m_turnCount;
    private InitGameContext m_gameContext;
    private List<Player> m_players;
    private int m_currentTurn;
    private boolean isOver = false; // default false

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
        m_players = players;
        m_gameContext = new InitGameContext(createSpaceships(), galaxy, galaxy.getPlanets());

        // Each planet create own market and each market creates own commodities
        m_gameContext.init(players);
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

        m_players.forEach(p -> p.play(m_gameContext));

        m_currentTurn++;

        if (isOver)
            finishGame();
    }

    private void finishGame()
    {
        System.out.printf("%nGame is finish!");
        System.exit(0);
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

    @Override
    public String toString()
    {
        return "GAME";
    }
}