package nuricanozturk.dev.entity;


import nuricanozturk.dev.util.logger.ILogger;
import nuricanozturk.dev.util.logger.Logger;
import nuricanozturk.dev.util.logger.LoggerType;
import project.gameengine.base.GameContext;

import static nuricanozturk.dev.factory.GalaxyFactory.createGalaxy;
import static nuricanozturk.dev.util.logger.Logger.getLoggerInstance;

public class BlackHole implements GameContext
{
    private final String m_name;
    private final ILogger m_logger = getLoggerInstance().getLogger(LoggerType.FILE);

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
        m_logger.log("Blackhole " + m_name + " is created....");
        return createGalaxy();
    }
}
