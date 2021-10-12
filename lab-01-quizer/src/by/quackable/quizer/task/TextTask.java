package by.quackable.quizer.task;

import java.util.Objects;

public class TextTask implements Task {
    public TextTask(String task, String answer) {
        this.text = task;
        this.right_answer = answer;
    }

    public String getText() {
        return this.text;
    }

    public Result validate(String answer) {
        if (Objects.equals(this.right_answer, answer)) {
            return Result.OK;
        }
        return Result.WRONG;
    }

    private final String text;
    private final String right_answer;
}
