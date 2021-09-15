package by.polchernikova.quizer.task_generators.math_task_generators;

import by.polchernikova.quizer.Task;
import by.polchernikova.quizer.TaskGenerator;
import by.polchernikova.quizer.exceptions.NothingToGenerateException;
import by.polchernikova.quizer.task.math_tasks.ExpressionMathTask;

import java.util.Vector;

public abstract class AbstractMathTaskGenerator implements TaskGenerator {
    public AbstractMathTaskGenerator(
            double minNumber,
            double maxNumber,
            boolean generateSum,
            boolean generateDifference,
            boolean generateMultiplication,
            boolean generateDivision,
            int precision
    ) {
        if(!generateDifference && !generateDivision && !generateSum && !generateMultiplication) {
            throw new NothingToGenerateException("No operations are allowed");
        }
        operations = new Vector<String>();
        if(generateSum) {
            operations.add("+");
        }
        if(generateDifference) {
            operations.add("-");
        }
        if(generateMultiplication) {
            operations.add("*");
        }
        if(generateDivision) {
            operations.add("/");
        }
        if(minNumber > maxNumber) {
            throw new IllegalArgumentException("Минимальное число должно быть меньше максимального");
        }
        minNum = minNumber;
        maxNum = maxNumber;
        if (precision < 0) {
            throw new IllegalArgumentException("Отрицательная точность");
        }
        maxPrecision = precision;
    }

    public double getMinNumber() {
        return minNum;
    }
    public double getMaxNumber() {
        return maxNum;
    }

    public Task generate() {
        double firstDouble = (Math.random() * (maxNum - minNum) + minNum);
        double secondDouble = (Math.random() * (maxNum - minNum) + minNum);
        int operIndex = (int)(Math.random() * operations.size());
        return new ExpressionMathTask(firstDouble, secondDouble, operations.get(operIndex), maxPrecision);
    }

    protected Vector<String> operations;
    protected final double minNum;
    protected final double maxNum;
    protected final int maxPrecision;
}
