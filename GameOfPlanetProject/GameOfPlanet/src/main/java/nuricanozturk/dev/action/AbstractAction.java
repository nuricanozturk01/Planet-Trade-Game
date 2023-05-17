package nuricanozturk.dev.action;

import nuricanozturk.dev.util.logger.ILogger;
import project.gameengine.base.GameContext;
import project.gameengine.base.Player;

public abstract class AbstractAction implements IAction
{

    public AbstractAction()
    {
    }

    protected abstract void startActionLog();

    protected abstract void finishActionLog();

    protected abstract void update();

    protected abstract void applyAction(Player player, GameContext context);


    @Override
    public void apply(Player player, GameContext context)
    {
        applyAction(player, context);

        update();
    }
}
