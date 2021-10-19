package processed_collections;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MappedCollection<T, E> implements ProcessedCollection<T, E> {

    public MappedCollection(Function mapper) {
        this.mapper = mapper;
    }

    @Override
    public void renew(Collection<? extends T> elements) {
        this.elements = elements.stream().collect(Collectors.toMap((T el) -> el, mapper));
    }

    @Override
    public Collection<? extends E> currentState() {
        return elements.values();
    }

    private Map<T, E> elements;
    private Function<T, E> mapper;
}
