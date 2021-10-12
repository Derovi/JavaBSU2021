package by.derovi.service_monitoring.emulator.events;

import by.derovi.service_monitoring.emulator.VirtualService;

public class HalfNodeFailEvent implements ServiceEvent {

    @Override
    public void doEvent(VirtualService service) {
        int failNumber = Math.max(1, service.getNodesCount() / 2);
        service.setNodesCount(Math.max(0, service.getNodesCount() - failNumber));
    }

    @Override
    public double getChance() {
        return 0.02;
    }
}
