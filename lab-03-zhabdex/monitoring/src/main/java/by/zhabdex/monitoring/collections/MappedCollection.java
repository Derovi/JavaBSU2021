package by.zhabdex.monitoring.collections;

import java.util.Collection;
import java.util.function.Function;

public class MappedCollection<T, E> implements ProcessedCollection<T, E> {

    public MappedCollection(Function<? super T, E> function) {
        this.mapper = function;
    }

    @Override
    public void renew(Collection<? extends T> elements) {
        this.elements = elements.stream().map(mapper).toList();
    }

    @Override
    public Collection<? extends E> currentState() {
        return elements;
    }

    private Collection<? extends E> elements;
    private final Function<? super T, ? extends E> mapper;
}
