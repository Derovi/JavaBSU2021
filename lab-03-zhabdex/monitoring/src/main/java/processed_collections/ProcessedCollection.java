package processed_collections;

import java.lang.reflect.Field;
import java.util.Collection;

public interface ProcessedCollection<T, E> extends
        FinalProcessedCollection<T, Collection<? extends E>> {
    default ProcessedCollection<T, E> compose(ProcessedCollection<T, E> other) {
        return new ProcessedCollection<T, E>() {
            @Override
            public void renew(Collection<? extends T> elements) {
                other.renew(elements);
                this.renew((Collection<? extends T>) other.currentState());
            }

            @Override
            public Collection<? extends E> currentState() {
                return other.currentState();
            }
        };
    }

    default FinalProcessedCollection<T, E> compose(FinalProcessedCollection<T, E> other) {
        return new FinalProcessedCollection<T, E>() {
            @Override
            public void renew(Collection<? extends T> elements) {
                other.renew(elements);
                this.renew((Collection<? extends T>) other.currentState());
            }

            @Override
            public E currentState() {
                return other.currentState();
            }
        };
    }
}
