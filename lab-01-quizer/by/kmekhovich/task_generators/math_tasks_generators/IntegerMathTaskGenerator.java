package by.kmekhovich.task_generators.math_tasks_generators;

import by.kmekhovich.operator.Operator;

import java.util.EnumSet;
import java.util.concurrent.ThreadLocalRandom;

public abstract class IntegerMathTaskGenerator extends MathTaskGenerator {
    int minNumber, maxNumber;

    public IntegerMathTaskGenerator(int minNumber, int maxNumber, EnumSet<Operator> operators) {
        super(operators);
        if (minNumber > maxNumber) {
            throw new IllegalArgumentException("Incorrect min and max numbers");
        }
        this.minNumber = minNumber;
        this.maxNumber = maxNumber;
    }

    protected int getRandomIntBetweenMinAndMax() {
        return ThreadLocalRandom.current().nextInt(this.maxNumber - this.minNumber + 1) + this.minNumber;
    }

    public int getMinNumber() {
        return minNumber;
    }

    public int getMaxNumber() {
        return maxNumber;
    }
}