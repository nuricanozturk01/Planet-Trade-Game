package nuricanozturk.dev.entity;

import project.gameengine.base.GameContext;

public class Planet implements GameContext
{
    private String m_name;
    private double m_unitFuelPrice;
    private double m_parkingPricePerTurn;
    private Market m_market;

    private Planet() {}

    public static class Builder
    {
        private final Planet m_planet;

        public Builder()
        {
            m_planet = new Planet();
        }

        public Builder setName(String name)
        {
            m_planet.m_name = name;
            return this;
        }

        public Builder setUnitFuelPrice(double unitFuelPrice)
        {
            m_planet.m_unitFuelPrice = unitFuelPrice;
            return this;
        }
        public Builder setParkingPricePerTurn(double parkingPricePerTurn)
        {
            m_planet.m_parkingPricePerTurn = parkingPricePerTurn;
            return this;
        }
        public Builder setMarket(Market market)
        {
            m_planet.m_market = market;
            return this;
        }

        public Planet build()
        {
            return m_planet;
        }
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