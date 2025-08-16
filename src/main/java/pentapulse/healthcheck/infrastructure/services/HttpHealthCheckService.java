package pentapulse.healthcheck.infrastructure.services;

import pentapulse.healthcheck.application.AbstractHealthCheckService;
import pentapulse.healthcheck.application.Notifier;
import pentapulse.healthcheck.application.ServiceEventRepository;
import pentapulse.healthcheck.domain.Heartbeat;
import pentapulse.healthcheck.domain.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpHealthCheckService extends AbstractHealthCheckService {

    public HttpHealthCheckService(Service service, ServiceEventRepository eventRepository, Notifier notifier) {
        super(service, eventRepository, notifier);
    }

    @Override
    protected Heartbeat ping() {
        try {
            URL url = new URL(service.getUrl());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(3000);
            connection.setReadTimeout(3000);

            int responseCode = connection.getResponseCode();
            if (responseCode >= 200 && responseCode < 300) {
                return new Heartbeat(true, null);
            } else {
                return new Heartbeat(false, "HTTP error code: " + responseCode);
            }

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            return new Heartbeat(false, e.getMessage());
        }
    }
}
