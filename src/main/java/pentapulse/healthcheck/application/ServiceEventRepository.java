package pentapulse.healthcheck.application;

import pentapulse.healthcheck.domain.ServiceEvent;

import java.util.List;

public interface ServiceEventRepository {
    void saveEvent(ServiceEvent event);
    List<ServiceEvent> getEvents(String serviceName, int limit);
}
