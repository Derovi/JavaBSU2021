package processed_collections;

import java.util.*;
import java.util.function.Function;

public class SortedCollection<T, F extends Comparable<F>> implements ProcessedCollection<T, T> {

    public SortedCollection(Comparator<? super T> comparator) {
        reversed = false;
        this.comparator = Optional.of(comparator);
        this.keyExtractor = Optional.empty();
    }

    public SortedCollection(Function<T, F> keyExtractor) {
        reversed = false;
        this.keyExtractor = Optional.of(keyExtractor);
        this.comparator = Optional.empty();
    }

    public SortedCollection(Function<T, F> keyExtractor, boolean reversed) {
        this.reversed = reversed;
        this.keyExtractor = Optional.of(keyExtractor);
        this.comparator = Optional.empty();
    }
    @Override
    public void renew(Collection<? extends T> elements) {
        this.elements = new ArrayList<>(elements);
        if(comparator.isPresent()) {
            Collections.sort(this.elements, comparator.get());
        } else if (keyExtractor.isPresent()) {
            Collections.sort(this.elements, Comparator.comparing((T t) -> keyExtractor.get().apply(t)));
        }
    }

    @Override
    public Collection<? extends T> currentState() {
        return elements;
    }

    private boolean reversed;
    private ArrayList<? extends T> elements;
    private Optional<Comparator<? super T>> comparator;
    private Optional<Function<T, F>> keyExtractor;
}