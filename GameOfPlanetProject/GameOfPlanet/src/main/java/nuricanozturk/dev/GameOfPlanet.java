package nuricanozturk.dev;

import nuricanozturk.dev.entity.BlackHole;
import nuricanozturk.dev.factory.CommodityFactory;
import nuricanozturk.dev.factory.SpaceshipFactory;
import project.gameengine.base.Action;
import project.gameengine.base.Game;
import project.gameengine.base.GameContext;
import project.gameengine.base.Player;

import java.util.List;

import static nuricanozturk.dev.util.Constants.MAX_PLAYER;
import static nuricanozturk.dev.util.Constants.MIN_PLAYER;

public class GameOfPlanet implements Game
{
    @Override
    public boolean isOver()
    {
        return false;
    }

    @Override
    public void init(List<Player> players)
    {
        var blackhole = new BlackHole("BlackHole");
        blackhole.explode();
        /*var galaxy = blackhole.explode();
        var commodities = CommodityFactory.createCommodities();
        var spaceships = SpaceshipFactory.createSpaceships();
        players.forEach(p -> p.prepareForGame(galaxy));*/
    }

    @Override
    public GameContext getContext()
    {
        return null;
    }

    @Override
    public void update(Action action)
    {

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
