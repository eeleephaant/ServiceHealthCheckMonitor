package pentapulse.healthcheck.domain;

public class Service {
    private final String name;
    private final String url;

    public Service(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() { return name; }
    public String getUrl() { return url; }
}
