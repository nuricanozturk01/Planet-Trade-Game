package nuricanozturk.dev.action;

import nuricanozturk.dev.entity.IVendable;

public class SoldItem implements IAction
{
    @Override
    public void apply(IVendable vendable)
    {
        System.out.println("SOLD ITEM");
    }

    @Override
    public String toString()
    {
        return "SOLD";
    }
}
