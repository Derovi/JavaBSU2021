package by.kmekhovich.tasks.math_tasks;

import by.kmekhovich.quizer.Result;

public interface IntegerMathTask extends MathTask {

    @Override
    default boolean isCorrectInput(String input) {
        try {
            Integer.parseInt(input);
        } catch(NumberFormatException e){
            return false;
        }
        return true;
    }
    int getCorrectAnswer();

    @Override
    default Result validate(String answer) {
        if (isCorrectInput(answer)) {
            int res = Integer.parseInt(answer);
            if (res == getCorrectAnswer()) {
                return Result.OK;
            } else {
                return Result.WRONG;
            }
        } else {
            return Result.INCORRECT_INPUT;
        }
    }
}
