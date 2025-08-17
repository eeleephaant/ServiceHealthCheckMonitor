package pentapulse.healthcheck.domain;

import pentapulse.healthcheck.application.ServiceStatus;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;

public class WakeupServiceEvent extends ServiceEvent {
    private final Duration downtime;
    public WakeupServiceEvent(String serviceName, Date timestamp, ServiceStatus status, String message, Duration downtime) {
        super(serviceName, timestamp, status, message);
        this.downtime = downtime;
    }

    public Duration getDowntime() {
        return downtime;
    }
}
