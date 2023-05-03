package nuricanozturk.dev.entity;


import project.gameengine.base.GameContext;


import java.util.logging.Logger;

import static nuricanozturk.dev.factory.GalaxyFactory.createGalaxy;

public class BlackHole implements GameContext
{
    Logger m_logger = Logger.getLogger(BlackHole.class.getName());
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
        m_logger.info("safsaf");
        System.exit(1);
        return createGalaxy();

    }
}
