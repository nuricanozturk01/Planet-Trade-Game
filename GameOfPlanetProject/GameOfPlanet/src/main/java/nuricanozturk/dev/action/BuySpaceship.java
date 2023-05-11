package nuricanozturk.dev.action;

import nuricanozturk.dev.entity.InitGameContext;
import nuricanozturk.dev.entity.PlayerImpl;
import nuricanozturk.dev.entity.SpaceShip;
import project.gameengine.base.GameContext;
import project.gameengine.base.Player;

public class BuySpaceship implements IAction
{
    @Override
    public void apply(Player player, GameContext context)
    {
        var spaceship = ((InitGameContext) context).getSpaceShips()
                .stream()
                .filter(s -> select(s, player))
                .findAny();

        if (spaceship.isPresent())
        {
            ((PlayerImpl) player).setSpaceShip(spaceship.get());
            ((PlayerImpl) player).setCurrentMoney(((PlayerImpl) player).getCurrentMoney() - spaceship.get().getPrice());
            spaceship.get().setIsSold(true);
        }
    }

    private boolean select(SpaceShip spaceShip, Player player)
    {
        return ((PlayerImpl) player).getCurrentMoney() >= spaceShip.getPrice() && !spaceShip.isSold();
    }

    @Override
    public String toString()
    {
        return "SS";
    }
}