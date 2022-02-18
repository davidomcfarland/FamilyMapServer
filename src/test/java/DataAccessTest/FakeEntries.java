package DataAccessTest;

import global.SqlFile;
import org.junit.jupiter.api.Assertions;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;

import static global.ProjectData.dbPath;

public class FakeEntries {

    private static String basePath = "sql_files" + File.separator + "test" + File.separator;

    public static void fill() {
        Connection conn = null;
        try (Connection c = DriverManager.getConnection(dbPath)) {
            conn = c;

            SqlFile.executeUpdate(conn, basePath + "FillTables.sql");

        } catch (Exception ex) {
            Assertions.fail("allPrep Fail:\n" + ex.getMessage());
        }
    }

    public static void remove() {
        Connection conn = null;
        try (Connection c = DriverManager.getConnection(dbPath)) {
            conn = c;

            SqlFile.executeUpdate(conn, basePath + "RemoveFromTables.sql");

        } catch (Exception ex) {
            Assertions.fail("allPrep Fail:\n" + ex.getMessage());
        }
    }
}
