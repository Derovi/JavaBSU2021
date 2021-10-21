package by.zhabdex.monitoring.collections;

import java.util.Collection;
import java.util.function.Predicate;

public class FilteredCollection<T> implements ProcessedCollection<T, T> {

    public FilteredCollection(Predicate<? super T> predicate) {
        this.predicate = predicate;
    }

    @Override
    public void renew(Collection<? extends T> elements) {
        this.collection = elements.stream().filter(predicate).toList();
    }

    @Override
    public Collection<? extends T> currentState() {
        return collection;
    }

    private final Predicate<? super T> predicate;
    private Collection<? extends T> collection;
}
