package by.zhabdex.monitoring.collections;

import java.util.Collection;

public interface ProcessedCollection<T, E> extends
        FinalProcessedCollection<T, Collection<? extends E>> {
//    default ProcessedCollection<? extends K> compose(ProcessedCollection<? extends E, K> collection) {
//        return new ;
//    }
}
