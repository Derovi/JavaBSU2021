package by.quackable.quizer.task;

public interface Task {
    String getText();
    Result validate(String answer);
}
