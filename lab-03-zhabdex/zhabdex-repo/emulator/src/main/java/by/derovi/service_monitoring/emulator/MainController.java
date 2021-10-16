package by.derovi.service_monitoring.emulator;

import by.zhabdex.common.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;

@RestController
public class MainController {
    @Autowired
    ServiceManager serviceManager;

    @GetMapping("/status")
    Collection<? extends Service> statusAll() {
        return serviceManager.getAvailableServices();
    }

    @GetMapping("/status/{name}")
    Service status(@PathVariable String name) {
        return serviceManager.getAvailableServices()
                .stream()
                .filter(service -> service.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found"));
    }
}
