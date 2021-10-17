package by.zhabdex.common;

import lombok.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Service {
    private String name;
    private String dataCenter;
    private LocalDateTime startedTime;
    private LocalDateTime currentTime;

    @Builder.Default
    private long requestsForUptime = 0;
    @Builder.Default
    private int nodesCount = 0;
    @Builder.Default
    private int summaryPing = 0;

    public long getUptimeSeconds() {
        return ChronoUnit.SECONDS.between(startedTime, currentTime);
    }

    public long getAveragePing() {
        return summaryPing / requestsForUptime;
    }

    public long getRequestsPerSecond() {
        long uptimeSeconds = getUptimeSeconds();
        if (uptimeSeconds == 0) {
            return 0;
        }
        return requestsForUptime / uptimeSeconds;
    }

    public static void main(String[] args) {
        Service service = new Service();
    }
}
