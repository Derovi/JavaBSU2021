package processed_collections;

import java.util.Collection;

public interface ProcessedCollection<T, E> extends
        FinalProcessedCollection<T, Collection<? extends E>> {
    default <F> ProcessedCollection<T, F> compose(ProcessedCollection<E, F> other) {
        ProcessedCollection<T, E> obj = this;
        return new ProcessedCollection<>() {
            @Override
            public void renew(Collection<? extends T> elements) {
                obj.renew(elements);
                other.renew(obj.currentState());
            }

            @Override
            public Collection<? extends F> currentState() {
                return other.currentState();
            }
        };
    }

    default <U> FinalProcessedCollection<T, U> compose(FinalProcessedCollection<E, U> other) {
        // f -  opt
        // t - t
        // e - coll t
        ProcessedCollection<T, E> obj = this;
        return new FinalProcessedCollection<>() {
            @Override
            public void renew(Collection<? extends T> elements) {
                obj.renew(elements);
                other.renew(obj.currentState());
            }

            @Override
            public U currentState() {
                return other.currentState();
            }
        };
    }
}
