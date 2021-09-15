package by.polchernikova.quizer.task.math_tasks;

import by.polchernikova.quizer.Result;

import java.util.Objects;

public class RealExpressionMathTask extends ExpressionMathTask implements RealMathTask {
    public RealExpressionMathTask(double firstArgument, double secondArgument, String oper, int precision) {
        maxPrecision = precision;
        firstArg = convertUsingPrecision(firstArgument);
        secondArg = convertUsingPrecision(secondArgument);
        operation = oper;
    }

    public String getText() {
        return Double.toString(firstArg) + operation + "x=" + Double.toString(secondArg);
    }
    public String getAnswer() {
        return Double.toString(calculateAnswer());
    }

    public double calculateAnswer() {
        double ans;
        if (Objects.equals(operation, "+")) {
            ans = (secondArg - firstArg);
        } else if (Objects.equals(operation, "-")) {
            ans = (firstArg - secondArg);
        } else if (Objects.equals(operation, "*")) {
            ans = (secondArg / firstArg);
        } else if (Objects.equals(operation, "/")) {
            ans = (firstArg/secondArg);
        } else {
            throw new NumberFormatException();
        }
        return convertUsingPrecision(ans);
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

    private double convertUsingPrecision(double num) {
        return ((double) ((int)(num * Math.pow(10, maxPrecision))))/ Math.pow(10, maxPrecision);
    }

    private final int maxPrecision;
    private final String operation;
    private final double firstArg;
    private final double secondArg;
}
