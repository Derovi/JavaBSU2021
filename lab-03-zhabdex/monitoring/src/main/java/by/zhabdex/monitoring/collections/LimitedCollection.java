package by.zhabdex.monitoring.collections;

import java.util.Collection;

public class LimitedCollection<T> implements ProcessedCollection<T, T> {

    public LimitedCollection(long maxSize) {
        this.maxSize = maxSize;
    }

    @Override
    public void renew(Collection<? extends T> elements) {
        collection = elements.stream().limit(maxSize).toList();
    }

    @Override
    public Collection<? extends T> currentState() {
        return collection;
    }

    private final long maxSize;
    private Collection<? extends T> collection;
}
