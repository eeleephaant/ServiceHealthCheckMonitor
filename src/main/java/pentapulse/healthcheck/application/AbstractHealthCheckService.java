package pentapulse.healthcheck.application;

import pentapulse.healthcheck.domain.*;
import pentapulse.healthcheck.domain.contracts.ServiceEventRepository;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

public abstract class AbstractHealthCheckService implements Runnable {
    protected Service service;
    protected Notifier notifier;
    protected boolean lastState;
    private final ServiceEventRepository eventRepository;

    public AbstractHealthCheckService(Service service, ServiceEventRepository eventRepository, Notifier notifier) {
        this.service = service;
        this.lastState = this.ping().isOk();
        this.eventRepository = eventRepository;
        this.notifier = notifier;
    }

    protected abstract Heartbeat ping();


    protected void sendNotification(Heartbeat heartbeat) {
        ServiceStatus serviceStatus = heartbeat.isOk() ? ServiceStatus.SERVICE_RUNNING : ServiceStatus.SERVICE_STOPPED;
        switch (serviceStatus) {
            case SERVICE_STOPPED:
            {
                int downCount = this.eventRepository.getDownEventCountForMonth(this.service.getName());
                this.notifier.notify(new DownServiceEvent(service.getName(), Date.from(Instant.now()), serviceStatus, heartbeat.getError(), downCount));
                break;
            }
            case SERVICE_RUNNING:
            {
                Date lastDownEventTime = this.eventRepository.getLastDownEventTime(this.service.getName());
                Duration downtime = getDowntimeDuration(lastDownEventTime, Date.from(Instant.now()));
                this.notifier.notify(new WakeupServiceEvent(service.getName(), Date.from(Instant.now()), serviceStatus, heartbeat.getError(), downtime));
            }
        }
    }

    private Duration getDowntimeDuration(Date lastDownEventTime, Date upEventTime) {
        if (lastDownEventTime == null || upEventTime == null) {
            throw new IllegalArgumentException("Dates must not be null");
        }

        Instant down = lastDownEventTime.toInstant();
        Instant up = upEventTime.toInstant();

        return Duration.between(down, up);
    }

    protected void saveServiceStatus(Heartbeat heartbeat) {
        ServiceStatus serviceStatus = heartbeat.isOk() ? ServiceStatus.SERVICE_RUNNING : ServiceStatus.SERVICE_STOPPED;
        ServiceEvent event = new ServiceEvent(service.getName(), Date.from(Instant.now()), serviceStatus, heartbeat.getError());
        eventRepository.saveEvent(event);
    }

    @Override
    public void run() {
        Heartbeat heartbeat = ping();
        if (heartbeat.isOk() != lastState) {
            sendNotification(heartbeat);
        }
        saveServiceStatus(heartbeat);
        lastState = heartbeat.isOk();
    }
}
