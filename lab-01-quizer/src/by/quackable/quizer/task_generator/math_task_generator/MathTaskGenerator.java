package by.quackable.quizer.task_generator.math_task_generator;

import by.quackable.quizer.task.Operator;
import by.quackable.quizer.task_generator.TaskGenerator;

import java.util.EnumSet;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public abstract class MathTaskGenerator implements TaskGenerator {
    public MathTaskGenerator(int precision, double min, double max) {
        this.precision = precision;
        this.min = min;
        this.max = max;
    }

    public MathTaskGenerator(double min, double max) {
        this.precision = 0;
        this.min = min;
        this.max = max;
    }

    int getRandomInteger() {
        Random random = ThreadLocalRandom.current();
        return random.nextInt((int) (Math.round(getMaxNumber()) - Math.round(getMinNumber())))
                + (int) Math.round(getMinNumber());
    }

    protected double getRandomDouble() {
        Random random = ThreadLocalRandom.current();
        double answer = random.nextDouble() * (getMaxNumber() - getMinNumber()) + getMinNumber();
        double pow = Math.pow(10., getPrecision());
        return Math.round(answer * pow) / pow;
    }

    public double getMinNumber() {
        return min;
    }

    public double getMaxNumber() {
        return max;
    }

    public int getPrecision() {
        return precision;
    }

    protected final double min;
    protected final double max;
    protected final int precision;
}
