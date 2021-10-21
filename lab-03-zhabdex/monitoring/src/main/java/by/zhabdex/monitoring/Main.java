package by.zhabdex.monitoring;

import by.zhabdex.common.Service;
import by.zhabdex.monitoring.collections.*;

import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static List<Service> fetchServices() {
        return List.of(new Service(),
                new Service("1", "d", LocalDateTime.MAX, LocalDateTime.MAX, 10, 10, 10),
                new Service("1", "d", LocalDateTime.MAX, LocalDateTime.MAX, 10, 1, 10));
    }

    public static void main(String[] args) {
//        MappedCollection<Integer, String> collection
//                = new MappedCollection<>((Integer a) -> "Number is " + a.toString());
//
//        collection.renew(List.of(4, 5, 6, 7));
//        collection.currentState().forEach(System.out::println);

//        FilteredCollection<Integer> collection
//                = new FilteredCollection<>(value -> value > 5);
//
//        collection.renew(List.of(4, 5, 6, 7));
//        collection.currentState().forEach(System.out::println);

//        LimitedCollection<Integer> collection
//                = new LimitedCollection<>(2);
//
//        collection.renew(List.of(4, 7, 6, 7));
//        collection.currentState().forEach(System.out::println);

//        ReducedCollection<Integer> collection
//                = new ReducedCollection<>(Integer::sum);
//
//        collection.renew(List.of(4, 5, 6, 7));
//        System.out.println(collection.currentState().get());

//        ReversedCollection<Integer> collection = new ReversedCollection<>();
//
//        collection.renew(List.of(4, 5, 6, 7));
//        collection.currentState().forEach(System.out::println);

//        SortedCollection<Service> collection
//                = new SortedCollection<>(Service::getNodesCount);
//
//        collection.renew(fetchServices());
//        collection.currentState().forEach(service -> System.out.println(service.getNodesCount()));

//        SortedCollection<Service> collection
//                = new SortedCollection<>((first, second) -> second.getNodesCount() - first.getNodesCount());
//
//        collection.renew(fetchServices());
//        collection.currentState().forEach(service -> System.out.println(service.getNodesCount()));

    }
}
