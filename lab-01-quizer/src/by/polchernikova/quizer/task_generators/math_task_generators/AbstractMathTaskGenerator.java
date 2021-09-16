package by.polchernikova.quizer.task_generators.math_task_generators;

import by.polchernikova.quizer.Task;
import by.polchernikova.quizer.TaskGenerator;
import by.polchernikova.quizer.exceptions.NothingToGenerateException;
import by.polchernikova.quizer.task.math_tasks.ExpressionMathTask;
import by.polchernikova.quizer.task.math_tasks.MathTask.Operation;

import java.util.*;

public abstract class AbstractMathTaskGenerator implements TaskGenerator {
    public AbstractMathTaskGenerator(
            double minNumber,
            double maxNumber,
            EnumSet<Operation> opers,
            int precision
    ) {
        if(opers.isEmpty()) {
            throw new NothingToGenerateException("No operations are allowed");
        }
        operations = new Vector<String>();
        for(Operation oper : opers) {
            operations.add(operation_to_symbol.get(oper));
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
    static protected final Map<Operation, String> operation_to_symbol;
    static {
        operation_to_symbol = new HashMap<Operation, String>();
        operation_to_symbol.put(Operation.SUM, "+");
        operation_to_symbol.put(Operation.DIFFERENCE, "-");
        operation_to_symbol.put(Operation.MULTIPLICATION, "*");
        operation_to_symbol.put(Operation.DIVISION, "/");
    }
}
