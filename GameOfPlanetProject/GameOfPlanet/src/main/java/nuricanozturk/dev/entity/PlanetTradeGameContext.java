package nuricanozturk.dev.entity;

import project.gameengine.base.GameContext;
import project.gameengine.base.Player;

import java.util.Collection;
import java.util.List;

import static java.lang.String.format;
import static nuricanozturk.dev.config.RandomConfig.getRandomInstance;
import static nuricanozturk.dev.util.Constants.*;
import static nuricanozturk.dev.util.Util.LOGGER;

@FunctionalInterface
interface ChangeSupplyAmount
{
    void changeSupplyAmount();
}

@FunctionalInterface
interface ChangePrice
{
    void changePrice();
}

public class PlanetTradeGameContext implements GameContext
{
    private final List<SpaceShip> m_spaceShips;
    private final Galaxy m_galaxy;
    private final List<Planet> m_planets;
    private final List<Player> m_players;
    private final StringBuilder gameStringBuilder;

    public PlanetTradeGameContext(List<SpaceShip> spaceShips, Galaxy galaxy, List<Planet> planets, List<Player> players)
    {
        m_players = players;
        m_spaceShips = spaceShips;
        m_galaxy = galaxy;
        m_planets = planets;
        gameStringBuilder = new StringBuilder();
    }

    public void init(List<Player> players)
    {
        //other init contexts on constructor
        players.forEach(p -> p.prepareForGame(this));
    }

    public List<SpaceShip> getSpaceShips()
    {
        return m_spaceShips;
    }

    public List<Planet> getPlanets()
    {
        return m_planets;
    }

    private void addRandomSuppliesToMarkets()
    {
        gameStringBuilder.append("Added random supplies to markets.....").append("\n");
    }

    private void changeSupplyAmountOnMarkets()
    {
        m_planets.stream().map(Planet::getMarket).forEach(this::changeSupplyAmountsOnMarket);
        gameStringBuilder.append("Supply amounts are changed.....").append("\n");
    }

    private void changeSupplyAmountsOnMarket(Market market)
    {
        // Anonymous class instead of Lambda notation
        market.getCommodities().forEach(commodity -> new ChangeSupplyAmount()
        {
            @Override
            public void changeSupplyAmount()
            {
                var isDecrease = getRandomInstance().nextBoolean();
                var currentAmount = commodity.getCurrentSupplyAmount();

                if (currentAmount > MIN_CURRENT_SUPPLY_AMOUNT && currentAmount < MAX_CURRENT_SUPPLY_AMOUNT)
                    commodity.setCurrentSupplyAmount(
                            isDecrease ? currentAmount - 1 : currentAmount + getRandomInstance().nextInt(3, 10));
                else if (currentAmount == MAX_CURRENT_SUPPLY_AMOUNT)
                    commodity.setCurrentSupplyAmount(currentAmount + getRandomInstance().nextInt(3, 10));

                else commodity.setCurrentSupplyAmount(currentAmount + getRandomInstance().nextInt(3, 10));
            }
        });
    }

    private void changeSupplyPricesOnMarkets()
    {
        m_planets.stream().map(Planet::getMarket).forEach(this::changePriceOnMarket);
        gameStringBuilder.append("Supply prices are changed.....").append("\n");
    }

