package nuricanozturk.dev.action;

import project.gameengine.base.GameContext;
import project.gameengine.base.Player;

import static nuricanozturk.dev.util.Util.LOGGER;

public class PlanTravelling implements IAction
{
    public PlanTravelling()
    {
        LOGGER.log("Action: Planning Travel created...");
    }

    @Override
    public void apply(Player player, GameContext context)
    {
       // System.out.println("Travel");
    }

    @Override
    public String toString()
    {
        return "TRAVEL";
    }
}
