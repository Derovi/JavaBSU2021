package by.derovi.service_monitoring.emulator;

import by.zhabdex.common.Service;

import java.time.LocalDateTime;

public class VirtualService extends Service {
    public enum LoadLevel {
        LOWEST(0.05),
        LOWE(0.17),
        MEDIUM(0.4),
        HIGH(0.66),
        HIGHEST(1);

        LoadLevel(double value) {
            this.value = value;
        }

        public double getValue() {
            return value;
        }

        private double value;
    }

    public VirtualService(
            String name,
            String dataCenter,
            int maxNodesCount,
            double loadLevel
    ) {
        if (loadLevel < 0 || loadLevel > 1) {
            throw new IllegalArgumentException("Invalid load level");
        }
        this.loadLevel = loadLevel;
        this.maxNodesCount = maxNodesCount;
        this.setName(name);
        this.setDataCenter(dataCenter);
        start();
    }

    public VirtualService(
            String name,
            String dataCenter,
            int maxNodesCount,
            LoadLevel loadLevel
    ) { this(name, dataCenter, maxNodesCount, loadLevel.value); }

    private final double loadLevel;
    private final int maxNodesCount;
    private boolean down = true;

    public void start() {
        if (!down) {
            return;
        }
        down = false;
        this.setStartedTime(LocalDateTime.now());
        this.setCurrentTime(LocalDateTime.now());
        this.setRequestsForUptime(0);
        this.setNodesCount(maxNodesCount);
        this.setSummaryPing(0);
    }

    public void shutdown() {
        down = true;
    }

    public double getLoadLevel() {
        return loadLevel;
    }

    public int getMaxNodesCount() {
        return maxNodesCount;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }
}
