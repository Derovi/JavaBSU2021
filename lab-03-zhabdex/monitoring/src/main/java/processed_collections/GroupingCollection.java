package processed_collections;

import java.util.*;
import java.util.function.Function;

public class GroupingCollection<T, K> implements ProcessedCollection<T, Map.Entry<? extends K, ? extends List<? extends T>>>{
    public GroupingCollection(Function <? super T, ? extends K> classifier) {
        this.classifier = classifier;
        elements = new HashMap<>();
    }

    @Override
    public void renew(Collection<? extends T> elements) {
        this.elements.clear();
        elements.forEach(element -> {
           var feature = classifier.apply(element);
           if(this.elements.get(feature) == null) {
               this.elements.put(feature, new ArrayList<>());
           }
           this.elements.get(feature).add(element);
        });
    }

    @Override
    public Collection<? extends Map.Entry<? extends K, ? extends List<? extends T>>> currentState() {
        return elements.entrySet();
    }

    private Function <? super T, ? extends K> classifier;
    private Map<K, List<T>> elements;
}
