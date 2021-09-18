package by.kmekhovich.tasks.math_tasks;

import by.kmekhovich.operator.Operator;
import by.kmekhovich.quizer.Result;
import by.kmekhovich.quizer.Task;

import java.util.EnumSet;
import java.util.concurrent.ThreadLocalRandom;

public interface MathTask extends Task {
    interface Generator extends Task.Generator {
        double getMinNumber();
        double getMaxNumber();

        default double getDiffNumber() {
            return getMaxNumber() - getMinNumber();
        }
    }

    double getCorrectAnswer();

    default boolean isCorrectInput(String input) {
        try {
            Double.parseDouble(input);
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }

    double getPrecisionInPower();

    @Override
    default Result validate(String answer) {
        if (isCorrectInput(answer)) {
            double user_answer = Double.parseDouble(answer);
            if (Math.abs(user_answer - getCorrectAnswer()) <= getPrecisionInPower()) {
                return Result.OK;
            } else {
                return Result.WRONG;
            }
        } else {
            return Result.INCORRECT_INPUT;
        }
    }
}
