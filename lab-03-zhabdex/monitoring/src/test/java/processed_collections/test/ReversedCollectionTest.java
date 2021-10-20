package processed_collections.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import processed_collections.ReversedCollection;

import java.util.List;

public class ReversedCollectionTest {
    @Test
    public void reversedCollectionTest1() {
        ReversedCollection<Integer> collection
                = new ReversedCollection<>();

        collection.renew(List.of(4, 5, 6, 7));
        Assertions.assertArrayEquals(collection.currentState().toArray(), List.of(7, 6, 5, 4).toArray());
    }

    @Test
    public void reversedCollectionTest2() {
        ReversedCollection<Integer> collection
                = new ReversedCollection<>();

        collection.renew(List.of());
        Assertions.assertArrayEquals(collection.currentState().toArray(), List.of().toArray());
    }
}
