package nuricanozturk.dev.action;

import project.gameengine.base.GameContext;
import project.gameengine.base.Player;

public class BuyItem implements IAction
{

    @Override
    public void apply(Player player, GameContext context)
    {
        //System.out.println("BUY ITEM");
    }

    @Override
    public String toString()
    {
        return "BUY ITEM";
    }
}
