package nuricanozturk.dev.factory;

import nuricanozturk.dev.entity.Market;

import static nuricanozturk.dev.factory.CommodityFactory.createCommodities;
import static nuricanozturk.dev.util.Constants.MARKET_NAME;

public final class MarketFactory
{
    private MarketFactory()
    {
    }

    public static Market createMarket()
    {
        //System.out.println("Market are created");
        return new Market(MARKET_NAME, createCommodities());
    }
}
