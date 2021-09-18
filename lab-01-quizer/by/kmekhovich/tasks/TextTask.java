package by.kmekhovich.tasks;

import by.kmekhovich.quizer.Result;
import by.kmekhovich.quizer.Task;

import java.util.Objects;

public class TextTask implements Task {
    String text, answer;
    public TextTask(String text, String answer) {
        this.text = text;
        this.answer = answer;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public Result validate(String answer) {
        return (this.answer.equals(answer) ? Result.OK : Result.WRONG);
    }
}
