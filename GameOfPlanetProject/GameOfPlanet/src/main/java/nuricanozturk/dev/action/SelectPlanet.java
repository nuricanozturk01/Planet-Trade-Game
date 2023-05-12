package nuricanozturk.dev.action;

import nuricanozturk.dev.config.RandomConfig;
import nuricanozturk.dev.entity.InitGameContext;
import nuricanozturk.dev.entity.PlayerImpl;
import project.gameengine.base.GameContext;
import project.gameengine.base.Player;

import static nuricanozturk.dev.config.RandomConfig.getRandomInstance;
import static nuricanozturk.dev.util.Util.LOGGER;

public class SelectPlanet implements IAction
{
    public SelectPlanet()
    {
        LOGGER.log("Action: Select Planet created...");
    }

    @Override
    public void apply(Player player, GameContext context)
    {
        var planets = ((InitGameContext)context).getPlanets();

        ((PlayerImpl)player).setCurrentPlanet(planets.get(getRandomInstance().nextInt(0, planets.size())));
    }

    @Override
    public String toString()
    {
        return "SELECT PLANET";
    }
}
