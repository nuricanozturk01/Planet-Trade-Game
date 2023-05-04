package nuricanozturk.dev.entity;

import project.gameengine.base.GameContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Market implements GameContext
{
    private final String m_name;
    private List<Commodity> m_commodities;
    public Market(String name)
    {
        m_commodities = new ArrayList<>();
        m_name = name;
    }

    public Market(String name, List<Commodity> commodities)
    {
        m_commodities = commodities;
        m_name = name;
    }
    public void addCommodity(Commodity commodity)
    {
        m_commodities.add(commodity);
    }
    public void setCommodities(List<Commodity> commodities)
    {
        m_commodities = commodities;
    }
    public String getName()
    {
        return m_name;
    }

    public List<Commodity> getCommodities()
    {
        return m_commodities;
    }
}