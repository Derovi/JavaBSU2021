package processed_collections;

import java.util.Collection;

public interface ProcessedCollection<T, E> extends
        FinalProcessedCollection<T, Collection<? extends E>> {
    default ProcessedCollection<T, E> compose(ProcessedCollection<T, E> other) {
        ProcessedCollection<T, E> obj = this;
        return new ProcessedCollection<>() {
            @Override
            public void renew(Collection<? extends T> elements) {
                obj.renew(elements);
                other.renew((Collection<? extends T>) obj.currentState());
            }

            @Override
            public Collection<? extends E> currentState() {
                return other.currentState();
            }
        };
    }

    default <F> FinalProcessedCollection<T, F> compose(FinalProcessedCollection<T, F> other) {
        // f -  opt
        // t - t
        // e - coll t
        ProcessedCollection<T, E> obj = this;
        return new FinalProcessedCollection<>() {
            @Override
            public void renew(Collection<? extends T> elements) {
                obj.renew(elements);
                other.renew((Collection<? extends T>) obj.currentState());
            }

            @Override
            public F currentState() {
                return other.currentState();
            }
        };
    }
}
