package nuricanozturk.dev.entity;

import project.gameengine.base.GameContext;

public class Commodity implements GameContext
{
    private final String m_name;
    private final int m_unitVolume;
    private final double m_delayRatio;
    private final double m_currentSupplyAmount;
    private final double m_unitBuyPrice;
    private final double m_unitSellPrice;

    public Commodity(String name, int unitVolume, double delayRatio, double currentSupplyAmount,
                     double unitBuyPrice, double unitSellPrice)
    {
        m_name = name;
        m_unitVolume = unitVolume;
        m_delayRatio = delayRatio;
        m_currentSupplyAmount = currentSupplyAmount;
        m_unitBuyPrice = unitBuyPrice;
        m_unitSellPrice = unitSellPrice;
    }

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

    public double getCurrentSupplyAmount()
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
}