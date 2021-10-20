package processed_collections;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MappedCollection<T, E> implements ProcessedCollection<T, E> {

    public MappedCollection(Function<T, E> mapper) {
        this.mapper = mapper;
    }

    @Override
    public void renew(Collection<? extends T> elements) {
        this.elements = elements.stream().map(mapper).collect(Collectors.toList());
    }

    @Override
    public Collection<? extends E> currentState() {
        return elements;
    }

    private Collection<E> elements;
    private Function<T, E> mapper;
}
