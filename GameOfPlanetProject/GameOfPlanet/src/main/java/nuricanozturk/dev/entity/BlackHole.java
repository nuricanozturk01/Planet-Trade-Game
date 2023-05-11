package nuricanozturk.dev.entity;


import project.gameengine.base.GameContext;

import static nuricanozturk.dev.factory.GalaxyFactory.createGalaxy;

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
       // System.out.println("Blackhole is created...");
        return createGalaxy();
    }
}
