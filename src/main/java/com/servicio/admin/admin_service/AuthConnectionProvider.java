import org.springframework.stereotype.Component;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

@Component
public class AuthConnectionProvider {
    
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/university",
            "root",
            "1234"
        );
    }
}