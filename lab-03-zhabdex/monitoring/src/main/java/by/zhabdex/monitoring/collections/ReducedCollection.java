package by.zhabdex.monitoring.collections;

import java.util.Collection;
import java.util.Optional;
import java.util.function.BinaryOperator;

public class ReducedCollection<T> implements FinalProcessedCollection<T, Optional<? extends T>> {

    public ReducedCollection(BinaryOperator<T> reducer) {
        this.reducer = reducer;
    }

    @Override
    public void renew(Collection<? extends T> elements) {
        this.element = elements.stream().map(x -> (T) x).reduce(reducer);
    }

    @Override
    public Optional<? extends T> currentState() {
        return element;
    }

    private final BinaryOperator<T> reducer;
    private Optional<? extends T> element;
}
