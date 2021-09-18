package by.Khody31.quizer;

public interface Task {
    public interface Generator {
        Task generate();
    }

    String getText();
    Result validate(String answer);
}