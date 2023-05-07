package nuricanozturk.dev.action;

import nuricanozturk.dev.entity.IVendable;
import nuricanozturk.dev.entity.Player;
import nuricanozturk.dev.entity.SpaceShip;

import static java.lang.String.format;

public class BuySpaceship implements IAction
{
    private final SpaceShip m_spaceShip;
    private final Player m_player;

    public BuySpaceship(SpaceShip spaceShip, Player player)
    {
        m_spaceShip = spaceShip;
        m_player = player;
    }

    public SpaceShip getSpaceShip()
    {
        return m_spaceShip;
    }

    @Override
    public void apply(IVendable vendable)
    {
        m_spaceShip.setIsSold(true);
        System.out.println(m_spaceShip.getName() + " - " +
                format("%.2f", m_spaceShip.getPrice()) +
                " selled to " + m_player.getName() +
                " Current Money: " + format("%.2f", m_player.getCurrentMoney()));
    }

}
