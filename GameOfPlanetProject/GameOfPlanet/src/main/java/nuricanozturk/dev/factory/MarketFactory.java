package nuricanozturk.dev.factory;

import nuricanozturk.dev.entity.Market;
import nuricanozturk.dev.util.exception.NotCreatedException;

import static nuricanozturk.dev.factory.CommodityFactory.createCommodities;
import static nuricanozturk.dev.util.Constants.MARKET_NAMES;
import static nuricanozturk.dev.util.exception.ExceptionUtil.handleException;

public final class MarketFactory
{
    private MarketFactory()
    {
    }


    public static Market createMarket()
    {
        return handleException(MarketFactory::create, "Market and commodities are not Created...",
                NotCreatedException.class);
    }

    private static Market create()
    {
        return new Market(MARKET_NAMES.next(), createCommodities());
    }
}
