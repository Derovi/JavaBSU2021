package by.derovi.service_monitoring.emulator.events;

import by.derovi.service_monitoring.emulator.VirtualService;

import java.util.Random;

public class NodeFailEvent implements ServiceEvent {

    @Override
    public void doEvent(VirtualService service) {
        int failNumber = Math.max(1, random.nextInt((int) Math.sqrt(service.getMaxNodesCount())));
        service.setNodesCount(Math.max(0, service.getNodesCount() - failNumber));
    }

    @Override
    public double getChance() {
        return 0.35;
    }
}
