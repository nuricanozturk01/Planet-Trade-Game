package nuricanozturk.dev.action;

import project.gameengine.base.GameContext;
import project.gameengine.base.Player;

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

    }

    @Override
    public String toString()
    {
        return "BUY ITEM";
    }
}
