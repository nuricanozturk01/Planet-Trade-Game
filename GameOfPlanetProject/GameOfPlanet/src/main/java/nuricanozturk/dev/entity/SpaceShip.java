package nuricanozturk.dev.entity;

import project.gameengine.base.GameContext;

import java.util.ArrayList;
import java.util.List;


public class SpaceShip implements GameContext
{
    private final List<Cargo> m_cargos;
    private String m_name;
    private double m_price;
    private int m_volumeCapacity;
    private int m_speed;
    private int m_currentFuel;
    private int m_fuelCapacity;
    private int fuelUsagePerLightYear;

    public SpaceShip() {
        m_cargos = new ArrayList<>();
    }

    public void addCargo(Cargo cargo) {
        m_cargos.add(cargo);
    }

    public List<Cargo> getSortedCargos() {
        return m_cargos.stream().sorted().toList();
    }

    public String getName() {
        return m_name;
    }

    public double getPrice() {
        return m_price;
    }

    public List<Cargo> getCargos() {
        return m_cargos;
    }

    public int getVolumeCapacity() {
        return m_volumeCapacity;
    }

    public int getSpeed() {
        return m_speed;
    }

    public int getCurrentFuel() {
        return m_currentFuel;
    }

    public int getFuelCapacity() {
        return m_fuelCapacity;
    }

    public int getFuelUsagePerLightYear() {
        return fuelUsagePerLightYear;
    }

    /**
     * Builder class for Spaceships
     */
    public static class Builder {
        private final SpaceShip m_spaceShip;

        public Builder() {
            m_spaceShip = new SpaceShip();
        }

        public Builder setName(String name) {
            m_spaceShip.m_name = name;
            return this;
        }

        public Builder setPrice(double price) {
            m_spaceShip.m_price = price;
            return this;
        }

        public Builder setVolumeCapacity(int volumeCapacity) {
            m_spaceShip.m_volumeCapacity = volumeCapacity;
            return this;
        }

        public Builder setSpeed(int speed) {
            m_spaceShip.m_speed = speed;
            return this;
        }

        public Builder setFuelCapacity(int fuelCapacity) {
            m_spaceShip.m_fuelCapacity = fuelCapacity;
            return this;
        }
        public Builder setCurrentFuel(int currentFuel)
        {
            m_spaceShip.m_currentFuel = currentFuel;
            return this;
        }
        public Builder setFuelUsagePerLightYear(int fuelUsagePerLightYear) {
            m_spaceShip.fuelUsagePerLightYear = fuelUsagePerLightYear;
            return this;
        }
        public SpaceShip build() {
            return m_spaceShip;
        }
    }

    @Override
    public String toString() {
        return "SpaceShip{" +
                "m_cargos=" + m_cargos +
                ", m_name='" + m_name + '\'' +
                ", m_price=" + m_price +
                ", m_volumeCapacity=" + m_volumeCapacity +
                ", m_speed=" + m_speed +
                ", m_currentFuel=" + m_currentFuel +
                ", m_fuelCapacity=" + m_fuelCapacity +
                ", fuelUsagePerLightYear=" + fuelUsagePerLightYear +
                '}';
    }
}