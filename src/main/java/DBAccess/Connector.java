package DBAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Used to setup a new connection for the database
 */
public class Connector {

    private static String URL;
    private static String USERNAME;
    private static String PASSWORD;

    private static Connection singleton;

    public static void setConnection( Connection con ) {
        singleton = con;
    }

    /**
     * <p>Creates a database connection if it does not exists or the connection is closed</p>
     * @return Connection object
     */
    public static Connection connection() throws ClassNotFoundException, SQLException {
        if ( singleton == null || singleton.isClosed() ) {
            setDBCredentials();
            Class.forName( "com.mysql.cj.jdbc.Driver" );
            singleton = DriverManager.getConnection( URL, USERNAME, PASSWORD );
        }
        return singleton;
    }

    /**
     * <p>Used to setup the DB Credentials.</p>
     * <p>If it's deployed it uses the environment variables></p>
     * <p>If not, it uses the hardcoded test database</p>
     */
    public static void setDBCredentials() {
        String deployed = System.getenv("DEPLOYED");

        if ( deployed != null ) {
            // Prod: hent variabler fra setenv.sh
            URL = System.getenv("JDBC_CONNECTION_STRING");
            USERNAME = System.getenv( "JDBC_USER");
            PASSWORD = System.getenv("JDBC_PASSWORD");
        } else {
            // Localhost
            URL = "jdbc:mysql://localhost:3306/FogByggemarked?serverTimezone=UTC";
            USERNAME = "mario";
            PASSWORD = "mario123";
        }
    }

}
