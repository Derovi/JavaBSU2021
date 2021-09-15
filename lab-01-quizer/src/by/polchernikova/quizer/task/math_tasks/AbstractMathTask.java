package by.polchernikova.quizer.task.math_tasks;

import by.polchernikova.quizer.Result;

import java.util.Objects;

public abstract class AbstractMathTask implements MathTask {
    public String getText() {
        return "Кто лучше всего придумывает лабы?";
    }

    public Result validate(String answer) {
        if (Objects.equals(answer, "Роман Демидович")) {
            return Result.OK;
        } else {
            return Result.WRONG;
        }
    }
}
