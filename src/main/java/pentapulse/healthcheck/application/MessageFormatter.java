package pentapulse.healthcheck.application;

import pentapulse.healthcheck.domain.DownServiceEvent;
import pentapulse.healthcheck.domain.ServiceEvent;
import pentapulse.healthcheck.domain.WakeupServiceEvent;

public interface MessageFormatter {
    public String format(WakeupServiceEvent wakeupServiceEvent);
    public String format(DownServiceEvent downServiceEvent);
}
