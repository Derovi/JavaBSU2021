package by.polchernikova.quizer.task_generators.math_task_generators;

import by.polchernikova.quizer.Task;
import by.polchernikova.quizer.task.math_tasks.RealEquationMathTask;

public class RealEquationMathTaskGenerator extends EquationMathTaskGenerator implements RealMathTaskGenerator{
    public RealEquationMathTaskGenerator (
            double minNumber,
            double maxNumber,
            boolean generateSum,
            boolean generateDifference,
            boolean generateMultiplication,
            boolean generateDivision,
            int precision
    ) {
        super(generateSum, generateDifference, generateMultiplication, generateDivision);
        if(minNumber > maxNumber) {
            throw new IllegalArgumentException("Минимальное число больше максимального");
        }
        minNum = minNumber;
        maxNum = maxNumber;
        if (precision < 0) {
            throw new IllegalArgumentException("Отрицательная точность");
        }
        maxPrecision = precision;
    }

    public Task generate() {
        double firstDouble = (Math.random() * (maxNum - minNum) + minNum);
        double secondDouble = (Math.random() * (maxNum - minNum) + minNum);
        int operIndex = (int)(Math.random() * operations.size());
        return new RealEquationMathTask(firstDouble, secondDouble, operations.get(operIndex), maxPrecision);
    }

    public double getMinNumber() {
        return minNum;
    }
    public double getMaxNumber() {
        return maxNum;
    }

    private final double minNum;
    private final double maxNum;
    private final int maxPrecision;
}
