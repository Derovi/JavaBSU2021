package by.polchernikova.quizer.task.math_tasks;

import by.polchernikova.quizer.Result;
import java.util.Objects;

public class IntegerEquationMathTask extends EquationMathTask implements IntegerMathTask {
    public IntegerEquationMathTask(int firstArgument, int secondArgument, String oper) {
        firstArg = firstArgument;
        secondArg = secondArgument;
        operation = oper;
    }

    public String getText() {
        return Integer.toString(firstArg) + operation + Integer.toString(secondArg) + "=?";
    }

    public String getAnswer() {
        return Integer.toString(calculateAnswer());
    }

    public int calculateAnswer() {
        int ans;
        if (Objects.equals(operation, "+")) {
            ans = (firstArg + secondArg);
        } else if (Objects.equals(operation, "-")) {
            ans = (firstArg - secondArg);
        } else if (Objects.equals(operation, "*")) {
            ans = (firstArg * secondArg);
        } else if (Objects.equals(operation, "/")) {
            ans = (firstArg / secondArg);
        } else {
            throw new NumberFormatException();
        }
        return ans;
    }

    public Result validate(String answer) {
        try {
            Integer.parseInt(answer);
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

    private final int firstArg;
    private final int secondArg;
    private final String operation;
}
