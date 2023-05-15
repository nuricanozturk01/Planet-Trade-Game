package nuricanozturk.dev.entity;

import java.util.Collection;

public interface ISpaceship
{
    void addCargo(Cargo cargo);
    void addAllCargo(Collection<? extends Cargo> cargos);
}
