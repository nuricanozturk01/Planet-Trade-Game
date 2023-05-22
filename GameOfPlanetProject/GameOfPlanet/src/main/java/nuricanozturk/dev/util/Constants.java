package nuricanozturk.dev.util;

import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;

public final class Constants
{


    // ############################## LIVING_CONDITIONS START ##############################
    public static final double UNIT_FUEL_PRICE_INCREASE_RATE = .05;
    public static final double MIN_SPACESHIP_COST = 3_500D;
    public static final double MAX_SPACESHIP_COST = 30_000D;
    public static final double MIN_UNIT_FUEL_PRICE = 2D;
    public static final double MAX_UNIT_FUEL_PRICE = 5D;
    public static final double MIN_TURN_PARKING_PRICE = 2D;
    public static final double MAX_TURN_PARKING_PRICE = 10D;
    public static final double MIN_COMMODITY_UNIT_BUY_PRICE = 1500D;
    public static final double MAX_COMMODITY_BUY_PRICE = 5_500D;

    // ############################## LIVING_CONDITIONS END ##############################


    // ############################## LOGGER START ##############################
    public static final String LOGGER_FILE_NAME = "log.txt";
    // ############################## LOGGER END ##############################


    // ############################## GALAXY START ##############################
    public static final LinkedList<String> GALAXY_NAMES = new LinkedList<>(
            asList("Andromeda", "Milky Way", "Triangulum", "Centaurus A", "Whirlpool", "Sombrero", "Pinwheel", "Cigar",
                    "Bode's", "Cartwheel")
    );

    // ############################## GALAXY END ##############################


    // ############################## PLANET DISTANCE START ##############################
    public static int MIN_DISTANCE_BETWEEN_PLANETS = 10;
    public static int MAX_DISTANCE_BETWEEN_PLANETS = 100;
    // ############################## PLANET DISTANCE END ##############################


    // ############################## BLACKHOLE START ##############################
    public static final LinkedList<String> BLACKHOLE_NAMES = new LinkedList<>(
            Arrays.asList("Sagittarius A*", "M87", "Cygnus X-1", "V404 Cygni", "GRO J1655-40", "NGC 1365", "NGC 1277",
                    "A0620-00", "IC 310", "PKS B1424-418")
    );
    // ############################## PLAYER START ##############################
    public static final int MIN_PLAYER = 1;

    public static final int MAX_PLAYER = 5;

    public static final double PLAYER_INITIAL_MONEY = 60_000D;

    // ############################## PLAYER END ##############################


    // ############################## SPACESHIP START ##############################
    public static final int MIN_SPACESHIP_COUNT = 10;
    public static final int MAX_SPACESHIP_COUNT = 30;
    public static final int DEFAULT_INIT_FUEL_CAPACITY = 500;
    public static final int MIN_FUEL_CAPACITY = 10_000;
    public static final int MAX_FUEL_CAPACITY = 50_000;
    public static final int MAX_SPEED = 1500;
    public static final int MIN_SPEED = 100;
    public static final int MIN_VOLUME = 3500;
    public static final int MAX_VOLUME = 8500;
    public static final LinkedList<String> SPACESHIP_NAMES = new LinkedList<>(
            asList("Apollo", "Discovery", "Enterprise", "Falcon", "Galaxy", "Hermes", "Infinity", "Jupiter",
                    "Nova", "Orion", "Pioneer", "Raptor", "Serenity", "Titan", "Voyager", "Zenith", "Aurora",
                    "Celestial", "Cosmos", "Eclipse", "Firefly", "Goliath", "Hawkeye", "Interceptor", "Liberty",
                    "Meteor", "Nebula", "Odyssey", "Pathfinder", "Quantum", "Ranger", "Stardust", "Trident",
                    "Utopia", "Vanguard", "Wanderer", "Xenon", "Yamato", "Zenon", "Aegis", "Blackhawk", "Comet",
                    "Defiant", "Endeavor", "Fury", "Genesis", "Horizon", "Intrepid", "Javelin", "Kestrel",
                    "Liberator", "Magellan", "Nimbus", "Omega", "Phoenix", "Quest", "Radiant", "Sentinel",
                    "Tempest", "Unity", "Valiant", "Warp", "Xplorer", "Yonder", "Zeus", "Astro", "Blaze",
                    "Cosmic", "Dragonfly", "Equinox", "Hawk", "Jetstream", "Lunar", "Meteorite", "Nebula",
                    "Orbit", "Pulsar", "Quasar", "Rocket", "Stellar", "Traverse", "Vortex", "Warp", "Xenith",
                    "Yosemite", "Zero-G"));

