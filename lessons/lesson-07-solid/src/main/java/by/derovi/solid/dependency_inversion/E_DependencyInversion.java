package by.derovi.solid.dependency_inversion;

import by.derovi.solid.dependency_inversion.databases.Database;
import by.derovi.solid.dependency_inversion.databases.MongoDatabase;
import by.derovi.solid.dependency_inversion.databases.SQLDatabase;
import by.derovi.solid.dependency_inversion.loggers.ConsoleLogger;
import by.derovi.solid.dependency_inversion.loggers.DatabaseLogger;
import by.derovi.solid.dependency_inversion.loggers.Logger;
import lombok.extern.java.Log;
import org.springframework.boot.ConfigurableBootstrapContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

@SpringBootApplication
public class E_DependencyInversion {

    @Bean
    Logger logger() {
        System.out.println("Called once");
        return new ConsoleLogger("logger");
    }

    @Bean
    Database<String, User> database() {
        return new SQLDatabase<>(logger());
    }

    // use to be controller()

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(E_DependencyInversion.class, args);
        Controller controller = ctx.getBean(Controller.class);

        controller.register("Bob", 18);
        controller.register("Alex", 19);
        controller.register("Alice", 22);

        controller.growOld("Bob");
        controller.growOld("Bob");
        controller.growOld("Bob");
        controller.growOld("Bob");

        System.out.println(controller.getTopByAge());

    }

    void f2() {
        // logger for model
        final Logger modelLogger = new ConsoleLogger("model");

        // logger for mongo for logging
        final Logger mongoLogger = new ConsoleLogger("mongo");
        // mongo for logging
        final Database<Integer, String> mongoDb = new MongoDatabase<>(mongoLogger);
        // logger that uses mongo
        final Logger dbLogger = new DatabaseLogger(mongoDb, "db");

        // db
        final Database<String, User> db = new SQLDatabase<>(dbLogger);
        final Model model = new Model(modelLogger, db);

        // Controller controller = new Controller(model);
    }
}

