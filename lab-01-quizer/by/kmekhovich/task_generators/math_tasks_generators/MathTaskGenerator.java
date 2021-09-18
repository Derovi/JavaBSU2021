package by.kmekhovich.task_generators.math_tasks_generators;

import by.kmekhovich.operator.Operator;
import by.kmekhovich.quizer.TaskGenerator;

import java.util.EnumSet;
import java.util.concurrent.ThreadLocalRandom;

public abstract class MathTaskGenerator implements TaskGenerator {
    EnumSet<Operator> operators;

    protected MathTaskGenerator(EnumSet<Operator> operators) {
        this.operators = operators;
    }

    protected Operator getRandomOperator() {
        if (operators.isEmpty()) {
            throw new RuntimeException("Enum Set of operators is empty");
        }
        int result = ThreadLocalRandom.current().nextInt(operators.size());
        return (Operator) operators.toArray()[result];
    }
}
