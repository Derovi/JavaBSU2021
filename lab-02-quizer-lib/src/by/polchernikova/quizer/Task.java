package by.polchernikova.quizer;

import by.polchernikova.quizer.Result;

public interface Task {
    static interface Generator {
        Task generate();
    }

    String getText();
    Result validate(String answer);
}
