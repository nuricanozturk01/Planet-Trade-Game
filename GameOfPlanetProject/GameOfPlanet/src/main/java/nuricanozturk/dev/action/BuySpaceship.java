package nuricanozturk.dev.action;

import nuricanozturk.dev.config.RandomConfig;
import nuricanozturk.dev.entity.InitGameContext;
import nuricanozturk.dev.entity.PlayerImpl;
import nuricanozturk.dev.entity.SpaceShip;
import project.gameengine.base.GameContext;
import project.gameengine.base.Player;

import static nuricanozturk.dev.config.RandomConfig.getRandomInstance;

public class BuySpaceship implements IAction
{
    @Override
    public void apply(Player player, GameContext context)
    {
        var spaceship = ((InitGameContext)context).getSpaceShips()
                .stream()
                .filter(s -> select(s, player))
                .findAny().get();

        ((PlayerImpl)player).setSpaceShip(spaceship);
        ((PlayerImpl)player).setCurrentMoney(((PlayerImpl)player).getCurrentMoney() - spaceship.getPrice());
        spaceship.setIsSold(true);
    }
    private boolean select(SpaceShip spaceShip, Player player)
    {
        var p = ((PlayerImpl)player);
        return p.getCurrentMoney() >= spaceShip.getPrice() && !spaceShip.isSold();
    }
    @Override
    public String toString()
    {
        return "SS";
    }
}