package by.derovi.service_monitoring.emulator.events;

import by.derovi.service_monitoring.emulator.VirtualService;

public class ServiceStartEvent implements ServiceEvent {
    @Override
    public void doEvent(VirtualService service) {
        service.start();
    }

    @Override
    public double getChance() {
        return 0.18;
    }
}
