package by.kmekhovich.task_generators.math_tasks_generators;

import by.kmekhovich.operator.Operator;

import java.util.EnumSet;
import java.util.concurrent.ThreadLocalRandom;

public abstract class RealMathTaskGenerator extends MathTaskGenerator {
    double minNumber, maxNumber;
    int precision;
    public RealMathTaskGenerator(int precision, double minNumber, double maxNumber, EnumSet<Operator> operators) {
        super(operators);
        if (precision <= 0 || precision > 12) {
            throw new IllegalArgumentException("Incorrect precision");
        }
        this.precision = precision;
        if (minNumber > maxNumber) {
            throw new IllegalArgumentException("Incorrect min and max numbers");
        }
        this.minNumber = minNumber;
        this.maxNumber = maxNumber;
    }

    protected double getRandomDoubleBetweenMinAndMax() {
        double range = this.maxNumber - this.minNumber;
        return ThreadLocalRandom.current().nextDouble() * range + this.minNumber;
    }

    public double getMinNumber() {
        return minNumber;
    }

    public double getMaxNumber() {
        return maxNumber;
    }
}
