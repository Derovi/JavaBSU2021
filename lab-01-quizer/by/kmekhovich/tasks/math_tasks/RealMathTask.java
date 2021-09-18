package by.kmekhovich.tasks.math_tasks;

import by.kmekhovich.quizer.Result;

public interface RealMathTask extends MathTask {

    @Override
    default boolean isCorrectInput(String input) {
        try {
            Double.parseDouble(input);
        } catch(NumberFormatException e){
            return false;
        }
        return true;
    }

    double getCorrectAnswer();
    double getPrecisionInPower();

    @Override
    default Result validate(String answer) {
        if (isCorrectInput(answer)) {
            double res = Double.parseDouble(answer);
            if (Math.abs(res - getCorrectAnswer()) < getPrecisionInPower()) {
                return Result.OK;
            } else {
                return Result.WRONG;
            }
        } else {
            return Result.INCORRECT_INPUT;
        }
    }
}
