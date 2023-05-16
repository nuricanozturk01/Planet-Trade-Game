package nuricanozturk.dev.action;

import nuricanozturk.dev.entity.PlayerImpl;
import nuricanozturk.dev.entity.SpaceShip;
import nuricanozturk.dev.util.logger.ILogger;
import project.gameengine.base.GameContext;
import project.gameengine.base.Player;

import static nuricanozturk.dev.util.Util.LOGGER;

public final class BuyFuel extends AbstractAction
{
    private PlayerImpl m_player;
    private SpaceShip m_spaceship;
    private int buyFuel;
    private int fuelPrice;

    public BuyFuel()
    {
        LOGGER.log("Action: Buy Fuel create...");
    }

    @Override
    protected void applyAction(Player player, GameContext context)
    {
        m_player = (PlayerImpl) player;
        m_spaceship = m_player.getSpaceShip();

        var fuel = buyFuel();

        buyFuel = fuel;
        fuelPrice = (int) (m_player.getCurrentPlanet().getUnitFuelPrice() * fuel);
    }

    @Override
    protected void startActionLog(ILogger logger)
    {

    }
    @Override
    protected void finishActionLog(ILogger logger)
    {

    }

    @Override
    protected void update()
    {
        m_spaceship.setCurrentFuel(m_spaceship.getCurrentFuel() + fuelPrice);
        m_player.setCurrentMoney(m_player.getCurrentMoney() - fuelPrice);
    }
    private int buyFuel()
    {
        var playerMoney = m_player.getCurrentMoney();
        var unitPrice = m_player.getCurrentPlanet().getUnitFuelPrice();

        if (playerMoney < unitPrice)
            return 0;

        var fuelCapacity = m_spaceship.getFuelCapacity();
        var currentFuel = m_spaceship.getCurrentFuel();

        int necessaryAmount = (int) ((fuelCapacity - currentFuel) / unitPrice);

        return (int) Math.min(necessaryAmount, playerMoney / unitPrice);
    }

    @Override
    public String toString()
    {
        return "FUEL";
    }
}
