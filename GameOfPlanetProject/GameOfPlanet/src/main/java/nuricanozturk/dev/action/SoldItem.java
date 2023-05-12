package nuricanozturk.dev.action;

import project.gameengine.base.GameContext;
import project.gameengine.base.Player;

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
        //System.out.println("SOLD ITEM");
    }

    @Override
    public String toString()
    {
        return "SOLD";
    }
}
