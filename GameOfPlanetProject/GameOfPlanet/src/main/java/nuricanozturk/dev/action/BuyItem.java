package nuricanozturk.dev.action;

import nuricanozturk.dev.entity.Cargo;
import nuricanozturk.dev.entity.Commodity;
import nuricanozturk.dev.entity.PlayerImpl;
import nuricanozturk.dev.entity.SpaceShip;
import project.gameengine.base.GameContext;
import project.gameengine.base.Player;

import java.util.ArrayList;
import java.util.List;

import static java.util.Comparator.comparingDouble;
import static nuricanozturk.dev.util.Util.LOGGER;

public class BuyItem implements IAction
{
    private double totalCost = 0.0;
    private int totalVolume = 0;

    public BuyItem()
    {
        LOGGER.log("Action: Buy Item create...");
    }

    @Override
    public void apply(Player player, GameContext context)
    {
        var p = (PlayerImpl) player;
        System.out.println("MONEY: " + p.getCurrentMoney());
        var market = p.getCurrentPlanet().getMarket();
        var commodities = market.getCommodities().stream().sorted(comparingDouble(Commodity::getUnitBuyPrice)).toList();

        System.out.println("-----------------------------");
        commodities.forEach(System.out::println);
        System.out.println("-----------------------------");
        var cargos = chooseItem(commodities, p.getSpaceShip(), p.getCurrentMoney());

        cargos.forEach(System.out::println);

        update(p);
        cargos.forEach(p.getSpaceShip()::addCargo);
        //System.exit(1);
    }

    private void update(PlayerImpl player)
    {
        player.setCurrentMoney(player.getCurrentMoney() - totalCost);
        player.getSpaceShip().setVolumeCapacity(player.getSpaceShip().getVolumeCapacity() - totalVolume);

    }

    private List<Cargo> chooseItem(List<Commodity> commodities, SpaceShip spaceShip, double currentMoney)
    {
        var cargoList = new ArrayList<Cargo>();
        //System.out.println("CURRENT MONEY: " + currentMoney);
        var remainingMoney = currentMoney;
        var remainingVolume = spaceShip.getVolumeCapacity();

        for (Commodity commodity : commodities)
        {
            var unitBuyPrice = commodity.getUnitBuyPrice();
            var unitVolume = commodity.getUnitVolume();

            if (unitBuyPrice <= remainingMoney && unitVolume <= remainingVolume && commodity.getCurrentSupplyAmount() > 0)
            {
                var maxQuantity = Math.min(remainingVolume / unitVolume, commodity.getCurrentSupplyAmount());

                if (remainingMoney - (unitBuyPrice * maxQuantity) < 0 || remainingVolume - (unitVolume * maxQuantity) < 0)
                    break;

                cargoList.add(new Cargo(commodity, maxQuantity));

                remainingMoney -= unitBuyPrice * maxQuantity;
                remainingVolume -= unitVolume * maxQuantity;

                commodity.setCurrentSupplyAmount(commodity.getCurrentSupplyAmount() - maxQuantity);
            }
        }
       /*cargoList.forEach(c -> System.out.println(c.getCommodity().getName() + " - " + c.getCommodity().getCurrentSupplyAmount()));
        System.out.println("RM: " + remainingMoney);
        System.out.println("RV: " + remainingVolume);*/
        return cargoList;
    }

    @Override
    public String toString()
    {
        return "BUY ITEM";
    }
}
