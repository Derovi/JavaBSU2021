package by.derovi.solid.dependency_inversion.loggers;

public class ConsoleLogger implements Logger {
    public ConsoleLogger(String tag) {
        this.tag = tag;
    }

    private final String tag;

    public void log(String line) {
        System.out.println(tag + " => " + line);
    }
}

