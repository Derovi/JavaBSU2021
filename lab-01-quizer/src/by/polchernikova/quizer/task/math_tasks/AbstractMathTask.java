package by.polchernikova.quizer.task.math_tasks;

import by.polchernikova.quizer.Result;

import java.util.Objects;

public abstract class AbstractMathTask implements MathTask {
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
