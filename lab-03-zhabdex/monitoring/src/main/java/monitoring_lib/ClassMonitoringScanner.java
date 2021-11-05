package monitoring_lib;

import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.stream.Collectors;

public class ClassMonitoringScanner implements MonitoringScanner {

    @Override
    public Collection<Monitoring> scan(Reflections reflection) {
        var annonated = reflection.getTypesAnnotatedWith(ActiveMonitoring.class);
        var monitorings = reflection.getSubTypesOf(Monitoring.class);
        annonated.retainAll(monitorings);
        return (Collection<Monitoring>) annonated.stream().map(monitoring -> {
            try {
                return monitoring.getConstructor().newInstance();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }).collect(Collectors.toList());
    }
}
