package nuricanozturk.dev;

import nuricanozturk.dev.entity.BlackHole;
import nuricanozturk.dev.entity.Galaxy;
import nuricanozturk.dev.entity.PlanetTradeGameContext;
import project.gameengine.base.Action;
import project.gameengine.base.Game;
import project.gameengine.base.GameContext;
import project.gameengine.base.Player;

import java.util.List;

import static nuricanozturk.dev.factory.SpaceshipFactory.createSpaceships;
import static nuricanozturk.dev.util.Constants.*;


public class PlanetTradeGame implements Game
{
    public static int PLAYER_COUNT;
    private final int m_turnCount;
    private PlanetTradeGameContext m_gameContext;
    private List<Player> m_players;
    private int m_currentTurn;
    private boolean isOver;


    public PlanetTradeGame(int turnCount)
    {
        m_turnCount = turnCount;
        m_currentTurn = 1;
    }

    @Override
    public boolean isOver()
    {
        return isOver;
    }

    @Override
    public void init(List<Player> players)
    {
        var blackhole = new BlackHole(BLACKHOLE_NAMES.next());
        var galaxy = blackhole.explode();

        m_players = players;
        PLAYER_COUNT = m_players.size();
        m_gameContext = new PlanetTradeGameContext(createSpaceships(), (Galaxy) galaxy, ((Galaxy) galaxy).getPlanets(),
                m_players);

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
        if (m_currentTurn == m_turnCount * m_players.size())
            finishGame();

        m_gameContext.updateGame();

        m_currentTurn++;
    }

    private void finishGame()
    {
        isOver = true;
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
        return m_gameContext.toString();
    }
}