package nuricanozturk.dev.action.actions;

import nuricanozturk.dev.action.IAction;
import nuricanozturk.dev.entity.Cargo;
import nuricanozturk.dev.entity.Commodity;
import nuricanozturk.dev.entity.PlanetTradeGameContext;
import nuricanozturk.dev.entity.PlayerImpl;
import nuricanozturk.dev.util.logger.ILogger;
import project.gameengine.base.GameContext;
import project.gameengine.base.Player;

import java.util.List;
import java.util.stream.IntStream;

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
    private int totalVolume = 0;
    private int volumeCapacity;
    private double playerMoney;
    private double initialMoney;
    private double totalCost = 0D;

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


    private void update()
    {
        m_player.getSpaceShip().addAllCargo(m_cargos);
        m_player.setCurrentMoney(m_player.getCurrentMoney() - totalCost);
        m_player.getSpaceShip().setVolumeCapacity(volumeCapacity - totalVolume);
    }

    @Override
    public void apply(Player player, GameContext context)
    {
        m_player = (PlayerImpl) player;
        playerMoney = m_player.getCurrentMoney();
        initialMoney = m_player.getCurrentMoney();
        if (m_player.getCurrentMoney() <= MIN_UNIT_BUY_PRICE)
        {
            LOGGER.log(m_player.getName() + " on shopping but who not have enough money!");
            return;
        }
        else if (m_player.getCurrentPlanet().getMarket().getCommodities().isEmpty())
        {
            LOGGER.log(m_player.getName() + " on shopping but market is empty");
            return;
        }
        startActionLog();

        volumeCapacity = m_player.getSpaceShip().getVolumeCapacity();
        Commodities = m_player.getCurrentPlanet().getMarket().getCommodities();

        // Sorted by unit buy price
        var commodities = Commodities.stream()
                .filter(c -> c.getCurrentSupplyAmount() > 0)
                .sorted(comparingDouble(Commodity::getUnitBuyPrice))
                .toList();

        m_cargos = chooseItem(commodities);
        update();
        finishActionLog();
    }

    private List<Cargo> chooseItem(List<Commodity> commodities)
    {
        return commodities.stream()
                .map(this::createCargo)
                .filter(c -> c.getQuantityOfCommodity() != 0)
                .toList();
    }

    private boolean findQuantity(int i, double unitBuyPrice, int unitVolume, int amount)
    {
        return totalCost + unitBuyPrice <= playerMoney && totalVolume + unitVolume <= volumeCapacity && i <= amount;
    }

    private Cargo createCargo(Commodity commodity)
    {
        var quantityAmount = getQuantityAmount(commodity);
        var cargo = new Cargo(commodity, quantityAmount);

        if (quantityAmount == commodity.getCurrentSupplyAmount())
            Commodities.remove(commodity);

        else commodity.setCurrentSupplyAmount(commodity.getCurrentSupplyAmount() - quantityAmount);

        return cargo;
    }

    private int getQuantityAmount(Commodity commodity)
    {
        var unitPrice = commodity.getUnitBuyPrice();
        var unitVolume = commodity.getUnitVolume();
        var amount = commodity.getCurrentSupplyAmount();

        return (int) IntStream.rangeClosed(1, commodity.getCurrentSupplyAmount())
                .takeWhile(i -> findQuantity(i, unitPrice, unitVolume, amount))
                .peek(i -> updateCostAndVolume(i, unitPrice, unitVolume))
                .count();
    }

    private void updateCostAndVolume(int ignored, double unitBuyPrice, int unitVolume)
    {
        totalCost += unitBuyPrice;
        totalVolume += unitVolume;
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
            sb.append("r---------------SHOPPING [").append(m_player.getName()).append("]---------------\n");
        }
        //sb.append(m_player.getName()).append(" ").append(getClass().getSimpleName());
        return sb.toString();
    }
}