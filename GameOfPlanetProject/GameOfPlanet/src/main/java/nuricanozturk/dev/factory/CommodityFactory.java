package nuricanozturk.dev.factory;


import nuricanozturk.dev.entity.Commodity;

import java.util.List;
import java.util.stream.IntStream;

import static nuricanozturk.dev.config.RandomConfig.getRandomInstance;
import static nuricanozturk.dev.util.Constants.*;

public final class CommodityFactory
{

    private CommodityFactory()
    {
    }

    public static List<Commodity> createCommodities()
    {
        return IntStream
                .range(0, getRandomInstance().nextInt(MIN_COMMODITY_COUNT, MAX_COMMODITY_COUNT))
                .mapToObj(CommodityFactory::createCommodity)
                .toList();
    }

    private static Commodity createCommodity(int i)
    {
        return new Commodity(COMMODITY_NAME.get(i),
                getRandomInstance().nextInt(MIN_UNIT_VOLUME, MAX_UNIT_VOLUME),
                getRandomInstance().nextDouble(MIN_DELAY_RATIO, MAX_DELAY_RATIO),
                getRandomInstance().nextInt(MIN_CURRENT_SUPPLY_AMOUNT, MAX_CURRENT_SUPPLY_AMOUNT),
                getRandomInstance().nextDouble(MIN_UNIT_BUY_PRICE, MAX_UNIT_BUY_PRICE));
    }
}
