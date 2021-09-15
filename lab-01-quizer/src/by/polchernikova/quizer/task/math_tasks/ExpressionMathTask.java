package by.polchernikova.quizer.task.math_tasks;

import by.polchernikova.quizer.Result;

import java.util.Objects;

public class ExpressionMathTask extends AbstractMathTask {
    public ExpressionMathTask(double firstArgument, double secondArgument, String oper, int precision) {
        super(firstArgument, secondArgument, oper, precision);
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
}
