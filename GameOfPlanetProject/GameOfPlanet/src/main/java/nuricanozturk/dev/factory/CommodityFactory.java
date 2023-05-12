package nuricanozturk.dev.factory;

import nuricanozturk.dev.entity.Commodity;

import java.util.List;
import java.util.stream.IntStream;

import static nuricanozturk.dev.config.RandomConfig.getRandomInstance;
import static nuricanozturk.dev.util.Constants.*;
import static nuricanozturk.dev.util.Util.LOGGER;
import static nuricanozturk.dev.util.Util.getBigFormattedNumber;

public final class CommodityFactory
{

    private CommodityFactory() {}

    public static List<Commodity> createCommodities()
    {
        //System.out.println("Commodities are created");
        return IntStream
                .range(0, getRandomInstance().nextInt(MIN_COMMODITY_COUNT, MAX_COMMODITY_COUNT))
                .mapToObj(CommodityFactory::createCommodity)
                .toList();
    }

    private static Commodity createCommodity(int i)
    {
        var commodity = new Commodity.Builder()
                .setName(COMMODITY_NAME.get(i))
                .setUnitVolume(getRandomInstance().nextInt(MIN_UNIT_VOLUME, MAX_UNIT_VOLUME))
                .setCurrentSupplyAmount(getRandomInstance().nextInt(MIN_CURRENT_SUPPLY_AMOUNT, MAX_CURRENT_SUPPLY_AMOUNT))
                .setUnitBuyPrice(getBigFormattedNumber(getRandomInstance().nextDouble(MIN_UNIT_BUY_PRICE, MAX_UNIT_BUY_PRICE)))
                .setDelayRatio(getBigFormattedNumber(getRandomInstance().nextDouble(MIN_DELAY_RATIO, MAX_DELAY_RATIO)))
                .build();
        LOGGER.log(commodity + " is created...");
        return commodity;
    }
}