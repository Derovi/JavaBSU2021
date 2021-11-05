package monitoring_lib;

import org.reflections.Reflections;

import java.util.Collection;

@FunctionalInterface
public interface MonitoringScanner {
    Collection<Monitoring> scan(Reflections reflection);
}