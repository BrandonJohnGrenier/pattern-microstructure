package fm.pattern.microstructure.dsl;

@SuppressWarnings("unchecked")
abstract class AbstractDSL<T, C> {

    public T thatIs() {
        return (T) this;
    }

    public T thatAre() {
        return (T) this;
    }

    public T and() {
        return (T) this;
    }

    public abstract C build();

}
