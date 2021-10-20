package processed_collections.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import processed_collections.FilteredCollection;
import java.util.List;


public class FilteredCollectionTest {
    @Test
    public void filteredCollectionTest1() {
        FilteredCollection<Integer> collection
                = new FilteredCollection<>(value -> value > 5);

        collection.renew(List.of(4, 5, 6, 7));
        Assertions.assertArrayEquals(collection.currentState().toArray(), List.of(6, 7).toArray());
    }

    @Test
    public void filteredCollectionTest2() {
        FilteredCollection<String> collection
                = new FilteredCollection<>(value -> !value.isEmpty());

        collection.renew(List.of("", "aba", "", "", "a"));
        Assertions.assertArrayEquals(collection.currentState().toArray(), List.of("aba", "a").toArray());
    }
}
