package nuricanozturk.dev.action.actions;

import nuricanozturk.dev.action.IAction;
import nuricanozturk.dev.entity.PlayerImpl;
import nuricanozturk.dev.entity.SpaceShip;
import project.gameengine.base.GameContext;
import project.gameengine.base.Player;

import static java.lang.String.format;
import static nuricanozturk.dev.util.Util.LOGGER;

public class SellSpaceship implements IAction
{
    private final String START_MESSAGE = "%s on hangar who is sell spaceship with $%.2f";
    private final String END_MESSAGE = "[%s] on hangar who is sold spaceship and has $%.2f";
    private SpaceShip m_currentSpaceship;
    private double m_playerInitialMoney;
    private PlayerImpl m_player;

    private void startActionLog()
    {
        LOGGER.log(format(START_MESSAGE, m_player.getName(), m_playerInitialMoney));
    }

    private void finishActionLog()
    {
        LOGGER.log(format(END_MESSAGE, m_player.getName(), m_player.getCurrentMoney()));
    }

    @Override
    public void apply(Player player, GameContext context)
    {
        m_player = (PlayerImpl) player;
        m_currentSpaceship = m_player.getSpaceShip();
        if (m_currentSpaceship == null)
        {
            LOGGER.log(m_player.getName() + " has not spaceship....");
            return;
        }
        startActionLog();

        sellSpaceship();

        finishActionLog();
    }


    private void sellSpaceship()
    {
        m_currentSpaceship.setIsSold(false);
        m_player.setSpaceShip(null);
        m_player.setCurrentMoney(m_player.getCurrentMoney() + m_currentSpaceship.getPrice());
    }

    @Override
    public String toString()
    {
        var sb = new StringBuilder();

        if (m_currentSpaceship == null) sb.append(m_player.getName()).append(" has not spaceship...").append("\n");
        else
        {
            sb.append(format(START_MESSAGE, m_player.getName(), m_playerInitialMoney)).append("\n")
                    .append(format(END_MESSAGE, m_player.getName(), m_player.getCurrentMoney())).append("\n");
        }


        return sb.toString();
    }
}
