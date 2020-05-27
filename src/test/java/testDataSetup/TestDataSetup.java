package testDataSetup;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;
import DBAccess.Connector;
import org.junit.Before;

/**
 *
 * Used to setup the database for testing
 */
public class TestDataSetup {

    private ArrayList<String> DBSetUp = scanFromFile("DBTestData.sql");

    public TestDataSetup() {
    }

    public ArrayList<String> scanFromFile(String filename) {
        ArrayList<String> lines = new ArrayList();
        try {
            Scanner scan = new Scanner(new File("SQL/" + filename));
            scan.useDelimiter(Pattern.compile(";"));
            while (scan.hasNext()) {
                lines.add(scan.next());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    protected void rebuildDB() {
        try {
            Connection connection = Connector.connection();
            Statement stmt = connection.createStatement();
            for (String sqlStatement : DBSetUp) {
                if (!sqlStatement.isEmpty()) {
                    stmt.executeUpdate(sqlStatement);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
        }
    }

    @Before
    public void setUpDB() {
        rebuildDB();
    }
}