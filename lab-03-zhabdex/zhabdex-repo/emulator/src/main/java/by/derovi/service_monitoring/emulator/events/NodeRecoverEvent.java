package by.derovi.service_monitoring.emulator.events;

import by.derovi.service_monitoring.emulator.VirtualService;

public class NodeRecoverEvent implements ServiceEvent {
    @Override
    public void doEvent(VirtualService service) {
        int recoverNumber = Math.max(1, random.nextInt((int) Math.sqrt(service.getMaxNodesCount())));
        service.setNodesCount(Math.min(service.getMaxNodesCount(), service.getNodesCount() + recoverNumber));
    }

    @Override
    public double getChance() {
        return 0.6;
    }
}
