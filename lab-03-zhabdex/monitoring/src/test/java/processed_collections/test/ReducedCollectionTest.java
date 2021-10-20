package processed_collections.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import processed_collections.ReducedCollection;

import java.util.List;

public class ReducedCollectionTest {
    @Test
    public void reducedCollectionTest1() {
        ReducedCollection<Integer> collection
                = new ReducedCollection<>(Integer::sum);

        collection.renew(List.of(1, 2, 3, 4));
        Assertions.assertSame(collection.currentState().get(), 10);
    }

    @Test
    public void reducedCollectionTest2() {
        ReducedCollection<String> collection
                = new ReducedCollection<>(String::concat);

        collection.renew(List.of("a", "", "aba"));
        Assertions.assertEquals(collection.currentState().get(), "aaba");
    }

    @Test
    public void reducedCollectionTest3() {
        ReducedCollection<Integer> collection
                = new ReducedCollection<>(Integer::sum);

        collection.renew(List.of());
        Assertions.assertTrue(collection.currentState().isEmpty());
    }
}
