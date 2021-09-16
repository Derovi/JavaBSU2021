package by.polchernikova.quizer.task.math_tasks;

import by.polchernikova.quizer.Result;
import by.polchernikova.quizer.Task;
import by.polchernikova.quizer.exceptions.NothingToGenerateException;

import java.util.*;

public abstract class AbstractMathTask implements MathTask {
    abstract static protected class Generator implements MathTask.Generator {
        public Generator(
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
                operations.add(oper.symbol);
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

    public AbstractMathTask(double firstArgument, double secondArgument, String oper, int precision) {
        maxPrecision = precision;
        firstArg = convertUsingPrecision(firstArgument);
        secondArg = convertUsingPrecision(secondArgument);
        operation = oper;
    }

    public Result validate(String answer) {
        try {
            Double.parseDouble(answer);
        } catch (NumberFormatException nfe) {
            return Result.INCORRECT_INPUT;
        }
        String ans = getAnswer();
        if(answer.equals(ans)) {
            return Result.OK;
        } else {
            return Result.WRONG;
        }
    }
    protected double convertUsingPrecision(double num) {
        return ((double) ((int)(num * Math.pow(10, maxPrecision)))) / (double) Math.pow(10, maxPrecision);
    }

    protected final int maxPrecision;
    protected final String operation;
    protected final double firstArg;
    protected final double secondArg;
}
