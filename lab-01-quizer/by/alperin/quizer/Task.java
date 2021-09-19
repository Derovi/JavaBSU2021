package by.alperin.quizer;

public interface Task {
    interface Generator {
        Task generate();
    }

    String getText();
    Result validate(String answer);
}
