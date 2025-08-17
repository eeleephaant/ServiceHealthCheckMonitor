package pentapulse.healthcheck;

import pentapulse.healthcheck.application.config.Config;
import pentapulse.healthcheck.application.config.ConfigLoader;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            Config config = ConfigLoader.load("./config.yaml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}