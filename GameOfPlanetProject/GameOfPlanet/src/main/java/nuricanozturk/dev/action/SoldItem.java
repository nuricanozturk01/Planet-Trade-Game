package nuricanozturk.dev.action;

import nuricanozturk.dev.entity.Cargo;
import nuricanozturk.dev.entity.Commodity;
import nuricanozturk.dev.entity.Market;
import nuricanozturk.dev.entity.PlayerImpl;
import project.gameengine.base.GameContext;
import project.gameengine.base.Player;

import java.util.List;
import java.util.stream.IntStream;

import static nuricanozturk.dev.config.RandomConfig.getRandomInstance;
import static nuricanozturk.dev.util.Util.LOGGER;

public class SoldItem implements IAction
{
    private Market m_market;
    private PlayerImpl m_player;
    private List<Cargo> m_cargos;

    public SoldItem()
    {
        LOGGER.log("Action: Sell Item created...");
    }

    @Override
    public void apply(Player player, GameContext context)
    {
        m_player = (PlayerImpl) player;

        var spaceship = m_player.getSpaceShip();
        m_market = m_player.getCurrentPlanet().getMarket();
        m_cargos = spaceship.getCargos();


        if (spaceship.getCargos().isEmpty() || spaceship.getCargos() == null)
            return;

        soldItems();
    }

    private void updatePlayer(double earnedMoney)
    {
        m_player.setCurrentMoney(m_player.getCurrentMoney() + earnedMoney);
    }

    private void soldItems()
    {
        var sellCount = getRandomInstance().nextInt(0, m_cargos.size()) / 2;
        IntStream.range(0, sellCount).forEach(this::startSell);
    }

    private void startSell(int ignored)
    {
        var randomCargo = m_cargos.stream().findAny();
        randomCargo.ifPresent(c -> sellItem(randomCargo.get()));
    }

    private void sellItem(Cargo cargo)
    {
        m_market.getCommodities().stream()
                .filter(c -> c.getName().equals(cargo.getCommodity().getName()))
                .findFirst()
                .ifPresentOrElse(c -> existsItemOnMarket(c, cargo), () -> notExistsItemOnMarket(cargo));

        m_cargos.remove(cargo);

        updatePlayer(cargo.getQuantityOfCommodity() * cargo.getCommodity().getUnitSellPrice());
    }

    private void notExistsItemOnMarket(Cargo cargo)
    {
        cargo.getCommodity().setCurrentSupplyAmount(cargo.getQuantityOfCommodity());
        m_market.addCommodity(cargo.getCommodity());
    }

    private void existsItemOnMarket(Commodity commodityOnMarket, Cargo cargo)
    {
        commodityOnMarket.setCurrentSupplyAmount(commodityOnMarket.getCurrentSupplyAmount() + cargo.getQuantityOfCommodity());
    }
}