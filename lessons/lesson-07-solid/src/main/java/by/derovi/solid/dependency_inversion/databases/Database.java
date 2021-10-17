package by.derovi.solid.dependency_inversion.databases;

import java.util.Collection;

public interface Database<K,V> {
    V get(K key) ;
    boolean has(K key);
    Collection<V> getAll();
    void put(K key, V value);
    void remove(K key);
}
