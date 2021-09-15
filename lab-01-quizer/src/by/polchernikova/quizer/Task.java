package by.polchernikova.quizer;

public interface Task {
    String getText();
    Result validate(String answer);
}
