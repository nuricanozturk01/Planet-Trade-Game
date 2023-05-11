package nuricanozturk.dev.action;

import project.gameengine.base.GameContext;
import project.gameengine.base.Player;

public class SoldItem implements IAction
{
    @Override
    public void apply(Player player, GameContext context)
    {
        System.out.println("SOLD ITEM");
    }

    @Override
    public String toString()
    {
        return "SOLD";
    }
}
