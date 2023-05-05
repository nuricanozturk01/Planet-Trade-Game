package nuricanozturk.dev.generator.name;

public enum NameType
{
    BlackHole("BlackHole"), Galaxy("G"), Planet("P"), Market("M"), Commodity("C"), SpaceShip("SPACESHIP");
    private final String m_name;
    NameType(String name)
    {
        m_name = name;
    }

    public String getName()
    {
        return m_name;
    }
}
