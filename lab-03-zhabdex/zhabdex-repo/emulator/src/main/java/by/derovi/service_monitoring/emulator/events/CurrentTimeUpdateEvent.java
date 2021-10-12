package by.derovi.service_monitoring.emulator.events;

import by.derovi.service_monitoring.emulator.VirtualService;

import java.time.LocalDateTime;

public class CurrentTimeUpdateEvent implements ServiceEvent {
    @Override
    public void doEvent(VirtualService service) {
        service.setCurrentTime(LocalDateTime.now());
    }

    @Override
    public double getChance() {
        return 1;
    }
}
