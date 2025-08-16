package pentapulse.healthcheck.domain;

public class Heartbeat {
    private final boolean ok;
    private final String error;

    public Heartbeat(boolean ok, String error) {
        this.ok = ok;
        this.error = error;
    }

    public boolean isOk() { return ok; }
    public String getError() { return error; }
}
