package pentapulse.healthcheck.domain;

import pentapulse.healthcheck.application.ServiceStatus;

import java.time.LocalDateTime;
import java.util.Date;

public class DownServiceEvent extends ServiceEvent {
    private final int monthlyDownCount;

    public DownServiceEvent(String serviceName, Date timestamp, ServiceStatus status, String message, int monthlyDownCount) {
        super(serviceName, timestamp, status, message);
        this.monthlyDownCount = monthlyDownCount;
    }

    public int getMonthlyDownCount() {
        return monthlyDownCount;
    }
}
