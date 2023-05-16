package nuricanozturk.dev.action;

import nuricanozturk.dev.entity.Cargo;
import nuricanozturk.dev.entity.Commodity;
import nuricanozturk.dev.entity.PlayerImpl;
import nuricanozturk.dev.util.Constants;
import nuricanozturk.dev.util.logger.ILogger;
import project.gameengine.base.GameContext;
import project.gameengine.base.Player;

import java.util.List;
import java.util.stream.IntStream;

import static java.util.Comparator.comparingDouble;
import static nuricanozturk.dev.util.Constants.MIN_UNIT_BUY_PRICE;
import static nuricanozturk.dev.util.Util.LOGGER;

public class BuyItem implements IAction
{
    private PlayerImpl m_player;
    private List<Cargo> m_cargos;
    private List<Commodity> Commodities;
    private int totalVolume = 0;
    private int volumeCapacity;
    private double playerMoney;
    private double totalCost = 0D;

    public BuyItem()
    {
        LOGGER.log("Action: Buy Item create...");
    }


    private void startActionLog(ILogger logger)
    {
        logger.log(m_player.getName() + " buyed some item with " + m_player.getCurrentMoney() + " money");
    }


    private void finishActionLog(ILogger logger)
    {
        //logger.log(m_player.getName() + " buyed items are: ");
        logger.log(toString());
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

        LOGGER.log(m_player.getName() + " have not enough money for shopping");
        if (m_player.getCurrentMoney() <= MIN_UNIT_BUY_PRICE)
        {

            return;
        }

        startActionLog(LOGGER);

        volumeCapacity = m_player.getSpaceShip().getVolumeCapacity();
        Commodities = m_player.getCurrentPlanet().getMarket().getCommodities();

        // Sorted by unit buy price
        var commodities = Commodities.stream()
                .filter(c -> c.getCurrentSupplyAmount() > 0)
                .sorted(comparingDouble(Commodity::getUnitBuyPrice))
                .toList();

        m_cargos = chooseItem(commodities);
        update();
        finishActionLog(LOGGER);
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
        /*var sb = new StringBuilder();

        if (m_cargos.isEmpty())
            return sb.append(m_player.getName()).append(" Not enough Money....").toString();

        sb.append("\n").append(m_player.getName()).append(" on ")
                .append(m_player.getCurrentPlanet().getName()).append(" Planet [")
                .append(m_player.getCurrentPlanet().getMarket().getName()).append("] Market")
                .append(" buy items:\n");
        m_cargos.forEach(sb::append);*/

        return "";
    }
}