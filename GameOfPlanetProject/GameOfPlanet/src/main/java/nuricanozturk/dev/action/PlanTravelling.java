package nuricanozturk.dev.action;

import nuricanozturk.dev.entity.IVendable;

public class PlanTravelling implements IAction
{
    @Override
    public void apply(IVendable vendable)
    {
        System.out.println("Travel");
    }

    @Override
    public String toString()
    {
        return "TRAVEL";
    }
}
