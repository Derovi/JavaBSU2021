package by.polchernikova.quizer;

import by.polchernikova.quizer.Result;

public interface Task {
    static interface Generator {
        Task generate();
    }

    Result validate(String answer);
    String getText();
}
