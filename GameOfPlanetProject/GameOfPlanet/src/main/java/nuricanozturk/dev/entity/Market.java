package nuricanozturk.dev.entity;

import project.gameengine.base.GameContext;

import java.util.HashMap;
import java.util.Map;

public class Market implements GameContext
{
    private final String m_name;
    private final Map<String, Commodity> m_commodities;
    public Market(String name)
    {
        m_commodities = new HashMap<>();
        m_name = name;
    }
    public void addCommodity(String name, Commodity commodity)
    {
        if (!m_commodities.containsKey(name))
            m_commodities.put(name, commodity);
    }
    public String getName()
    {
        return m_name;
    }

    public Map<String, Commodity> getCommodities()
    {
        return m_commodities;
    }
}