    private void changePriceOnMarket(Market market)
    {
        market.getCommodities().forEach(commodity -> new ChangePrice()
                {
                    @Override
                    public void changePrice()
                    {
                        var currentBuyPrice = commodity.getUnitBuyPrice();
                        var currentSellPrice = commodity.getUnitSellPrice();

                        changeUnitBuyPrice(commodity, currentBuyPrice);
                        changeUnitSellPrice(commodity, currentSellPrice);
                    }

                    private void changeUnitSellPrice(Commodity commodity, double currentSellPrice)
                    {
                        var price = currentSellPrice - 100;
                        commodity.setUnitSellPrice(price <= 10 ? 20 : price);
                    }

                    private void changeUnitBuyPrice(Commodity commodity, double currentBuyPrice)
                    {
                        boolean isDecrease = getRandomInstance().nextBoolean();

                        if (currentBuyPrice >= MIN_COMMODITY_UNIT_BUY_PRICE + MIN_COMMODITY_INCREASE &&
                                currentBuyPrice <= MAX_COMMODITY_BUY_PRICE - MAX_COMMODITY_INCREASE)
                        {
                            commodity.setUnitBuyPrice(isDecrease ? currentBuyPrice - MIN_COMMODITY_INCREASE :
                                    currentBuyPrice + MAX_COMMODITY_INCREASE);
                        }
                        if (currentBuyPrice > MIN_COMMODITY_UNIT_BUY_PRICE)
                            commodity.setUnitBuyPrice(MIN_COMMODITY_UNIT_BUY_PRICE);

                        else commodity.setUnitBuyPrice(MAX_COMMODITY_BUY_PRICE);
                    }
                }
        );
    }

    public void updateGame()
    {
        gameStringBuilder.setLength(0);
        gameStringBuilder.append("\n\n---------------------------GAME UPDATE---------------------------\n");
        changeSupplyAmountOnMarkets();
        changeSupplyPricesOnMarkets();
        changeUnitFuelPricesOnPlanets();
        addRandomSuppliesToMarkets();
        decaySupplies();
        printPlayerStatus();
        gameStringBuilder.append("---------------------------GAME UPDATE\"---------------------------\n\n");
    }

    private void printPlayerStatus()
    {
        gameStringBuilder.append("\n-------- PLAYER STATUS --------\n");
        m_players.stream().map(p -> (PlayerImpl) p)
                .forEach(p -> gameStringBuilder.append(
                        format("Name: %s, CurrentMoney: $%.2f, Spaceship: %s [%d/%d], Current Planet: %s",
                                p.getName(), p.getCurrentMoney(), p.getSpaceShip().getName(),
                                p.getSpaceShip().getCurrentVolume(), p.getSpaceShip().getVolumeCapacity(),
                                p.getCurrentPlanet().getName())).append("\n"));
        gameStringBuilder.append("\n");
        gameStringBuilder.append("-------- PLAYER STATUS --------\n\n");
    }

    private void changeUnitFuelPricesOnPlanets()
    {
        var sb = new StringBuilder();

        m_planets.forEach(planet -> {
            var oldPrice = planet.getUnitFuelPrice();
            planet.changePrice();
            sb.append(format("Planet [%s] unit fuel price changed $%.2f to $%.2f\n", planet.getName(), oldPrice,
                    planet.getUnitFuelPrice()));
        });

        gameStringBuilder.append("Unit Fuel Prices are changed...\n");
        gameStringBuilder.append("\n-------- NEW PRICES OF FUEL --------\n");
        gameStringBuilder.append(sb);
        gameStringBuilder.append("-------- NEW PRICES OF FUEL --------\n\n");
    }

    private void decaySupplies()
    {
        m_players.stream().map(p -> (PlayerImpl) p).map(p -> p.getSpaceShip().getCargos())
                .flatMap(Collection::stream)
                .forEach(this::startDecay);

        removeDecayedSupplies();

        gameStringBuilder.append("updated supply amounts cause of decay.....").append("\n");
    }

    private void removeDecayedSupplies()
    {
        m_players.stream().map(p -> (PlayerImpl) p).map(p -> p.getSpaceShip().getCargos())
                .forEach(cargos -> cargos.removeIf(cargo -> cargo.getQuantityOfCommodity() <= 0));
    }

    private void startDecay(Cargo cargo)
    {
        var decayRatio = cargo.getCommodity().getDecayRatio();
        cargo.getCommodity().setCurrentSupplyAmount((int) (cargo.getQuantityOfCommodity() * (1 - decayRatio)));
        cargo.setQuantityOfCommodity(cargo.getCommodity().getCurrentSupplyAmount());
    }

    @Override
    public String toString()
    {
        LOGGER.log(gameStringBuilder.toString());
        return gameStringBuilder.toString();
    }
}