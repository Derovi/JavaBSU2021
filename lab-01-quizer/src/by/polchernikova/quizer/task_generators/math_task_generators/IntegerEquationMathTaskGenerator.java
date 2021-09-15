package by.polchernikova.quizer.task_generators.math_task_generators;

import by.polchernikova.quizer.Task;
import by.polchernikova.quizer.task.TextTask;
import by.polchernikova.quizer.task.math_tasks.EquationMathTask;
import by.polchernikova.quizer.task.math_tasks.IntegerEquationMathTask;
import by.polchernikova.quizer.task.math_tasks.IntegerMathTask;

import java.util.Objects;
import java.util.Vector;

public class IntegerEquationMathTaskGenerator extends EquationMathTaskGenerator implements IntegerMathTaskGenerator {
    public IntegerEquationMathTaskGenerator (
            int minNumber,
            int maxNumber,
            boolean generateSum,
            boolean generateDifference,
            boolean generateMultiplication,
            boolean generateDivision
    ) {
        super(generateSum, generateDifference, generateMultiplication, generateDivision);
        if(minNumber > maxNumber) {
            throw new IllegalArgumentException("Минимальное число должно быть меньше максимального");
        }
        minNum = minNumber;
        maxNum = maxNumber;
    }

    public Task generate() {
        int firstInt = (int)(Math.random() * ((maxNum - minNum) + 1) + minNum);
        int secondInt = (int)(Math.random() * ((maxNum - minNum) + 1) + minNum);
        int operIndex = (int)(Math.random() * operations.size());
        return new IntegerEquationMathTask(firstInt, secondInt, operations.get(operIndex));
    }

    public int getMinNumber() {
        return minNum;
    }

    public int getMaxNumber() {
        return maxNum;
    }


    private final int minNum;
    private final int maxNum;
}
