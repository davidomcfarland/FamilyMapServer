package global;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class SqlFile {
    public static void executeUpdate(Connection conn, String path) throws SQLException, FileNotFoundException {
        try (Scanner scanner = new Scanner(new File(path))) {
            while (scanner.hasNext()) {
                try (PreparedStatement stmt = conn.prepareStatement(scanner.useDelimiter(";\\s").next())) {
                    stmt.executeUpdate();
                }
            }
        }
    }
}
