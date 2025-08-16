package pentapulse.healthcheck.infrastructure.notifications.telegram;

import pentapulse.healthcheck.application.MessageFormatter;
import pentapulse.healthcheck.domain.DownServiceEvent;
import pentapulse.healthcheck.domain.ServiceEvent;
import pentapulse.healthcheck.domain.WakeupServiceEvent;

public class TelegramMessageFormatter implements MessageFormatter {
    public static final String UP_ALERT_TEXT = "✅ Сервис";
    public static final String UP_INTERESTIAL_TEXT = "поднялся!";
    public static final String DOWN_ALERT_TEXT = "⛔ Сервис";
    public static final String DOWN_INTERESTIAL_TEXT = "упал!";


    public static final String DOWNTIME_TEXT = "Downtime:";
    public static final String DOWNCOUNT_TEXT = "Сервис падал за месяц:";

    public String formatBase(ServiceEvent serviceEvent) {
        String result = "";
        if (serviceEvent.getStatus().isRunning()) {
            result += UP_ALERT_TEXT;
            result += " " + serviceEvent.getServiceName();
            result += " " + UP_INTERESTIAL_TEXT;
        } else {
            result += DOWN_ALERT_TEXT;
            result += " " + serviceEvent.getServiceName();
            result += " " + DOWN_INTERESTIAL_TEXT;
        }
        return result;
    }

    @Override
    public String format(WakeupServiceEvent wakeupServiceEvent) {
        String baseFormat = formatBase(wakeupServiceEvent);

        baseFormat += "\n" + DOWNTIME_TEXT + " " + wakeupServiceEvent.getDowntime().toHours() + "ч.";
        return baseFormat;
    }

    @Override
    public String format(DownServiceEvent downServiceEvent) {
        String baseFormat = formatBase(downServiceEvent);

        baseFormat += "\n" + DOWNCOUNT_TEXT + " " + downServiceEvent.getMonthlyDownCount() + " раз(-а)";
        return baseFormat;
    }


}
