package processed_collections.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import processed_collections.MappedCollection;
import java.util.List;

public class MappedCollectionTest {
    @Test
    public void mappedCollectionTest1() {
        MappedCollection<Integer, String> collection
                = new MappedCollection<>(a -> "Number is " + a.toString());

        collection.renew(List.of(4, 5, 6, 7));
        Assertions.assertArrayEquals(collection.currentState().toArray(), List.of("Number is 4", "Number is 5", "Number is 6", "Number is 7").toArray());
    }

    @Test
    public void mappedCollectionTest2() {
        MappedCollection<String, Integer> collection
                = new MappedCollection<>(a -> ((String)a).length());

        collection.renew(List.of("", "abc", "abacaba"));
        Assertions.assertArrayEquals(collection.currentState().toArray(), List.of(0, 3, 7).toArray());
    }
}
