package nuricanozturk.dev.action;

import nuricanozturk.dev.entity.IVendable;
import project.gameengine.base.Action;

@FunctionalInterface
public interface IAction extends Action
{
    void apply(IVendable vendable);
}
