package by.alperin.quizer.tasks;

import by.alperin.quizer.Result;
import by.alperin.quizer.Task;

public class TextTask implements Task {
    private final String text;
    private final String correctAnswer;

    public TextTask(String text, String answer) {
        this.correctAnswer = answer;
        this.text = text;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public Result validate(String answer) {
        return correctAnswer.equals(answer) ? Result.OK : Result.WRONG;
    }
}
