package by.derovi.service_monitoring.emulator.events;

import by.derovi.service_monitoring.emulator.VirtualService;

import java.util.Random;

public class UserRequestsEvent implements ServiceEvent {

    @Override
    public void doEvent(VirtualService service) {
        int requestNumber = (int) (random.nextInt(10) * service.getNodesCount() * service.getLoadLevel());
        service.setRequestsForUptime(service.getRequestsForUptime() + requestNumber);
        service.setSummaryPing(service.getSummaryPing() + requestNumber * (new Random().nextInt(100) + 20));
    }

    @Override
    public double getChance() {
        return 1;
    }
}
