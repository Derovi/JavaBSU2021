package processed_collections;

import java.util.Collection;
import java.util.Optional;
import java.util.function.BinaryOperator;

public class ReducedCollection<T> implements FinalProcessedCollection<T, Optional<T>> {

    public ReducedCollection(BinaryOperator<T> reducer) {
        this.reducer = reducer;
    }

    @Override
    public void renew(Collection<? extends T> elements) {
        // бог знает почему он не хотел примернять BinaryOperator<T> к типу <? extends T>
        // но привести въявную к типу <T>, естественно, проблему решило
        reduced_collection = elements.isEmpty() ? Optional.empty() : ((Collection<T>)elements).stream().reduce(reducer);
    }

    @Override
    public Optional<T> currentState() {
        return reduced_collection;
    }

    Optional<T> reduced_collection;
    BinaryOperator<T> reducer;
}