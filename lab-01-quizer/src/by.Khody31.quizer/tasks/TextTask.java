package by.Khody31.quizer.tasks;

import by.Khody31.quizer.Result;
import by.Khody31.quizer.Task;

public class TextTask implements Task {
    public TextTask(String text, String answer) {
        this.text = text;
        this.answer = answer;
    }

    public String getText() {
        return answer;
    }

    public Result validate(String answer) {
        if (answer == null) {
            return Result.INCORRECT_INPUT;
        } else if (answer.equals(this.answer)) {
            return Result.OK;
        } else {
            return Result.WRONG;
        }
    }

    String text;
    String answer;
}
