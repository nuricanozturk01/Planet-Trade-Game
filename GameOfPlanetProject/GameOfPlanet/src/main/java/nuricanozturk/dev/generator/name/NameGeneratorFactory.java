package nuricanozturk.dev.generator.name;

public final class NameGeneratorFactory
{
    private static int NAME_COUNT = 1;

    public static String create(NameType nameType)
    {
        return nameType.getName() + "-" + NAME_COUNT++;
    }

    public static String create(NameType nameType, int count)
    {
        return nameType.getName() + "-" + count;
    }


}
