package nuricanozturk.dev.action;

import nuricanozturk.dev.entity.IVendable;

public class BuyItem implements IAction
{

    @Override
    public void apply(IVendable vendable)
    {
        System.out.println("BUY ITEM");
    }

    @Override
    public String toString()
    {
        return "BUY ITEM";
    }
}
