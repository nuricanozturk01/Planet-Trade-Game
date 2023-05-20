package nuricanozturk.dev.action.actions;

import nuricanozturk.dev.action.IAction;
import nuricanozturk.dev.entity.Cargo;
import nuricanozturk.dev.entity.Commodity;
import nuricanozturk.dev.entity.PlayerImpl;
import nuricanozturk.dev.entity.SpaceShip;
import project.gameengine.base.GameContext;
import project.gameengine.base.Player;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;
import static java.util.Comparator.comparingDouble;
import static nuricanozturk.dev.util.Constants.MIN_UNIT_BUY_PRICE;
import static nuricanozturk.dev.util.Util.LOGGER;

public class BuyItem implements IAction
{
    private final String START_MESSAGE = "%s on shopping on %s planet with $%.2f";
    private final String END_MESSAGE = "[%s] bought something: Rest Of Amount: $%.2f\nShopping List:";
    private PlayerImpl m_player;
    private List<Cargo> m_cargos;
    private List<Commodity> Commodities;
    private List<Commodity> _removedCommodities;
    private int totalVolume = 0;
    private double initialMoney;
    private double totalCost = 0D;

    private SpaceShip m_spaceship;

    public BuyItem()
    {
        LOGGER.log("Action: Buy Item create...");
    }


    private void startActionLog()
    {
        LOGGER.log("\n--------------------SHOPPING--------------------------------");
        LOGGER.log(format(START_MESSAGE, m_player.getName(), m_player.getCurrentPlanet().getName(),
                m_player.getCurrentMoney()));
    }


    private void finishActionLog()
    {
        LOGGER.log(format(END_MESSAGE, m_player.getName(), m_player.getCurrentMoney()));
        m_cargos.stream().map(Cargo::toString).forEach(LOGGER::log);
        LOGGER.log("--------------------SHOPPING--------------------------------\n");

    }


    private void update(double currentCost, int currentVolume)
    {
        totalCost += currentCost;
        totalVolume += currentVolume;
        m_player.setCurrentMoney(m_player.getCurrentMoney() - currentCost);
        m_spaceship.setCurrentVolume(m_spaceship.getCurrentVolume() + currentVolume);
    }

    @Override
    public void apply(Player player, GameContext context)
    {
        m_player = (PlayerImpl) player;
        initialMoney = m_player.getCurrentMoney();
        m_spaceship = m_player.getSpaceShip();
        _removedCommodities = new ArrayList<>();
        totalVolume = 0;
        totalCost = 0;

        if (initialMoney <= MIN_UNIT_BUY_PRICE)
        {
            LOGGER.log(m_player.getName() + " on shopping but who not have enough money!");
        } else if (m_player.getCurrentPlanet().getMarket().getCommodities().isEmpty())
        {
            LOGGER.log(m_player.getName() + " on shopping but market is empty");
        } else
        {
            startActionLog();

            m_cargos = m_player.getSpaceShip().getCargos();

            Commodities = m_player.getCurrentPlanet().getMarket().getCommodities();

            buyMaxItems();

            finishActionLog();
        }
    }

    private void buyMaxItems()
    {
        Commodities.stream().sorted(comparingDouble(Commodity::getUnitBuyPrice)).forEach(this::buy);
    }


    private void buy(Commodity commodity)
    {
        if (totalCost >= initialMoney || totalVolume >= m_spaceship.getVolumeCapacity() || commodity.getCurrentSupplyAmount() < 0)
            return;

        //int quantity = (int) (m_player.getCurrentMoney() / commodity.getUnitBuyPrice()); // how many can be bought
        var quantity = (int) (m_player.getCurrentMoney() / commodity.getUnitBuyPrice());
        if (quantity == 0)
            return;

        if (quantity < commodity.getCurrentSupplyAmount())
            commodity.setCurrentSupplyAmount(commodity.getCurrentSupplyAmount() - quantity);

        else quantity = commodity.getCurrentSupplyAmount();

        var currentCost = quantity * commodity.getUnitBuyPrice();
        var currentVolume = quantity * commodity.getUnitVolume();

        while (totalCost + currentCost > m_player.getCurrentMoney() &&
                totalVolume + currentVolume > m_spaceship.getVolumeCapacity() &&
                quantity > 0)
        {
            quantity = quantity / 2;
            currentCost = quantity * commodity.getUnitBuyPrice();
            currentVolume = quantity * commodity.getUnitVolume();
        }

        m_cargos.add(new Cargo(commodity, quantity));

        if (quantity >= commodity.getCurrentSupplyAmount())
            _removedCommodities.add(commodity);

        update(currentCost, currentVolume);
    }

    @Override
    public String toString()
    {
        var sb = new StringBuilder();

        if (initialMoney <= MIN_UNIT_BUY_PRICE)
            sb.append(m_player.getName()).append(" on shopping but who not have enough money!");
        else if (m_player.getCurrentPlanet().getMarket().getCommodities().isEmpty())
        {
            sb.append(m_player.getName()).append(" on shopping but market is empty")
                    .append(m_player.getCurrentPlanet().getName());
        } else
        {
            sb.append("\n---------------SHOPPING [").append(m_player.getName()).append("]---------------\n");
            sb.append(format(START_MESSAGE, m_player.getName(), m_player.getCurrentPlanet().getName(),
                    initialMoney)).append("\n");
            sb.append(format(END_MESSAGE, m_player.getName(), m_player.getCurrentMoney())).append("\n");
            m_cargos.stream().map(Cargo::toString).forEach(sb::append);
            sb.append("---------------SHOPPING [").append(m_player.getName()).append("]---------------\n");

        }

        return sb.toString();
    }
}