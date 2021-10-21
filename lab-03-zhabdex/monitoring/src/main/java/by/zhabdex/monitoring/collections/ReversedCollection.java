package by.zhabdex.monitoring.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ReversedCollection<T> implements ProcessedCollection<T, T> {

    public ReversedCollection() {}

    @Override
    public void renew(Collection<? extends T> elements) {
        collection = new ArrayList(elements.stream().toList());
        Collections.reverse(collection);
    }

    @Override
    public Collection<? extends T> currentState() {
        return collection;
    }

    private ArrayList<? extends T> collection;
}
