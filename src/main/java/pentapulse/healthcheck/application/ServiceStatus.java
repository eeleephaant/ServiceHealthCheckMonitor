package pentapulse.healthcheck.application;

public enum ServiceStatus {
    SERVICE_RUNNING("UP", true),
    SERVICE_STOPPED("DOWN", false);

    private final String stringLiteral;
    private final boolean isRunning;

    ServiceStatus(String stringLiteral, boolean isRunning) {
        this.stringLiteral = stringLiteral;
        this.isRunning = isRunning;
    }

    public String getStringLiteral() {
        return stringLiteral;
    }

    public boolean isRunning() {
        return isRunning;
    }
}