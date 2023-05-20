package nuricanozturk.dev.util.exception;

@FunctionalInterface
public interface ISupplier<R> {
    R get() throws Exception;
}