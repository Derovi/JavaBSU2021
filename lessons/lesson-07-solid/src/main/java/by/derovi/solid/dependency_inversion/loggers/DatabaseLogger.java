package by.derovi.solid.dependency_inversion.loggers;

import by.derovi.solid.dependency_inversion.databases.Database;
import by.derovi.solid.dependency_inversion.databases.SQLDatabase;

public class DatabaseLogger implements Logger {
    final Database<Integer, String> sqlDatabase;
    private final String tag;

    public DatabaseLogger(Database<Integer, String> sqlDatabase, String tag) {
        this.sqlDatabase = sqlDatabase;
        this.tag = tag;
    }

    public void log(String line) {
        sqlDatabase.put(index++, tag + " => " + line);
    }

    private int index = 0;
}

