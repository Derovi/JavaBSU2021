package processed_collections;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Optional;
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
        this.elements = elements;
        if(comparator.isPresent()) {
            Collections.sort(Arrays.asList(), comparator.get());
        } else if (keyExtractor.isPresent()) {
            Collections.sort(Arrays.asList(), Comparator.comparing((T t) -> keyExtractor.get().apply(t)));
        }
    }

    @Override
    public Collection<? extends T> currentState() {
        return elements;
    }

    private boolean reversed;
    private Collection<? extends T> elements;
    private Optional<Comparator<? super T>> comparator;
    private Optional<Function<T, F>> keyExtractor;
}