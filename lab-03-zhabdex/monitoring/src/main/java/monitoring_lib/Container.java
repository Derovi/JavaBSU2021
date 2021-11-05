package monitoring_lib;

import by.derovi.service_monitoring.visualizer.Table;
import by.zhabdex.common.Service;
import processed_collections.*;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;

@MonitoringContainer
public class Container {
    @ActiveMonitoring
    static FinalProcessedCollection<Service, Table> top2Nodes() {
        return new SortedCollection<>(Service::getNodesCount)
                .compose(new LimitedCollection<>(2))
                .compose(new TableViewCollection<>("top nodes", List.of(
                        TableViewCollection.ColumnProvider.of("Name", Service::getName),
                        TableViewCollection.ColumnProvider.of("Available nodes", Service::getNodesCount)
                )));
    }
}