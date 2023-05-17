package nuricanozturk.dev.action;

import nuricanozturk.dev.entity.PlayerImpl;
import project.gameengine.base.GameContext;
import project.gameengine.base.Player;

import static nuricanozturk.dev.util.Util.LOGGER;

public class PlanTravelling implements IAction
{
    public static int COUNTER = 1;
    private PlayerImpl m_player;
    public PlanTravelling()
    {
        LOGGER.log("Action: Planning Travel created...");
    }

    @Override
    public void apply(Player player, GameContext context)
    {
        m_player = (PlayerImpl) player;
       // System.out.println("Travel");
    }

    @Override
    public String toString()
    {
        var sb = new StringBuilder();
        sb.append(m_player.getName()).append(" ").append(getClass().getSimpleName());
        return sb.toString();
    }
}
