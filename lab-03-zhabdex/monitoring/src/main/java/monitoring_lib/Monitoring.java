package monitoring_lib;

import by.derovi.service_monitoring.visualizer.Table;
import by.zhabdex.common.Service;

import java.util.Collection;

public interface Monitoring {
    public void update(Collection<? extends Service> services);
    public Table getStatistics();
}
