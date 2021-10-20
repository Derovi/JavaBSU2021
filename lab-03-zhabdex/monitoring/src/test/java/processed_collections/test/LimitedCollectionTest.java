package processed_collections.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import processed_collections.LimitedCollection;
import java.util.List;

public class LimitedCollectionTest {
    @Test
    public void limitedCollectionTest1() {
        LimitedCollection<Integer> collection
                = new LimitedCollection<>(2);

        collection.renew(List.of(4, 5, 6, 7));
        Assertions.assertArrayEquals(collection.currentState().toArray(), List.of(4, 5).toArray());
    }

    @Test
    public void limitedCollectionTest2() {
        LimitedCollection<String> collection
                = new LimitedCollection<>(7);

        collection.renew(List.of("a", "", "aba"));
        Assertions.assertArrayEquals(collection.currentState().toArray(), List.of("a", "", "aba").toArray());
    }
}
