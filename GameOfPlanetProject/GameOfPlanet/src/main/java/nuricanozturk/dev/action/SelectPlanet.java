package nuricanozturk.dev.action;

import nuricanozturk.dev.entity.InitGameContext;
import nuricanozturk.dev.entity.PlayerImpl;
import project.gameengine.base.GameContext;
import project.gameengine.base.Player;

public class SelectPlanet implements IAction
{
    @Override
    public void apply(Player player, GameContext context)
    {
        ((PlayerImpl)player).setCurrentPlanet(((InitGameContext)context).getPlanets().stream().findAny().get());
    }

    @Override
    public String toString()
    {
        return "SELECT PLANET";
    }
}
