package by.derovi.service_monitoring.emulator.events;

import by.derovi.service_monitoring.emulator.VirtualService;

import java.util.Random;

public interface ServiceEvent {
    Random random = new Random();

    void doEvent(VirtualService service);
    double getChance();
}
