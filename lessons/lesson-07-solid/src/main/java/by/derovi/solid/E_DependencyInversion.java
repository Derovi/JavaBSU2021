package by.derovi.solid;

interface Logger {
    void log(String str);
}

class ErrLogger implements Logger {
    @Override
    public void log(String str) {
        System.err.println(str);
    }
}

class OutLogger implements Logger {
    @Override
    public void log(String str) {
        System.out.println(str);
    }
}

class Database {
    Logger logger;
    Database(Logger logger) {
        this.logger = logger;
    }

    void write(String key, String value) {
        logger.log("Write " + value + " to " + key);
    }

    String get(String key) {
        logger.log("Read " + key);
        return null;
    }
}

public class E_DependencyInversion {

    public static void main(String[] args) {
        Database database = new Database(new OutLogger());

        database.get("fff");
        database.write("cat", "tom");
    }
}
