package nuricanozturk.dev.action;

import nuricanozturk.dev.entity.Cargo;
import nuricanozturk.dev.entity.Commodity;
import nuricanozturk.dev.entity.PlayerImpl;
import project.gameengine.base.GameContext;
import project.gameengine.base.Player;

import java.util.List;
import java.util.stream.IntStream;

import static java.util.Comparator.comparingDouble;
import static nuricanozturk.dev.util.Util.LOGGER;

public final class BuyItem implements IAction
{
    private double totalCost = 0D;
    private int totalVolume = 0;
    private int spaceshipVolumeCapacity;
    private double playerMoney = 0D;

    public BuyItem()
    {
        LOGGER.log("Action: Buy Item create...");
    }

    @Override
    public void apply(Player player, GameContext context)
    {
        var p = (PlayerImpl) player;

        LOGGER.log("\n############### Player [" + p.getName() + "] bought new items... ###############\n");
        LOGGER.log("------- Player INFORMATIONS -------");
        LOGGER.log(p.toString());
        LOGGER.log("------- Player INFORMATIONS -------");
        playerMoney = p.getCurrentMoney();
        spaceshipVolumeCapacity = p.getSpaceShip().getVolumeCapacity();

        var market = p.getCurrentPlanet().getMarket();

        // Sorted by unit buy price
        var commodities = market.getCommodities().stream().sorted(comparingDouble(Commodity::getUnitBuyPrice)).toList();

        var cargos = chooseItem(commodities);
        cargos.forEach(c -> LOGGER.log(c.toString()));
        update(p);

        p.getSpaceShip().addAllCargo(cargos);
    }

    private void update(PlayerImpl player)
    {
        player.setCurrentMoney(player.getCurrentMoney() - totalCost);

        player.getSpaceShip().setVolumeCapacity(spaceshipVolumeCapacity - totalVolume);
    }

    private List<Cargo> chooseItem(List<Commodity> commodities)
    {
        return commodities.stream().map(this::createCargo).toList();
    }

    private Cargo createCargo(Commodity commodity)
    {
        var quantityAmount = getQuantityAmount(commodity);

        commodity.setCurrentSupplyAmount(commodity.getCurrentSupplyAmount() - quantityAmount);

        return new Cargo(commodity, quantityAmount);
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

    private boolean findQuantity(int i, double unitBuyPrice, int unitVolume, int amount)
    {
        return totalCost + unitBuyPrice <= playerMoney && totalVolume + unitVolume <= spaceshipVolumeCapacity && i <= amount;
    }

    @Override
    public String toString()
    {
        return "BUY ITEM";
    }
}
