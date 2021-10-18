package processed_collections;

import java.util.*;

public class ReversedCollection<T> implements ProcessedCollection<T, T> {
    @Override
    public void renew(Collection<? extends T> elements) {
        this.elements = new ArrayList<>(elements);
        Collections.reverse(this.elements);
    }

    @Override
    public Collection<? extends T> currentState() {
        return elements;
    }

    ArrayList<? extends T> elements;
}