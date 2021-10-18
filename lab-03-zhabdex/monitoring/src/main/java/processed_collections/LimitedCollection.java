package processed_collections;

import java.util.Collection;

public class LimitedCollection<T> implements ProcessedCollection<T, T> {

    public LimitedCollection(int maxSize) {
        this.maxSize = maxSize;
    }
    @Override
    public void renew(Collection<? extends T> elements) {
        this.elements = elements.stream().toList().subList(0, this.maxSize);
    }

    @Override
    public Collection<? extends T> currentState() {
        return elements;
    }

    Collection<? extends T> elements;
    int maxSize;
}