package processed_collections.test;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import processed_collections.SortedCollection;
import java.util.List;

public class SortedCollectionTest {
    @Test
    void SortedCollectionTest1() {
        SortedCollection<Integer, Integer> collection
                = new SortedCollection<>(a -> a);

        collection.renew(List.of(1, 2, 4, 3));
        Assertions.assertArrayEquals(collection.currentState().toArray(), List.of(1, 2, 3, 4).toArray());
    }

    @Test
    void SortedCollectionTest2() {
        SortedCollection<Integer, Integer> collection
                = new SortedCollection<>(Integer::compareTo);

        collection.renew(List.of(1, 2, 4, 3));
        Assertions.assertArrayEquals(collection.currentState().toArray(), List.of(1, 2, 3, 4).toArray());
    }
}
