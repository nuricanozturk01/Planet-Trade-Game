package nuricanozturk.dev.action;

import nuricanozturk.dev.entity.PlayerImpl;
import nuricanozturk.dev.entity.SpaceShip;
import nuricanozturk.dev.util.logger.ILogger;
import project.gameengine.base.GameContext;
import project.gameengine.base.Player;

import static java.lang.String.format;
import static nuricanozturk.dev.util.Util.LOGGER;

public final class BuyFuel implements IAction
{
    private final String START_MESSAGE =
            "%s on fuel station with $%.2f\nSpaceship: %s [%d/%d], fuelUsage: %d, unitPrice: $%.2f";
    private final String END_MESSAGE = "[%s] bought fuel. [%d/%d] Total = $%d, Rest Of Amount: $%.2f";
    private PlayerImpl m_player;
    private SpaceShip m_spaceship;
    private int initialFuel;
    private double initialPlayerMoney;
    private int fuelPrice;

    public BuyFuel()
    {
        LOGGER.log("Action: Buy Fuel create...");
    }

    @Override
    public void apply(Player player, GameContext context)
    {
        m_player = (PlayerImpl) player;
        m_spaceship = m_player.getSpaceShip();

        initialFuel = m_spaceship.getCurrentFuel();
        initialPlayerMoney = m_player.getCurrentMoney();

        if (initialPlayerMoney <= 0)
        {
            LOGGER.log(m_player.getName() + " on fuel station but who not have enough money!");

            return;
        }

        startActionLog(LOGGER);

        int buyFuel = buyFuel();

        fuelPrice = (int) (m_player.getCurrentPlanet().getUnitFuelPrice() * buyFuel);

        update();

        finishActionLog(LOGGER);
    }

    private void startActionLog(ILogger logger)
    {
        logger.log("\n--------------------BUY FUEL--------------------------------");
        logger.log(format(START_MESSAGE, m_player.getName(), m_player.getCurrentMoney(), m_spaceship.getName(),
                initialFuel, m_spaceship.getFuelCapacity(),
                m_spaceship.getFuelUsagePerLightYear(), m_player.getCurrentPlanet().getUnitFuelPrice()));
    }

    private void finishActionLog(ILogger logger)
    {
        logger.log(format(END_MESSAGE, m_player.getName(), m_spaceship.getCurrentFuel(), m_spaceship.getFuelCapacity(),
                fuelPrice, m_player.getCurrentMoney()));
        logger.log("--------------------BUY FUEL--------------------------------\n");
    }

    private void update()
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
        var sb = new StringBuilder();
        if (initialPlayerMoney <= 0)
            sb.append(m_player.getName() + " on fuel station but who not have enough money!").append("\n");
        else
        {
            sb.append("\n----------------------BUY FUEL------------------------------\n");
            sb.append(format(START_MESSAGE, m_player.getName(), initialPlayerMoney, m_spaceship.getName(),
                            initialFuel, m_spaceship.getFuelCapacity(),
                            m_spaceship.getFuelUsagePerLightYear(), m_player.getCurrentPlanet().getUnitFuelPrice()))
                    .append("\n");
            sb.append(format(END_MESSAGE, m_player.getName(), m_spaceship.getCurrentFuel(), m_spaceship.getFuelCapacity(),
                    fuelPrice, m_player.getCurrentMoney())).append("\n");
            sb.append("----------------------BUY FUEL------------------------------\n");
        }
        //sb.append(m_player.getName()).append(" ").append(getClass().getSimpleName());

        return sb.toString();
    }
}
