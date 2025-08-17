package pentapulse.healthcheck.application;

import pentapulse.healthcheck.domain.ServiceEvent;

public interface Notifier {
    void notify(ServiceEvent serviceEvent);
}