    // ############################## SPACESHIP END ##############################


    // ############################## PLANET START ##############################
    public static final int MAX_PLANET_SIZE = 10;
    public static final int MIN_PLANET_SIZE = 2;
    public static final LinkedList<String> PLANET_NAMES = new LinkedList<>(
            asList("Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune",
                    "Pluto", "Eris", "Haumea", "Makemake", "Ceres", "Orcus", "Quaoar", "Sedna", "Varuna",
                    "Ixion", "Chaos", "Nessus", "Huya", "Salacia", "Gonggong", "Varda", "Tyche"));

    // ############################## PLANET END ##############################





    // ############################## COMMODITY START ##############################
    public static final int MIN_COMMODITY_INCREASE = 2;
    public static final int MAX_COMMODITY_INCREASE = 5;
    public static final int MIN_COMMODITY_COUNT = 10;
    public static final int MAX_COMMODITY_COUNT = 20;
    public static final int MIN_UNIT_VOLUME = 50;
    public static final int MAX_UNIT_VOLUME = 100;
    public static final int MIN_CURRENT_SUPPLY_AMOUNT = 5;
    public static final int MAX_CURRENT_SUPPLY_AMOUNT = 20;
    public static final double MAX_DECAY_RATIO = .7D;
    public static final double MIN_DECAY_RATIO = .2D;
    public static final List<String> COMMODITY_NAME = asList("Lithium", "Titanium", "Platinum", "Gold",
            "Silver", "Helium-3", "Water", "Food", "Oxygen", "Hydrogen",
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


    // ############################## COMMODITY END ##############################


    // ############################## MARKET START ##############################
    public static final LinkedList<String> MARKET_NAMES = new LinkedList<>(
            asList("Alpha Market", "Beta Bazaar", "Gamma Mart", "Delta Trade", "Epsilon Emporium", "Zeta Shop",
                    "Eta Exchange", "Theta Mall", "Iota Depot", "Kappa Store", "Lambda Outlet", "Mu Market",
                    "Nu Boutique", "Xi Superstore", "Omicron Market", "Pi Plaza", "Rho Market", "Sigma Mart",
                    "Tau Trade", "Upsilon Shop", "Phi Bazaar", "Chi Mall", "Psi Store", "Omega Emporium",
                    "Aurora Market", "Celestial Bazaar", "Stellar Mart", "Cosmos Trade", "Galaxy Emporium",
                    "Nebula Shop", "Solar Exchange", "Lunar Mall", "Meteor Depot", "Supernova Store",
                    "Voyager Market", "Starship Bazaar", "Infinity Mart", "Galactic Trade", "Zenith Emporium",
                    "Astro Shop", "Nova Outlet", "Orion Market", "Pioneer Plaza", "Mercury Market", "Venus Bazaar",
                    "Mars Mart", "Jupiter Trade", "Saturn Shop", "Uranus Mall", "Neptune Depot", "Pluto Store",
                    "Cosmic Market", "Nebula Bazaar", "Stardust Mart", "Comet Trade", "Meteorite Shop",
                    "Quasar Mall", "Asteroid Depot", "Galaxy Store", "Starlight Market", "Luminous Bazaar",
                    "Eclipse Mart", "Solar Trade", "Lunar Shop", "Celestial Mall", "Infinity Depot",
                    "Galactic Store", "Zenith Market", "Aurora Bazaar", "Cosmos Mart", "Stellar Trade",
                    "Supernova Shop", "Voyager Mall", "Astro Market", "Nova Bazaar", "Orion Mart", "Pioneer Trade",
                    "Mercury Shop", "Venus Mall", "Mars Depot", "Jupiter Store", "Saturn Market", "Uranus Bazaar",
                    "Neptune Mart", "Pluto Trade", "Nebula Shop", "Stardust Mall", "Comet Depot", "Meteorite Store")
    );
    // ############################## MARKET END ##############################

    private Constants()
    {
    }
}
