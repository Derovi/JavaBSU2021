package by.zhabdex.monitoring.collections;

import java.util.Collection;
import java.util.Comparator;
import java.util.function.Function;

public class SortedCollection<T> implements ProcessedCollection<T, T> {

    public SortedCollection(Comparator<? super T> comparator) {
        this.comparator = comparator;
    }

    public SortedCollection(Function<? super T, Comparable> keyExtractor) {
        this(keyExtractor, false);
    }

    public SortedCollection(Function<? super T, Comparable> keyExtractor,
                            boolean reverse) {
        this.comparator = (a, b) -> reverse ?
                keyExtractor.apply(a).compareTo(keyExtractor.apply(b)) :
                -keyExtractor.apply(a).compareTo(keyExtractor.apply(b));
    }

    @Override
    public void renew(Collection<? extends T> elements) {
        this.collection = elements.stream().sorted(comparator).toList();
    }

    @Override
    public Collection<? extends T> currentState() {
        return collection;
    }

    private final Comparator<? super T> comparator;
    Collection<? extends T> collection;
}
