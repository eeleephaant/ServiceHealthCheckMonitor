package pentapulse.healthcheck.domain;

import pentapulse.healthcheck.application.ServiceStatus;

import java.time.LocalDateTime;
import java.util.Date;

public class ServiceEvent {
    private final String serviceName;
    private final Date timestamp;
    private final ServiceStatus status;
    private final String message;

    public ServiceEvent(String serviceName, Date timestamp, ServiceStatus status, String message) {
        this.serviceName = serviceName;
        this.timestamp = timestamp;
        this.status = status;
        this.message = message;
    }

    public String getServiceName() { return serviceName; }
    public Date getTimestamp() { return timestamp; }
    public ServiceStatus getStatus() { return status; }
    public String getMessage() { return message; }
}
