package nuricanozturk.dev.action;

import project.gameengine.base.Player;

public class SoldItem implements IAction
{
    @Override
    public void apply(Player player)
    {
        System.out.println("SOLD ITEM");
    }

    @Override
    public String toString()
    {
        return "SOLD";
    }
}
