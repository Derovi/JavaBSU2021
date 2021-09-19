package by.alperin.quizer.exceptions;

public class EmptyTasksListException extends IndexOutOfBoundsException {
    public EmptyTasksListException(String message) {
        super(message);
    }
}
