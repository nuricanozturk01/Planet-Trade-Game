package nuricanozturk.dev.action;

import project.gameengine.base.GameContext;
import project.gameengine.base.Player;

public class PlanTravelling implements IAction
{
    @Override
    public void apply(Player player, GameContext context)
    {
        System.out.println("Travel");
    }

    @Override
    public String toString()
    {
        return "TRAVEL";
    }
}
