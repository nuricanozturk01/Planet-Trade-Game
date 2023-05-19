package nuricanozturk.dev.action.actions;

import nuricanozturk.dev.action.IAction;
import nuricanozturk.dev.entity.Planet;
import nuricanozturk.dev.entity.PlanetTradeGameContext;
import nuricanozturk.dev.entity.PlayerImpl;
import nuricanozturk.dev.util.logger.ILogger;
import project.gameengine.base.GameContext;
import project.gameengine.base.Player;

import static nuricanozturk.dev.config.RandomConfig.getRandomInstance;
import static nuricanozturk.dev.util.Util.LOGGER;

public class SelectPlanet implements IAction
{
    private PlayerImpl m_player;
    private Planet m_selectedPlanet;

    public SelectPlanet()
    {
        LOGGER.log("Action: Select Planet created...");
    }

    private void startActionLog(ILogger logger)
    {
        logger.log(m_player.getName() + " selecting the planet...");
    }

    private void finishActionLog(ILogger logger)
    {
        logger.log(m_player.getName() + " on " + m_selectedPlanet.getName());
    }

    public void update()
    {
        m_player.setCurrentPlanet(m_selectedPlanet);
    }

    @Override
    public String toString()
    {
        return "SELECT PLANET";
    }

    @Override
    public void apply(Player player, GameContext context)
    {
        m_player = (PlayerImpl) player;

        var planets = ((PlanetTradeGameContext) context).getPlanets();

        m_selectedPlanet = planets.get(getRandomInstance().nextInt(0, planets.size() - 1));

        update();
    }
}
