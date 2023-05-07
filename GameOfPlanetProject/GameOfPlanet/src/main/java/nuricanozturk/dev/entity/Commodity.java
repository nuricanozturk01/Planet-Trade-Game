package nuricanozturk.dev.entity;

import project.gameengine.base.GameContext;

public class Commodity implements GameContext
{
    private String m_name;
    private int m_unitVolume;
    private double m_delayRatio;
    private int m_currentSupplyAmount;
    private double m_unitBuyPrice;
    private double m_unitSellPrice;

    private Commodity() {}

    public String getName()
    {
        return m_name;
    }

    public int getUnitVolume()
    {
        return m_unitVolume;
    }

    public double getDelayRatio()
    {
        return m_delayRatio;
    }

    public int getCurrentSupplyAmount()
    {
        return m_currentSupplyAmount;
    }

    public double getUnitBuyPrice()
    {
        return m_unitBuyPrice;
    }

    public double getUnitSellPrice()
    {
        return m_unitSellPrice;
    }

    @Override
    public String toString()
    {
        return "Commodity{" +
                "m_name='" + m_name + '\'' +
                ", m_unitVolume=" + m_unitVolume +
                ", m_delayRatio=" + m_delayRatio +
                ", m_currentSupplyAmount=" + m_currentSupplyAmount +
                ", m_unitBuyPrice=" + m_unitBuyPrice +
                ", m_unitSellPrice=" + m_unitSellPrice +
                '}';
    }

    public static class Builder
    {
        private final Commodity m_commodity;

        public Builder()
        {
            m_commodity = new Commodity();
        }

        public Builder setName(String name)
        {
            m_commodity.m_name = name;
            return this;
        }

        public Builder setUnitVolume(int unitVolume)
        {
            m_commodity.m_unitVolume = unitVolume;
            return this;
        }

        public Builder setDelayRatio(double delayRatio)
        {
            m_commodity.m_delayRatio = delayRatio;
            return this;
        }

        public Builder setCurrentSupplyAmount(int supplyAmount)
        {
            m_commodity.m_currentSupplyAmount = supplyAmount;
            return this;
        }

        public Builder setUnitBuyPrice(double unitBuyPrice)
        {
            m_commodity.m_unitBuyPrice = unitBuyPrice;
            m_commodity.m_unitSellPrice = m_commodity.m_unitBuyPrice + 500;
            return this;
        }

        public Commodity build()
        {
            return m_commodity;
        }
    }
}