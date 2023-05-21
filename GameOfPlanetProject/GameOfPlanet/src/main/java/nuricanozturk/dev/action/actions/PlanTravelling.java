package nuricanozturk.dev.action.actions;

import nuricanozturk.dev.action.IAction;
import nuricanozturk.dev.entity.Planet;
import nuricanozturk.dev.entity.PlanetTradeGameContext;
import nuricanozturk.dev.entity.PlayerImpl;
import nuricanozturk.dev.entity.SpaceShip;
import project.gameengine.base.GameContext;
import project.gameengine.base.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static nuricanozturk.dev.config.RandomConfig.getRandomInstance;
import static nuricanozturk.dev.util.Util.LOGGER;

public class PlanTravelling implements IAction
{
    private PlayerImpl m_player;
    private List<Planet> m_planets;
    private SpaceShip m_spaceShip;
    private double currentFuel;
    private Planet m_currentPlanet;
    private List<Planet> m_validPlanetsForTravelling;

    public PlanTravelling()
    {
        LOGGER.log("Action: Planning Travel created...");
    }

    @Override
    public void apply(Player player, GameContext context)
    {

        m_player = (PlayerImpl) player;
        m_currentPlanet = m_player.getCurrentPlanet();
        m_planets = ((PlanetTradeGameContext) context).getPlanets();
        m_spaceShip = m_player.getSpaceShip();
        currentFuel = m_spaceShip.getCurrentFuel();

        findValidPlanetsForTravelling(m_player.getCurrentPlanet());

        if (!m_validPlanetsForTravelling.isEmpty())
            travel();

        else m_player.setCurrentMoney(m_player.getCurrentMoney() - m_currentPlanet.getParkingPricePerTurn());

    }

    private void travel()
    {
        var targetPlanet = m_validPlanetsForTravelling.get(
                getRandomInstance().nextInt(0, m_validPlanetsForTravelling.size()));

        var necessaryFuel = m_spaceShip.getFuelUsagePerLightYear() * m_currentPlanet.getDistanceMap().get(targetPlanet);

        m_spaceShip.setCurrentFuel(m_spaceShip.getCurrentFuel() - necessaryFuel);

        m_player.setCurrentPlanet(targetPlanet);
    }

    private void findValidPlanetsForTravelling(Planet currentPlanet)
    {
        m_validPlanetsForTravelling = new ArrayList<>();

        IntStream.range(0, currentPlanet.getDistanceMap().size() + 1)
                .mapToObj(i -> m_planets.get(i))
                .filter(planet -> !planet.getName().equals(currentPlanet.getName()))
                .filter(planet -> isFuelEnough(currentPlanet.getDistanceMap().get(planet)))
                .forEach(m_validPlanetsForTravelling::add);
    }

    private boolean isFuelEnough(int targetPlanetDistance)
    {
        var currentFuel = m_spaceShip.getCurrentFuel();

        var usage = m_spaceShip.getFuelUsagePerLightYear();

        return targetPlanetDistance * usage <= currentFuel;
    }

    @Override
    public String toString()
    {
        var sb = new StringBuilder();
        sb.append("\n----------------------TRAVELLING [").append(m_player.getName())
                .append("]------------------------------\n");

        if (m_player.getCurrentPlanet().getName().equals(m_currentPlanet.getName()))
        {

            sb.append(m_player.getName()).append(" cannot travelling because who has not enough money. ")
                    .append("\nParking Price: $").append(m_currentPlanet.getParkingPricePerTurn())
                    .append(" updated current money is: $").append(m_player.getCurrentMoney()).append("\n");
        } else
        {

            sb.append(m_player.getName()).append(" travelling from ").append(m_currentPlanet.getName())
                    .append(" to ").append(m_player.getCurrentPlanet().getName()).append(" fuel usage is ")
                    .append(currentFuel - m_spaceShip.getCurrentFuel()).append(" lt.\n");

        }
        sb.append("----------------------TRAVELLING [").append(m_player.getName())
                .append("]------------------------------\n");
        return sb.toString();
    }
}
