import by.derovi.service_monitoring.visualizer.Table;
import by.derovi.service_monitoring.visualizer.TerminalRenderer;
import by.zhabdex.common.Service;
import by.zhabdex.common.Tools;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.util.JSONPObject;

import java.net.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        TerminalRenderer renderer = TerminalRenderer.init(1);
        URL url = new URL("http://zhabdex.ovi.by/status");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        while (true) {
            Table table = new Table("Monitor");
            table.addRow("Name", "Data Center", "Ping", "Available nodes", "Requests/sec", "Started time", "Current time");
            List<Service> services = Tools.JSON.readValue(url, new TypeReference<List<Service>>() {
            });
            for (Service service : services) {
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
}