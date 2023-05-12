package nuricanozturk.dev.action;

import nuricanozturk.dev.entity.PlayerImpl;
import nuricanozturk.dev.entity.SpaceShip;
import project.gameengine.base.GameContext;
import project.gameengine.base.Player;

import static java.lang.String.format;

public class BuyFuel implements IAction
{
    private int currentFuel;
    private int newFuel;
    private SpaceShip spaceship;

    @Override
    public void apply(Player player, GameContext context)
    {
        spaceship = ((PlayerImpl) player).getSpaceShip();

        currentFuel = spaceship.getCurrentFuel();

        buyFuel();
    }

    private void buyFuel()
    {
        newFuel = currentFuel + 1000;
        spaceship.setCurrentFuel(newFuel);
    }

    @Override
    public String toString()
    {
        return spaceship.getCurrentFuel() + "/" + spaceship.getCurrentFuel();
    }
}
