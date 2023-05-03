package nuricanozturk.dev.entity;

import project.gameengine.base.GameContext;

import java.util.ArrayList;
import java.util.List;

/*

    A spaceship has :
        • name,
        • buy price
        • a list of cargo,
        • capacity in terms of volume,
        • speed in terms of light year per turn,
        • current fuel
        • fuel capacity
        • fuel usage per lightyear

 */
public class SpaceShip implements GameContext
{
    private String m_name;
    private double m_price;
    private final List<Cargo> m_cargos;
    private int m_volumeCapacity;
    private double m_speed;
    private int m_currentFuel;
    private int m_fuelCapacity;
    private int fuelUsagePerLightYear;

    public SpaceShip()
    {
        m_cargos = new ArrayList<>();
    }

    public void addCargo(Cargo cargo)
    {
        m_cargos.add(cargo);
    }

    public List<Cargo> getSortedCargos()
    {
        return m_cargos.stream().sorted().toList();
    }

    public String getName()
    {
        return m_name;
    }

    public double getPrice()
    {
        return m_price;
    }

    public List<Cargo> getCargos()
    {
        return m_cargos;
    }

    public int getVolumeCapacity()
    {
        return m_volumeCapacity;
    }

    public double getSpeed()
    {
        return m_speed;
    }

    public int getCurrentFuel()
    {
        return m_currentFuel;
    }

    public int getFuelCapacity()
    {
        return m_fuelCapacity;
    }

    public int getFuelUsagePerLightYear()
    {
        return fuelUsagePerLightYear;
    }

    /**
     * Builder class for Spaceships
     */
    public static class Builder
    {
        private final SpaceShip m_spaceShip;

        public Builder()
        {
            m_spaceShip = new SpaceShip();
        }

        public Builder setName(String name)
        {
            m_spaceShip.m_name = name;
            return this;
        }

        public Builder setPrice(double price)
        {
            m_spaceShip.m_price = price;
            return this;
        }

        public Builder setVolumeCapacity(int volumeCapacity)
        {
            m_spaceShip.m_volumeCapacity = volumeCapacity;
            return this;
        }

        public Builder setSpeed(double speed)
        {
            m_spaceShip.m_speed = speed;
            return this;
        }

        public Builder setFuelCapacity(int fuelCapacity)
        {
            m_spaceShip.m_fuelCapacity = fuelCapacity;
            return this;
        }

        public Builder setFuelUsagePerLightYear(int fuelUsagePerLightYear)
        {
            m_spaceShip.fuelUsagePerLightYear = fuelUsagePerLightYear;
            return this;
        }

        public SpaceShip build()
        {
            return m_spaceShip;
        }
    }
}