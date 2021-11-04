import by.derovi.service_monitoring.visualizer.Table;
import by.derovi.service_monitoring.visualizer.TerminalRenderer;
import by.zhabdex.common.Service;
import by.zhabdex.common.Tools;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.util.JSONPObject;
import processed_collections.*;

import java.net.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class Main {
    public static List<Service> fetchServices() throws IOException {
        URL url = new URL("http://zhabdex.ovi.by/status");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        return Tools.JSON.readValue(url, new TypeReference<List<Service>>() {});
    }

    public static void displayZhabdexState() throws IOException, InterruptedException {
        TerminalRenderer renderer = TerminalRenderer.init(1);
        while (true) {
            Table table = new Table("Monitor");
            table.addRow("Name", "Data Center", "Ping", "Available nodes", "Requests/sec", "Started time", "Current time");
            for (Service service : fetchServices()) {
                table.addRow(service.getName(),
                        service.getDataCenter(),
                        Long.toString(service.getAveragePing()),
                        Integer.toString(service.getNodesCount()),
                        Long.toString(service.getRequestsPerSecond()),
                        service.getStartedTime().toString(),
                        service.getCurrentTime().toString());
            }
            renderer.render(List.of(table));
            Thread.sleep(1000);
        }
    }

    public static void tableViewCollectionTest() throws IOException {
        FinalProcessedCollection<Service, Table> collection =
                new TableViewCollection<>("Test", List.of(
                        TableViewCollection.ColumnProvider.of("Name", Service::getName),
                        TableViewCollection.ColumnProvider.of("Data center", Service::getDataCenter),
                        TableViewCollection.ColumnProvider.of("Ping", Service::getAveragePing),
                        TableViewCollection.ColumnProvider.of("Available nodes", Service::getNodesCount),
                        TableViewCollection.ColumnProvider.of("Requests/sec", Service::getRequestsPerSecond),
                        TableViewCollection.ColumnProvider.of("Started time", Service::getStartedTime),
                        TableViewCollection.ColumnProvider.of("Current time", Service::getCurrentTime)
                ));

        collection.renew(fetchServices());

        TerminalRenderer renderer = TerminalRenderer.init(1);
        renderer.render(List.of(collection.currentState()));
    }

    public static void ComposeTest() throws IOException {
        var collection =
                new FilteredCollection<Integer>(a -> a > 2)
                        .compose(new SortedCollection<>(a->a)).compose(new LimitedCollection<>(3))
                        .compose(new ReducedCollection<>(Integer::sum));

        collection.renew(List.of(3, 4, 1, 9, 9, 0));
        System.out.println(collection.currentState());
    }

    public static void main(String[] args) throws IOException {
        ComposeTest();
    }
}