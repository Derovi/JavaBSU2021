package by.derovi.solid.dependency_inversion.databases;

import by.derovi.solid.dependency_inversion.loggers.ConsoleLogger;
import by.derovi.solid.dependency_inversion.loggers.Logger;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class SQLDatabase<K, V> implements Database<K,V>{
    private final Logger logger;
    private final Map<K, V> records = new HashMap<>();

    public SQLDatabase(Logger logger) {
        this.logger = logger;
    }

    public V get(K key) {
        logger.log("get " + key);
        return records.get(key);
    }

    public boolean has(K key) {
        logger.log("has " + key);
        return records.containsKey(key);
    }

    public Collection<V> getAll() {
        logger.log("getAll");
        return records.values();
    }

    public void put(K key, V value) {
        logger.log("put " + value + " to " + key);
        records.put(key, value);
    }

    public void remove(K key) {
        logger.log("remove " + key);
        records.remove(key);
    }
}

