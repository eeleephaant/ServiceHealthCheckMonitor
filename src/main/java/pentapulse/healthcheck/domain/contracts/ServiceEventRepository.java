package pentapulse.healthcheck.domain.contracts;

import pentapulse.healthcheck.domain.ServiceEvent;

import java.util.Date;
import java.util.List;

public interface ServiceEventRepository {
    void saveEvent(ServiceEvent event);
    List<ServiceEvent> getEvents(String serviceName, int limit);
    int getDownEventCountForMonth(String serviceName);
    Date getLastDownEventTime(String serviceName);
}
