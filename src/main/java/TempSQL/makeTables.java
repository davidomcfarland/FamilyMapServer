package TempSQL;

import java.io.File;
import java.sql.*;
import java.util.Scanner;

public class makeTables {
    public makeTables(){
        // do nothing
    }

     public static void main(String[] args) {
        Connection conn = null;
        try (Connection c = DriverManager.getConnection("jdbc:sqlite:db/database.db")){
            conn = c;
            File file = new File("db/CreateTables.sql");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()){
                try (PreparedStatement stmt = conn.prepareStatement(scanner.useDelimiter(";\\s*").next())){
                    stmt.executeUpdate();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}
