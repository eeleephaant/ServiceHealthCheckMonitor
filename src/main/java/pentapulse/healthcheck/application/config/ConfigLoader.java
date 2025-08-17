package pentapulse.healthcheck.application.config;

import org.yaml.snakeyaml.Yaml;

import java.io.FileReader;
import java.io.IOException;

public class ConfigLoader {
    public static Config load(String path) throws IOException {
        Yaml yaml = new Yaml();
        FileReader fileReader = new FileReader(path);

        return yaml.loadAs(fileReader, Config.class);
    }
}
