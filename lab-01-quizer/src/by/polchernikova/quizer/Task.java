package by.polchernikova.quizer;

public interface Task {
    static interface Generator {
        Task generate();
    }

    String getText();
    Result validate(String answer);
}
