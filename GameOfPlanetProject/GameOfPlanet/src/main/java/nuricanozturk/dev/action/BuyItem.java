package nuricanozturk.dev.action;

import nuricanozturk.dev.entity.Commodity;
import nuricanozturk.dev.entity.PlayerImpl;
import nuricanozturk.dev.entity.SpaceShip;
import project.gameengine.base.GameContext;
import project.gameengine.base.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Comparator.comparingDouble;
import static java.util.Comparator.comparingInt;
import static nuricanozturk.dev.util.Util.LOGGER;

public class BuyItem implements IAction
{

    public BuyItem()
    {
        LOGGER.log("Action: Buy Item create...");
    }

    @Override
    public void apply(Player player, GameContext context)
    {
        var p = (PlayerImpl) player;
        var market = p.getCurrentPlanet().getMarket();
        var commodities = market.getCommodities();

        var selectedItems = chooseItem(commodities, p.getSpaceShip(), p.getCurrentMoney());


        selectedItems.forEach(System.out::println);
        update(p, selectedItems);
    }

    private void update(PlayerImpl player, List<Commodity> selectedItems)
    {
        var totalCost = selectedItems.stream()
                .mapToDouble(c -> c.getCurrentSupplyAmount() * c.getUnitSellPrice())
                .sum();

        var totalVolume = selectedItems.stream().mapToInt(c -> c.getUnitVolume() * c.getCurrentSupplyAmount()).sum();

        player.setCurrentMoney(player.getCurrentMoney() - totalCost);
        player.getSpaceShip().setVolumeCapacity(player.getSpaceShip().getVolumeCapacity() - totalVolume);

        System.out.println("totalCost: " + totalCost);
        System.out.println("totalVolume: " + totalVolume);
        System.out.println("Player Current Money: " + ((PlayerImpl) player).getCurrentMoney());
        System.out.println("VOLUME: " + ((PlayerImpl) player).getSpaceShip().getVolumeCapacity());
        System.exit(1);
    }

    // Marketin izin verdiği ölçüde ve uzay gemisinin alabileceği kadar ürün topla
    private List<Commodity> chooseItem(List<Commodity> commodities, SpaceShip spaceShip, double currentMoney)
    {
        // Oyuncunun parasından az ürünleri getir ve sırala
        var sortedCommoditiesByMoney = commodities.stream()
                .filter(c -> c.getUnitSellPrice() <= currentMoney)
                .sorted(comparingDouble(Commodity::getUnitSellPrice))
                .sorted(comparingInt(Commodity::getUnitVolume))
                .toList();

        if (sortedCommoditiesByMoney.isEmpty())
            return Collections.emptyList();

        return decideCommodities(sortedCommoditiesByMoney, spaceShip, currentMoney);
    }


    private List<Commodity> decideCommodities(List<Commodity> commoditiesByVolumeAndCost, SpaceShip spaceShip,
                                              double currentMoney)
    {
        var list = new ArrayList<Commodity>();
        var sumOfVolume = 0;
        for (var item : commoditiesByVolumeAndCost)
        {
            sumOfVolume += item.getUnitVolume() * item.getCurrentSupplyAmount();
            var totalCost = item.getUnitSellPrice() * item.getCurrentSupplyAmount();

            if (sumOfVolume <= spaceShip.getVolumeCapacity() && totalCost <= currentMoney)
                list.add(item);
        }
        return list;
    }

    private boolean filterItems(Commodity item, int volumeCapacity, double currentMoney)
    {
        var totalVolume = item.getUnitVolume() * item.getCurrentSupplyAmount();
        var totalCost = item.getUnitSellPrice() * item.getCurrentSupplyAmount();

        return totalVolume <= volumeCapacity && totalCost <= currentMoney;
    }

    @Override
    public String toString()
    {
        return "BUY ITEM";
    }
}
