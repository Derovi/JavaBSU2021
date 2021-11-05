package monitoring_lib;

import by.derovi.service_monitoring.visualizer.Table;
import by.derovi.service_monitoring.visualizer.TerminalRenderer;
import by.zhabdex.common.Service;
import by.zhabdex.common.Tools;
import com.fasterxml.jackson.core.type.TypeReference;
import org.reflections.Reflections;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MonitoringApplication {
    private MonitoringApplication(String URL,List<Monitoring> monitorings) throws IOException, InterruptedException {
        this.URL = URL;
        this.monitorings = monitorings;
        displayZhabdexState();
    }
    public static MonitoringApplicationBuilder builder() {
        return new MonitoringApplicationBuilder();
    }

    public static class MonitoringApplicationBuilder {
        public MonitoringApplicationBuilder setPackage(String packageName) {
            this.packageName = packageName;
            return this;
        }

        public MonitoringApplicationBuilder setServiceURL(String URL) {
            this.URL = URL;
            return this;
        }

        public MonitoringApplicationBuilder addScanner(MonitoringScanner scanner) {
            scanners.add(scanner);
            return this;
        }

        public MonitoringApplication start() throws IOException, InterruptedException {
            scanners.forEach(scanner -> monitorings.addAll(scanner.scan(new Reflections(packageName))));

            System.out.println(monitorings.size());
            System.out.println(scanners.size());
            System.out.println(packageName);
            return new MonitoringApplication(URL, monitorings);
        }

        String packageName;
        String URL;
        List<MonitoringScanner> scanners = new ArrayList<>();
        List<Monitoring> monitorings = new ArrayList<>();
    }

    public void displayZhabdexState() throws IOException, InterruptedException {
        TerminalRenderer renderer = TerminalRenderer.init(this.monitorings.size());
        while (true) {
            List<Service> services = fetchServices();
            List<Table> tables = monitorings.stream().map(monitoring -> {
                monitoring.update(services);
                return monitoring.getStatistics();
            }).toList();
            renderer.render(tables);
            Thread.sleep(1000);
        }
    }

    public List<Service> fetchServices() throws IOException {
        java.net.URL url = new URL(URL);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        return Tools.JSON.readValue(url, new TypeReference<List<Service>>() {});
    }

    String URL;
    List<Monitoring> monitorings;
}
