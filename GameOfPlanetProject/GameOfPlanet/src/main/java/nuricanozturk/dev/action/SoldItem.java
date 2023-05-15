package nuricanozturk.dev.action;

import nuricanozturk.dev.config.RandomConfig;
import nuricanozturk.dev.entity.*;
import project.gameengine.base.GameContext;
import project.gameengine.base.Player;

import static java.util.Comparator.comparingDouble;
import static nuricanozturk.dev.util.Util.LOGGER;

public class SoldItem implements IAction
{
    public SoldItem()
    {
        LOGGER.log("Action: Sell Item created...");
    }

    @Override
    public void apply(Player player, GameContext context)
    {
        var p = (PlayerImpl) player;
        var spaceship = p.getSpaceShip();
        var market = p.getCurrentPlanet().getMarket();

        var earningMoney = soldItems(p, market, spaceship);
        System.out.println("PPPPP: " + p.getCurrentMoney());
        updatePlayer(p, earningMoney);
        System.out.println("AAAAA: " + p.getCurrentMoney());
    }

    private void updatePlayer(PlayerImpl p, double earningMoney)
    {
        p.setCurrentMoney(p.getCurrentMoney() + earningMoney);
    }

    private double soldItems(PlayerImpl p, Market market, SpaceShip spaceship)
    {

        return spaceship.getCargos().stream()
                .sorted(comparingDouble(c -> c.getCommodity().getUnitSellPrice()))
                .limit(4)
                .map(Cargo::getCommodity)
                .peek(c -> c.setCurrentSupplyAmount(c.getCurrentSupplyAmount() - 1))
                .mapToDouble(Commodity::getUnitSellPrice)
                .sum();
    }

    @Override
    public String toString()
    {
        return "SOLD";
    }
}
