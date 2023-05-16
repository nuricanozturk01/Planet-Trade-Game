package nuricanozturk.dev.action;

import nuricanozturk.dev.util.logger.ILogger;
import project.gameengine.base.GameContext;
import project.gameengine.base.Player;

import static nuricanozturk.dev.util.Util.LOGGER;

public abstract class AbstractAction implements IAction
{
    public AbstractAction()
    {
    }
    protected abstract void startActionLog(ILogger logger);

    protected abstract void finishActionLog(ILogger logger);

    protected abstract void update();

    protected abstract void applyAction(Player player, GameContext context);

    @Override
    public void apply(Player player, GameContext context)
    {
        applyAction(player, context);

        startActionLog(LOGGER); // ?

        update();

        finishActionLog(LOGGER);
    }
}
