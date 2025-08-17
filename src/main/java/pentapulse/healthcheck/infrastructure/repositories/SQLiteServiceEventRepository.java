package pentapulse.healthcheck.infrastructure.repositories;

import pentapulse.healthcheck.domain.contracts.ServiceEventRepository;
import pentapulse.healthcheck.domain.ServiceEvent;

import java.sql.*;
import java.util.Date;
import java.util.List;

public class SQLiteServiceEventRepository implements ServiceEventRepository {
    private final String dbUrl;

    public SQLiteServiceEventRepository(String dbFilePath) {
        this.dbUrl = "jdbc:sqlite:" + dbFilePath;
        initTable();
    }

    private void initTable() {
        try (Connection conn = DriverManager.getConnection(dbUrl);
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS service_events (" +
                            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                            "service_name TEXT NOT NULL," +
                            "timestamp TEXT NOT NULL," +
                            "status TEXT NOT NULL," +
                            "message TEXT" +
                            ");"
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveEvent(ServiceEvent event) {
        String sql = "INSERT INTO service_events(service_name, timestamp, status, message) VALUES(?,?,?,?)";

        try (Connection conn = DriverManager.getConnection(dbUrl);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, event.getServiceName());
            stmt.setString(2, event.getTimestamp().toString());
            stmt.setString(3, event.getStatus().getStringLiteral());
            stmt.setString(4, event.getMessage());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ServiceEvent> getEvents(String serviceName, int limit) {
        // TODO: Implement this
        return List.of();
    }

    @Override
    public int getDownEventCountForMonth(String serviceName) {
        // TODO: Implement this
        return 0;
    }

    @Override
    public Date getLastDownEventTime(String serviceName) {
        // TODO: Implement this
        return null;
    }
}
