package nuricanozturk.dev.factory;

import nuricanozturk.dev.entity.Market;

import static nuricanozturk.dev.factory.CommodityFactory.createCommodities;
import static nuricanozturk.dev.util.Constants.MARKET_NAMES;
import static nuricanozturk.dev.util.ExceptionUtil.handleException;

public final class MarketFactory
{
    private MarketFactory()
    {
    }


    public static Market createMarket()
    {
        return handleException(MarketFactory::create, NotCreatedException.class,
                "Market and commodities are not Created...");
    }

    private static Market create()
    {
        return new Market(MARKET_NAMES.next(), createCommodities());
    }
}
