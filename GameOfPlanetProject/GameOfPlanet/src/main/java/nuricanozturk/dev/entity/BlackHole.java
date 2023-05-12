package nuricanozturk.dev.entity;


import project.gameengine.base.GameContext;

import static nuricanozturk.dev.factory.GalaxyFactory.createGalaxy;
import static nuricanozturk.dev.util.Util.LOGGER;

public class BlackHole implements GameContext
{
    private final String m_name;


    public BlackHole(String name)
    {
        m_name = name;
    }

    public String getName()
    {
        return m_name;
    }

    public Galaxy explode()
    {
        LOGGER.log("Blackhole " + m_name + " is created....");
        return createGalaxy();
    }
}
