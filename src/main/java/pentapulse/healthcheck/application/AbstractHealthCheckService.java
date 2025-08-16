package pentapulse.healthcheck.application;

import pentapulse.healthcheck.domain.*;

import java.time.Duration;
import java.time.LocalDateTime;

public abstract class AbstractHealthCheckService implements Runnable {
    protected Service service;
    protected Notifier notifier;
    protected boolean lastState;
    private ServiceEventRepository eventRepository;

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
                //TODO: Harvest monthly down count
                this.notifier.notify(new DownServiceEvent(service.getName(), LocalDateTime.now(), serviceStatus, heartbeat.getError(), 0));
                break;
            }
            case SERVICE_RUNNING:
            {
                //TODO: Harvest downtime duration
                this.notifier.notify(new WakeupServiceEvent(service.getName(), LocalDateTime.now(), serviceStatus, heartbeat.getError(), Duration.ofHours(0)));
            }
        }
    }

    protected void saveServiceStatus(Heartbeat heartbeat) {
        String serviceStatus = heartbeat.isOk() ? ServiceStatus.SERVICE_RUNNING.getStringLiteral() : ServiceStatus.SERVICE_STOPPED.getStringLiteral();
        ServiceEvent event = new ServiceEvent(service.getName(), LocalDateTime.now(), serviceStatus, heartbeat.getError());
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
