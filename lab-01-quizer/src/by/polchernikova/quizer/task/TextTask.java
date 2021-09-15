package by.polchernikova.quizer.task;

import by.polchernikova.quizer.Result;
import by.polchernikova.quizer.*;

public class TextTask implements Task {
    public TextTask(String text, String answer) {
        task = text;
        ans = answer;
    }

    public String getText() {
        return task;
    }

    public Result validate(String answer) {
        try {
            Integer.parseInt(answer);
        } catch (NumberFormatException nfe) {
            return Result.INCORRECT_INPUT;
        }
        if(answer.equals(ans)) {
            return Result.OK;
        } else {
            return Result.WRONG;
        }
    }

    String task;
    String ans;
}
