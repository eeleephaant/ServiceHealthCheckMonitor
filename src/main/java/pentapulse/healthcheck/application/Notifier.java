package pentapulse.healthcheck.application;

import pentapulse.healthcheck.domain.ServiceEvent;

public interface Notifier {
    public void notify(ServiceEvent serviceEvent);
}
