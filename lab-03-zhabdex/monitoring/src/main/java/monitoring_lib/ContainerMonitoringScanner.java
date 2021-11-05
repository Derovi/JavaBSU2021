package monitoring_lib;

import by.derovi.service_monitoring.visualizer.Table;
import by.zhabdex.common.Service;
import org.reflections.Reflections;
import processed_collections.FinalProcessedCollection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

public class ContainerMonitoringScanner implements MonitoringScanner {
    @Override
    public Collection<Monitoring> scan(Reflections reflection) {
        var annonated = reflection.getTypesAnnotatedWith(MonitoringContainer.class);
        List<Monitoring> monitorings = new ArrayList<>();
        annonated.forEach(monitoring -> {
            Arrays.stream(monitoring.getMethods()).
                                    filter(method -> method.isAnnotationPresent(ActiveMonitoring.class))
                    .forEach(method -> {
                        if(method.getReturnType() == Monitoring.class) {
                            try {
                                monitorings.add((Monitoring) method.invoke(null));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else if(method.getReturnType() == FinalProcessedCollection.class) {
                            monitorings.add(new Monitoring() {
                                FinalProcessedCollection<Service, Table> collection;
                                {
                                    try {
                                       collection = (FinalProcessedCollection<Service, Table>)method.invoke(null);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }

                                @Override
                                public void update(Collection<? extends Service> services) {
                                    collection.renew(services);
                                }

                                @Override
                                public Table getStatistics() {
                                    return collection.currentState();
                                }
                            });
                        }
                    });

        });
        return monitorings;
    }
}
