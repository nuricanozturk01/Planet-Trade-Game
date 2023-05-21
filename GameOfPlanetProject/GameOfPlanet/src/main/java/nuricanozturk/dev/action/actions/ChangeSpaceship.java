package nuricanozturk.dev.action.actions;

import nuricanozturk.dev.action.IAction;
import nuricanozturk.dev.entity.PlanetTradeGameContext;
import nuricanozturk.dev.entity.PlayerImpl;
import nuricanozturk.dev.entity.SpaceShip;
import project.gameengine.base.GameContext;
import project.gameengine.base.Player;

import java.util.List;
import java.util.Optional;

import static java.lang.String.format;
import static nuricanozturk.dev.util.Util.LOGGER;

public class ChangeSpaceship implements IAction
{
    private final String START_MESSAGE = "%s on hangar who is change spaceship with $%.2f and current spaceship: %s";
    private final String END_MESSAGE = "[%s] on hangar who is changed spaceship to %s and has $%.2f";
    private SpaceShip m_currentSpaceship;
    private SpaceShip m_newSpaceship;
    private double m_playerInitialMoney;
    private PlayerImpl m_player;
    private List<SpaceShip> m_hangar;

    private void startActionLog()
    {
        LOGGER.log(format(START_MESSAGE, m_player.getName(), m_playerInitialMoney, m_currentSpaceship.getName()));
    }

    private void finishActionLog()
    {
        LOGGER.log(format(END_MESSAGE, m_player.getName(), m_newSpaceship.getName(), m_player.getCurrentMoney()));
    }

    @Override
    public void apply(Player player, GameContext context)
    {
        m_player = (PlayerImpl) player;
        m_currentSpaceship = m_player.getSpaceShip();
        m_hangar = ((PlanetTradeGameContext) context).getSpaceShips();
        m_playerInitialMoney = m_player.getCurrentMoney();

        if (m_currentSpaceship == null)
        {
            LOGGER.log(m_player.getName() + " has not spaceship....");
            return;
        }
        startActionLog();

        changeSpaceship();

        finishActionLog();
    }


    private void changeSpaceship()
    {
        var newSpaceship = findNewSpaceship();

        newSpaceship.ifPresent(this::buyNewAndSellCurrent);
    }

    private void buyNewAndSellCurrent(SpaceShip newSpaceship)
    {
        m_newSpaceship = newSpaceship;

        // m_currentSpaceship.setIsSold(false); //  Not added hangar again...
        m_player.setCurrentMoney(m_player.getCurrentMoney() + m_currentSpaceship.getPrice());
        m_currentSpaceship.setPrice(m_currentSpaceship.getPrice() - 100D);

        m_player.setSpaceShip(newSpaceship);
        newSpaceship.setIsSold(true);
        m_player.setCurrentMoney(m_player.getCurrentMoney() - newSpaceship.getPrice());
    }

    private Optional<SpaceShip> findNewSpaceship()
    {

        return m_hangar.stream()
                .filter(s -> !s.isSold() && s.getPrice() <= m_player.getCurrentMoney() + m_currentSpaceship.getPrice())
                .findFirst();
    }

    @Override
    public String toString()
    {
        var sb = new StringBuilder();
        sb.append("----------------------CHANGE SPACESHIP [").append(m_player.getName())
                .append("]------------------------------\n");
        if (m_currentSpaceship == null) sb.append(m_player.getName()).append(" has not spaceship...").append("\n");

        else if (m_currentSpaceship.getName().equals(m_newSpaceship.getName()) || m_newSpaceship == null)
            sb.append(m_player.getName()).append(" not enough money for buy new spaceship...");

        else sb.append(
                            format(START_MESSAGE, m_player.getName(), m_playerInitialMoney, m_currentSpaceship.getName()))
                    .append("\n")
                    .append(format(END_MESSAGE, m_player.getName(), m_newSpaceship.getName(),
                            m_player.getCurrentMoney())).append("\n");
        sb.append("----------------------CHANGE SPACESHIP [").append(m_player.getName())
                .append("]------------------------------\n\n");
        return sb.toString();
    }
}
