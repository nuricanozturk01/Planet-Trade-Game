package nuricanozturk.dev.entity;

import project.gameengine.base.GameContext;
import project.gameengine.base.Player;

import java.util.Collection;
import java.util.List;

public class PlanetTradeGameContext implements GameContext {
    private final List<SpaceShip> m_spaceShips;
    private final Galaxy m_galaxy;
    private final List<Planet> m_planets;
    private final List<Player> m_players;

    public PlanetTradeGameContext(List<SpaceShip> spaceShips, Galaxy galaxy, List<Planet> planets, List<Player> players) {
        m_players = players;
        m_spaceShips = spaceShips;
        m_galaxy = galaxy;
        m_planets = planets;
    }

    public void init(List<Player> players) {
        //other init contexts on constructor
        players.forEach(p -> p.prepareForGame(this));
    }

    public List<SpaceShip> getSpaceShips() {
        return m_spaceShips;
    }

    public Galaxy getGalaxy() {
        return m_galaxy;
    }

    public List<Planet> getPlanets() {
        return m_planets;
    }

    public void updateTurn()
    {
        System.out.println("UPDATED TURN");
       /* updateCargos();
        changeSupplyPricesOnMarkets();
        changeSupplyAmountOnMarkets();
        addRandomSuppliesToMarkets();*/
    }

    private void addRandomSuppliesToMarkets()
    {

    }

    private void changeSupplyAmountOnMarkets() {

    }

    private void changeSupplyPricesOnMarkets() {

    }

    /*
        The current cargo of each player are decayed.
        (the amount of each commodity in the cargo are reduced by decay ratio of that commodity)
        Amount = Amount*(1-Decay Ratio)
     */
    private void updateCargos() {
        m_players.stream().map(p -> (PlayerImpl) p)
                .map(PlayerImpl::getSpaceShip)
                .map(SpaceShip::getCargos)
                .flatMap(Collection::stream)
                .peek(this::startDecay)
                .filter(c -> c.getQuantityOfCommodity() > 0)
                .toList();
    }

    private void startDecay(Cargo cargo) {
        var decayRatio = cargo.getCommodity().getDecayRatio();
        cargo.getCommodity().setCurrentSupplyAmount((int) (cargo.getQuantityOfCommodity() * (1 - decayRatio)));
        cargo.setQuantityOfCommodity(cargo.getCommodity().getCurrentSupplyAmount());
    }
}