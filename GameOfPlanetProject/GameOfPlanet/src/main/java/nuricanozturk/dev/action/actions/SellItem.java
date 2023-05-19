package nuricanozturk.dev.action.actions;

import nuricanozturk.dev.action.IAction;
import nuricanozturk.dev.entity.Cargo;
import nuricanozturk.dev.entity.Commodity;
import nuricanozturk.dev.entity.Market;
import nuricanozturk.dev.entity.PlayerImpl;
import project.gameengine.base.GameContext;
import project.gameengine.base.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static java.lang.String.format;
import static nuricanozturk.dev.config.RandomConfig.getRandomInstance;
import static nuricanozturk.dev.util.Util.LOGGER;

public class SellItem implements IAction {
    private final List<Cargo> m_sellItems = new ArrayList<>();
    private final String START_MESSAGE = "%s on market for sell something with $%.2f";
    private final String END_MESSAGE = "[%s] sold something Rest of Amount: $%.2f\nSold Items:\n";
    private Market m_market;
    private PlayerImpl m_player;
    private List<Cargo> m_cargos;
    private double initialMoney;

    public SellItem() {
        LOGGER.log("Action: Sell Item created...");
    }

    @Override
    public void apply(Player player, GameContext context) {
        m_player = (PlayerImpl) player;
        initialMoney = m_player.getCurrentMoney();

        startActionLog();

        var spaceship = m_player.getSpaceShip();

        m_market = m_player.getCurrentPlanet().getMarket();
        m_cargos = spaceship.getCargos();


        if (spaceship.getCargos().isEmpty() || spaceship.getCargos() == null)
            return;

        soldItems();

        finishActionLog();
    }


    private void startActionLog() {
        LOGGER.log("\n--------------------SELL ITEM--------------------------------");
        LOGGER.log(format(START_MESSAGE, m_player.getName(), initialMoney));
    }


    private void finishActionLog() {
        LOGGER.log(format(END_MESSAGE, m_player.getName(), m_player.getCurrentMoney()));
        m_sellItems.stream().map(Cargo::toString).forEach(LOGGER::log);
        LOGGER.log("--------------------SELL ITEM--------------------------------\n");

    }

    private void updatePlayer(double earnedMoney) {
        m_player.setCurrentMoney(m_player.getCurrentMoney() + earnedMoney);
    }

    private void soldItems() {
        var sellCount = getRandomInstance().nextInt(0, m_cargos.size());
        IntStream.range(0, sellCount).forEach(this::startSell);
    }

    private void startSell(int ignored) {
        var randomCargo = m_cargos.stream().findAny();
        randomCargo.ifPresent(c -> sellItem(randomCargo.get()));
    }

    private void sellItem(Cargo cargo) {
        m_market.getCommodities().stream()
                .filter(c -> c.getName().equals(cargo.getCommodity().getName()))
                .findFirst()
                .ifPresentOrElse(c -> existsItemOnMarket(c, cargo), () -> notExistsItemOnMarket(cargo));

        m_sellItems.add(cargo);
        m_cargos.remove(cargo);

        updatePlayer(cargo.getQuantityOfCommodity() * cargo.getCommodity().getUnitSellPrice());
    }

    private void notExistsItemOnMarket(Cargo cargo) {
        cargo.getCommodity().setCurrentSupplyAmount(cargo.getQuantityOfCommodity());
        m_market.addCommodity(cargo.getCommodity());
    }

    private void existsItemOnMarket(Commodity commodityOnMarket, Cargo cargo) {
        commodityOnMarket.setCurrentSupplyAmount(commodityOnMarket.getCurrentSupplyAmount() + cargo.getQuantityOfCommodity());
    }

    @Override
    public String toString() {
        var sb = new StringBuilder();
        sb.append("\n---------------SELL ITEM [").append(m_player.getName()).append("]---------------\n");
        sb.append(format(START_MESSAGE, m_player.getName(), initialMoney)).append("\n")
                .append(format(END_MESSAGE, m_player.getName(), m_player.getCurrentMoney()));
        m_sellItems.forEach(sb::append);
        sb.append("---------------SELL ITEM [").append(m_player.getName()).append("]---------------\n");
        return sb.toString();
    }
}