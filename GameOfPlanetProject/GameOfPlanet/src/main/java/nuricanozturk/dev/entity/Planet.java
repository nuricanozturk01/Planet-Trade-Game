package nuricanozturk.dev.entity;

import project.gameengine.base.GameContext;

public class Planet implements GameContext
{
    private final String m_name;
    private final double m_unitFuelPrice;
    private final double m_parkingPricePerTurn; // For spaceship
    private final Market m_market;

    public Planet(String name, double unitFuelPrice, double parkingPricePerTurn, Market market)
    {
        m_name = name;
        m_unitFuelPrice = unitFuelPrice;
        m_market = market;
        m_parkingPricePerTurn = parkingPricePerTurn;
    }

    public String getName()
    {
        return m_name;
    }

    public Market getMarket()
    {
        return m_market;
    }

    public double getUnitFuelPrice()
    {
        return m_unitFuelPrice;
    }

    public double getParkingPricePerTurn()
    {
        return m_parkingPricePerTurn;
    }
}