package nuricanozturk.dev.action;

import project.gameengine.base.Action;
import project.gameengine.base.GameContext;
import project.gameengine.base.Player;


public interface IAction extends Action
{
    void apply(Player player, GameContext context);
}
