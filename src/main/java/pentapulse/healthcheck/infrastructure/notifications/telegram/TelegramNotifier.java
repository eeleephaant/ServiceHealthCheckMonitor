package pentapulse.healthcheck.infrastructure.notifications.telegram;

import pentapulse.healthcheck.application.Notifier;
import pentapulse.healthcheck.domain.DownServiceEvent;
import pentapulse.healthcheck.domain.ServiceEvent;
import pentapulse.healthcheck.domain.WakeupServiceEvent;
import pentapulse.healthcheck.infrastructure.notifications.telegram.settings.TelegramNotifierSettings;

public class TelegramNotifier implements Notifier {
    private final TelegramNotifierSettings settings;
    static final TelegramMessageFormatter formatter = new TelegramMessageFormatter();

    public TelegramNotifier(TelegramNotifierSettings settings) {
        this.settings = settings;
    }

    @Override
    public void notify(ServiceEvent serviceEvent) {
        String messageFormatted = "";

        if (serviceEvent instanceof DownServiceEvent downServiceEvent) {
            messageFormatted = formatter.format(downServiceEvent);
        } else if (serviceEvent instanceof WakeupServiceEvent wakeupServiceEvent) {
            messageFormatted = formatter.format(wakeupServiceEvent);
        } else {
            throw new IllegalArgumentException("Unknown service event: " + serviceEvent);
        }

        for (String recipient : settings.recipients) {
            sendMessage(recipient, messageFormatted);
        }
    }

    public void sendMessage(String message, String recipient) {
        //TODO: Add implementation
    }
}
