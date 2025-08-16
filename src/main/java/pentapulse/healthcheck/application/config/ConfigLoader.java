package pentapulse.healthcheck.application.config;

import java.io.IOException;

public class ConfigLoader {
    public static Config load(String path) throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        return mapper.readValue(new File(path), Config.class);
    }
}
