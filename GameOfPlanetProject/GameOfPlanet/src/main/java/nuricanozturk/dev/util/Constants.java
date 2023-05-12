package nuricanozturk.dev.util;

import java.util.Arrays;
import java.util.List;

public final class Constants
{
    public static final String LOGGER_FILE_NAME = "log.txt";
    public static final String LOGGER_SYSTEM_FILE_NAME = "sys.txt";
    public static final int MIN_PLAYER = 2;
    public static final int MAX_PLAYER = 5;
    public static final double MIN_UNIT_FUEL_PRICE = 5D;
    public static final double MAX_UNIT_FUEL_PRICE = 50D;
    public static final double MIN_TURN_PARKING_PRICE = 10D;
    public static final double MAX_TURN_PARKING_PRICE = 30D;
    public static final double PLAYER_INITIAL_MONEY = 30_000D;
    public static final double MIN_SPACESHIP_COST = 3_500D;
    public static final double MAX_SPACESHIP_COST = 50_000D;
    public static final int MIN_SPACESHIP_COUNT = 10;
    public static final int MAX_SPACESHIP_COUNT = 20;
    public static final int MIN_COMMODITY_COUNT = 15;
    public static final int MAX_COMMODITY_COUNT = 25;
    public static final int MAX_PLANET_SIZE = 10;
    public static final int MIN_PLANET_SIZE = 2;
    public static final int MIN_UNIT_VOLUME = 5;
    public static final int MAX_UNIT_VOLUME = 15;
    public static final double MIN_DELAY_RATIO = 0.5;
    public static final double MAX_DELAY_RATIO = 1.5D;
    public static final int MIN_CURRENT_SUPPLY_AMOUNT = 5;
    public static final int MAX_CURRENT_SUPPLY_AMOUNT = 30;
    public static final double MIN_UNIT_BUY_PRICE = 150D;
    public static final double MAX_UNIT_BUY_PRICE = 1500D;
    public static final int DEFAULT_INIT_FUEL_CAPACITY = 500;
    public static final int MIN_FUEL_CAPACITY = 1000;
    public static final int MAX_FUEL_CAPACITY = 2000;
    public static final int MAX_SPEED = 1500;
    public static final int MIN_SPEED = 100;
    public static final int MIN_VOLUME = 500;
    public static final int MAX_VOLUME = 1000;
    public static final String MARKET_NAME = "MARKET";
    public static final List<String> COMMODITY_NAME =
            Arrays.asList("Lithium", "Titanium", "Platinum", "Gold", "Silver", "Helium-3", "Water", "Food", "Oxygen",
                    "Hydrogen",
                    "Carbon", "Iron", "Copper", "Nickel", "Aluminum", "Tungsten", "Palladium", "Rhodium", "Iridium",
                    "Cobalt",
                    "Mercury", "Lead", "Zinc", "Tin", "Beryllium", "Uranium", "Thorium", "Plutonium", "Neptunium",
                    "Radium",
                    "Neon", "Argon", "Krypton", "Xenon", "Nitrogen", "Carbon Dioxide", "Methane", "Ammonia", "Sulfur",
                    "Chlorine",
                    "Sodium", "Potassium", "Magnesium", "Calcium", "Phosphorus", "Selenium", "Fluorine", "Bromine",
                    "Iodine", "Lithium",
                    "Rubidium", "Cesium", "Boron", "Silicon", "Germanium", "Arsenic", "Antimony", "Bismuth", "Cadmium",
                    "Gallium",
                    "Indium", "Thallium", "Lanthanum", "Cerium", "Praseodymium", "Neodymium", "Promethium", "Samarium",
                    "Europium", "Gadolinium",
                    "Terbium", "Dysprosium", "Holmium", "Erbium", "Thulium", "Ytterbium", "Lutetium", "Hafnium",
                    "Tantalum", "Niobium",
                    "Rhenium", "Osmium", "Ruthenium", "Technetium", "Molybdenum", "Boron Nitride", "Graphene",
                    "Fullerenes", "Carbon Nanotubes", "Quantum Dots");

    private Constants()
    {
    }
}
