package nuricanozturk.dev.action;

import nuricanozturk.dev.entity.IVendable;

public class BuyFuel implements IAction
{
    @Override
    public void apply(IVendable vendable)
    {
        System.out.println("FUEL: ");
    }

    @Override
    public String toString()
    {
        return "FUEL";
    }
}
