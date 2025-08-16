package pentapulse.healthcheck.domain;

import pentapulse.healthcheck.application.ServiceStatus;

import java.time.LocalDateTime;

public class DownServiceEvent extends ServiceEvent {
    private int monthlyDownCount;

    public DownServiceEvent(String serviceName, LocalDateTime timestamp, ServiceStatus status, String message, int monthlyDownCount) {
        super(serviceName, timestamp, status, message);
        this.monthlyDownCount = monthlyDownCount;
    }

    public int getMonthlyDownCount() {
        return monthlyDownCount;
    }
}
