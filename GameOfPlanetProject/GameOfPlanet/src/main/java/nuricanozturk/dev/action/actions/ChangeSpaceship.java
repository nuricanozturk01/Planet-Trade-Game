package nuricanozturk.dev.action.actions;

import nuricanozturk.dev.action.IAction;
import nuricanozturk.dev.entity.PlayerImpl;
import project.gameengine.base.GameContext;
import project.gameengine.base.Player;

import static nuricanozturk.dev.action.ActionGenerator.getBuySpaceshipAction;
import static nuricanozturk.dev.action.ActionGenerator.getSellSpaceshipAction;
import static nuricanozturk.dev.util.Util.LOGGER;

public class ChangeSpaceship implements IAction
{
    private double playerInitialMoney;
    private PlayerImpl m_player;
    private IAction sellSpaceshipAction;
    private IAction buySpaceshipAction;

    private void startActionLog()
    {
        LOGGER.log("----------- CHANGE SPACESIP ACTION -----------");
    }

    @Override
    public void apply(Player player, GameContext context)
    {
        m_player = (PlayerImpl) player;
        playerInitialMoney = m_player.getCurrentMoney();

        startActionLog();

        sellSpaceshipAction = (IAction) getSellSpaceshipAction();
        sellSpaceshipAction.apply(player, context);


        buySpaceshipAction = (IAction) getBuySpaceshipAction();
        buySpaceshipAction.apply(player, context);

        finishActionLog();
    }

    private void finishActionLog()
    {
        LOGGER.log("----------- CHANGE SPACESIP ACTION -----------");
    }

    @Override
    public String toString()
    {
        var sb = new StringBuilder();
        sb.append("----------- CHANGE SPACESIP ACTION -----------").append("\n");
        sb.append(sellSpaceshipAction).append("\n").append(buySpaceshipAction).append("\n");
        sb.append("----------- CHANGE SPACESIP ACTION -----------").append("\n");

        return sb.toString();
    }
}
