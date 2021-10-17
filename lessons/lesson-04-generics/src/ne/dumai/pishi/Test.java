package ne.dumai.pishi;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.IntStream;

class Pet {
    private String name;

    public Pet(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}


class Human {
    private String name;

    public Human(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Child extends Human {
    private List<Pet> pets;

    public Child(String name) {
        super(name);
    }

    public List<Pet> getPets() {
        return pets;
    }

    Child withPets(List<Pet> pets) {
        this.pets = pets;
        return this;
    }
}

class Vlad extends Child {

    public Vlad() {
        super("Vlad");
    }
}

class Alex extends Child {

    public Alex() {
        super("Alex");
    }
}

public class Test {
//
//
//    static void print(Collection<? extends Pet> pets) {
//        print(pets.stream());
//    }
//
//    static void print(Stream<? extends Pet> elements) {
//        elements.forEach(pet -> System.out.println(pet.getName()));
//    }

    static <T> void method(
            Supplier<T> supplier,
            Consumer<T> consumer,
            int count
    ) {
        for (int i = 0; i < count; ++i) {
            consumer.accept(supplier.get());
        }
    }

//    static boolean aba(Integer a) {
//        return a.toString().startsWith("4") || a.toString().startsWith("8");
//    }

    static double generate() {
        return new Random().nextDouble();
    }

    static void prin(Number d) {
        System.out.println(d);
    }

    public static void main(String[] args) {

        List<Child> children = List.of(
            new Vlad().withPets(List.of(new Pet("Хот"), new Pet("Дог"))),
            new Alex().withPets(List.of(new Pet("Котя"), new Pet("Догги")))
        );

//        method(() -> new Random().nextDouble(), Test::prin, 10);

        method(Test::generate, (Number value) -> prin(value)    , 10);

//
//        List<Pet> pets = new ArrayList<>();
//        for (Child child : children) {
//            for (Pet pet : child.getPets()) {
//                pets.add(pet);
//            }
//        }
//
//        print(children.stream().flatMap(child -> child.getPets().stream()));

        // print(pets);
    }
}
