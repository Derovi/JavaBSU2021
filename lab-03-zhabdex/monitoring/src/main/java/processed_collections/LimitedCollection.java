package processed_collections;

import java.util.Collection;
import java.util.stream.Collectors;

public class LimitedCollection<T> implements ProcessedCollection<T, T> {

    public LimitedCollection(int maxSize) {
        this.maxSize = maxSize;
    }
    @Override
    public void renew(Collection<? extends T> elements) {
        this.elements = elements.stream().limit(maxSize).collect(Collectors.toList());
    }

    @Override
    public Collection<? extends T> currentState() {
        return elements;
    }

    private Collection<? extends T> elements;
    private int maxSize;
}