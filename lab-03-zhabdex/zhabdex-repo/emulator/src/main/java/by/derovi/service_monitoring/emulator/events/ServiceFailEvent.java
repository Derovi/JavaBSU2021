package by.derovi.service_monitoring.emulator.events;

import by.derovi.service_monitoring.emulator.VirtualService;

public class ServiceFailEvent implements ServiceEvent {
    @Override
    public void doEvent(VirtualService service) {
        service.shutdown();
    }

    @Override
    public double getChance() {
        return 0.02;
    }
}
