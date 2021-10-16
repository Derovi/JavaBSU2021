package by.derovi.solid;

import lombok.*;

import java.util.*;
import java.util.function.Function;

/**
 * Каждый объект должен иметь одну ответственность и эта ответственность должна быть
 * полностью инкапсулирована в класс.
 * Все его поведения должны быть направлены исключительно на обеспечение этой ответственности.
 *
 * Если у класса есть несколько разных причин для изменения, следует их вынести в отдельные сущности.
 */


@Data
@AllArgsConstructor
class Cat {
    private int id;
    private int numberOfWheels;
    private String model;
}

class CatDatabase {
    static Map<Integer, Cat> cats = new HashMap<>();

    void saveToDatabase(Cat cat) {
        System.out.println("Saving to database");
        cats.put(cat.getId(), cat);
    }

    <T> T visit(Function<Collection<? extends Cat>, T> visitor) {
        return visitor.apply(cats.values());
    }
}

public class A_SingleResponsibility {

    static Optional<? extends Cat> getMostWheeledCatFromDatabase(Collection<? extends Cat> cats) {
        System.out.println("Getting most wheeled car");
        return cats.stream().max(Comparator.comparingInt(Cat::getNumberOfWheels));
    }

    public static void main(String[] args) {

        CatDatabase catDatabase = new CatDatabase();

        List.of(
            new Cat(0, 3, "Audi"),
            new Cat(1, 6, "Bugatti"),
            new Cat(2, 4, "Buhanka")
        ).forEach(catDatabase::saveToDatabase);

        System.out.println(catDatabase.visit(A_SingleResponsibility::getMostWheeledCatFromDatabase));
    }
}
