package nuricanozturk.dev.entity;

import project.gameengine.base.GameContext;

import java.util.HashMap;
import java.util.Map;

import static nuricanozturk.dev.util.Constants.UNIT_FUEL_PRICE_INCREASE_RATE;

public class Planet implements GameContext
{
    private String m_name;
    private double m_unitFuelPrice;
    private double m_parkingPricePerTurn;
    private Market m_market;
    private Map<Planet, Integer> m_distanceMap;

    private Planet()
    {
        m_distanceMap = new HashMap<>();
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

    public Map<Planet, Integer> getDistanceMap()
    {
        return m_distanceMap;
    }

    public void setDistanceMap(Map<Planet, Integer> distanceMap)
    {
        m_distanceMap = distanceMap;
    }

    public double getParkingPricePerTurn()
    {
        return m_parkingPricePerTurn;
    }

    @Override
    public String toString()
    {
        return "Planet{" +
                "m_name='" + m_name + '\'' +
                ", m_unitFuelPrice=" + m_unitFuelPrice +
                ", m_parkingPricePerTurn=" + m_parkingPricePerTurn +
                ", m_market=" + m_market +
                '}';
    }

    public void changePrice()
    {
        m_unitFuelPrice += (m_unitFuelPrice * UNIT_FUEL_PRICE_INCREASE_RATE);
    }

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
}