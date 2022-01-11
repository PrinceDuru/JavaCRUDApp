package game_package;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {
	
		// Instantiate the Logger class
        private static final Logger logger = Logger.getLogger(Database.class.getName());
        
        // Set database parameters as constant parameters
        private static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
        private static final String DB_CONNECTION = "jdbc:oracle:thin:@hostname:SQLD";
        private static final String DB_USER = "db_user";
        private static final String DB_PASSWORD = "db_password";
        
        private Database() {
                
        }
        
        public static Connection getDBConnection() throws SQLException {
                Connection connection = null;

                try {
                        Class.forName(DB_DRIVER);
                } catch (ClassNotFoundException exception) {
                        logger.log(Level.SEVERE, exception.getMessage());
                }

                try {
                        connection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
                        return connection;
                } catch (SQLException exception) {
                        logger.log(Level.SEVERE, exception.getMessage());
                }

                return connection;
        }
}