package by.kmekhovich.quizer;

public interface Task {
    String getText();
    Result validate(String answer);
}