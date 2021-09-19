package by.quackable.quizer.task;

import java.util.EnumSet;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public enum Operator {
    SUM,
    SUBTRACT,
    MULTIPLY,
    DIVIDE;

    public static Operator getRandomOperator(EnumSet<Operator> enum_set) {
        Random random = ThreadLocalRandom.current();
        return (Operator) enum_set.toArray()[random.nextInt(enum_set.size())];
    }

    public String asString() {
        return switch (this) {
            case SUM -> "+";
            case DIVIDE -> "/";
            case MULTIPLY -> "*";
            case SUBTRACT -> "-";
        };
    }
}
