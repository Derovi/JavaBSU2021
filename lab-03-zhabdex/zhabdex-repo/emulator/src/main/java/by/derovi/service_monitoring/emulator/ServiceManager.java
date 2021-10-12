package by.derovi.service_monitoring.emulator;

import by.zhabdex.common.Service;
import by.derovi.service_monitoring.emulator.events.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Component
public class ServiceManager {
    @Autowired
    ResourceLoader resourceLoader;

    private List<VirtualService> services = new ArrayList<>();

    private List<ServiceEvent> events = new ArrayList<>();

    @PostConstruct
    void init() {
        events = List.of(
            new CurrentTimeUpdateEvent(),
            new NodeFailEvent(),
            new HalfNodeFailEvent(),
            new NodeRecoverEvent(),
            new ServiceFailEvent(),
            new ServiceStartEvent(),
            new UserRequestsEvent()
        );

        services = List.of(
            new VirtualService(
                "Zhabdex-search",
                "Chaplin",
                100,
                VirtualService.LoadLevel.HIGHEST
            ),
            new VirtualService(
                "AB-testing",
                "Chaplin",
                10,
                VirtualService.LoadLevel.MEDIUM
            ),
            new VirtualService(
                "Zhabdex-maps",
                "Buhalovo",
                30,
                VirtualService.LoadLevel.MEDIUM
            )
        );

        for (VirtualService service : services) {
            service.start();
        }
    }

    @Scheduled(fixedRate = 1000)
    void update() {
        for (VirtualService service : services) {
            for (ServiceEvent event : events) {
                if (Math.random() < event.getChance()) {
                    event.doEvent(service);
                }
            }
        }
    }

    public List<? extends Service> getAvailableServices() {
        return services.stream().filter(Predicate.not(VirtualService::isDown)).collect(Collectors.toList());
    }
}
