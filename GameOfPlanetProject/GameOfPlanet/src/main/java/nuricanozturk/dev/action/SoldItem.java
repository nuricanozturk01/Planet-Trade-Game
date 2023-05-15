package nuricanozturk.dev.action;

import nuricanozturk.dev.entity.*;
import project.gameengine.base.GameContext;
import project.gameengine.base.Player;

import java.util.stream.IntStream;

import static nuricanozturk.dev.util.Util.LOGGER;

/*
Sell any cargo in the spaceship. The sell operation causes increase in the current money
with amount calculated by the cargo amount and unit sell price of the commodity in the
market
 */
public class SoldItem implements IAction
{
    private double earnedMoney;
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

        if (spaceship.getCargos().isEmpty() || spaceship.getCargos() == null)
            return;
        soldItems(p, market, spaceship);

        updatePlayer(p);

    }

    private void updatePlayer(PlayerImpl p)
    {
        p.setCurrentMoney(p.getCurrentMoney() + earnedMoney);
    }

    private void soldItems(PlayerImpl p, Market market, SpaceShip spaceship)
    {
        var cargoSize = spaceship.getCargos().size();

        IntStream.range(0, cargoSize).forEach(i -> sellItem(i, spaceship.getCargos().stream().findAny().get(), market));
    }

    private void sellItem(int ignored, Cargo cargo, Market market)
    {
        System.out.println("asfasf");
        var soldCommodity = cargo.getCommodity();
        soldCommodity.setCurrentSupplyAmount(cargo.getQuantityOfCommodity());
        System.out.println(cargo);
        //System.exit(1);
        earnedMoney = soldCommodity.getUnitSellPrice() * cargo.getQuantityOfCommodity();

        market.addCommodity(soldCommodity);
    }
}
