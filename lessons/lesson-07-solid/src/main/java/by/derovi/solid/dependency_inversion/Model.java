package by.derovi.solid.dependency_inversion;

import by.derovi.solid.dependency_inversion.databases.Database;
import by.derovi.solid.dependency_inversion.databases.SQLDatabase;
import by.derovi.solid.dependency_inversion.loggers.ConsoleLogger;
import by.derovi.solid.dependency_inversion.loggers.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Collection;

@Component
public class Model {
    private final Logger logger;
    private final Database<String, User> database;

    @PostConstruct
    void postCon() {
        logger.log("CONSTRUCTED");
    }

    @PreDestroy
    void predes() {
        logger.log("DESTRUCTED");
    }

    @Autowired
    public Model(Logger logger, Database<String, User> database) {
        this.logger = logger;
        this.database = database;
    }

    public User getUser(String name) {
        return database.get(name);
    }

    public void saveUser(User user) {
        if (!database.has(user.getName())) {
            logger.log("Created " + user.getName());
        }
        database.put(user.getName(), user);
    }

    public Collection<User> getAllUsers() {
        return database.getAll();
    }
}
