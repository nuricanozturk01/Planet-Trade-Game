package nuricanozturk.dev.entity;

import project.gameengine.base.GameContext;

public class Commodity implements GameContext, Comparable<Commodity>
{
    private String m_name;
    private int m_unitVolume;
    private double m_decayRatio;
    private int m_currentSupplyAmount;
    private double m_unitBuyPrice;
    private double m_unitSellPrice;

    private Commodity()
    {
    }

    public String getName()
    {
        return m_name;
    }

    public int getUnitVolume()
    {
        return m_unitVolume;
    }

    public double getDecayRatio()
    {
        return m_decayRatio;
    }

    public int getCurrentSupplyAmount()
    {
        return m_currentSupplyAmount;
    }

    public void setCurrentSupplyAmount(int currentSupplyAmount)
    {
        m_currentSupplyAmount = currentSupplyAmount;
    }

    public double getUnitBuyPrice()
    {
        return m_unitBuyPrice;
    }

    public void setUnitBuyPrice(double m_unitBuyPrice)
    {
        this.m_unitBuyPrice = m_unitBuyPrice;
    }

    public double getUnitSellPrice()
    {
        return m_unitSellPrice;
    }

    public void setUnitSellPrice(double m_unitSellPrice)
    {
        this.m_unitSellPrice = m_unitSellPrice;
    }

    @Override
    public String toString()
    {
        return "Commodity{" +
                "m_name='" + m_name + '\'' +
                ", m_unitVolume=" + m_unitVolume +
                ", m_decayRatio=" + m_decayRatio +
                ", m_currentSupplyAmount=" + m_currentSupplyAmount +
                ", m_unitBuyPrice=" + m_unitBuyPrice +
                ", m_unitSellPrice=" + m_unitSellPrice +
                '}';
    }

    @Override
    public int compareTo(Commodity o)
    {
        return Double.compare(m_unitSellPrice, o.getUnitSellPrice());
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

        public Builder setDecayRatio(double decayRatio)
        {
            m_commodity.m_decayRatio = decayRatio;
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
            var p = m_commodity.m_unitBuyPrice - 100;
            m_commodity.m_unitSellPrice = p <= 10 ? 20 : p;
            return this;
        }

        public Commodity build()
        {
            return m_commodity;
        }
    }
}