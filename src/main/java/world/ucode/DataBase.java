package world.ucode;

import java.sql.*;

public class DataBase {
    private String url = "jdbc:sqlite:src/main/resources/world/ucode/db.sqlite";
    String sql = "CREATE TABLE IF NOT EXISTS character (\n"
            + "name text NOT NULL,\n"
            + "type int NOT NULL,\n"
            + "health double,\n"
            + "happiness double,\n"
            + "hunger double,\n"
            + "thirst double,\n"
            + "cleanliness double\n"
            + ");";

    public void createDB() {
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                meta.getDriverName();
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void createTable() {
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private Connection connect() {
        // SQLite connection string
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void insert(String name, int type, double health, double hunger,
                       double thirst, double cleanliness, double happiness) {
        String sql = "INSERT INTO character(name,type,health,hunger,thirst,cleanliness,happiness) VALUES(?,?,?,?,?,?,?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, type);
            pstmt.setDouble(3, health);
            pstmt.setDouble(4, hunger);
            pstmt.setDouble(5, thirst);
            pstmt.setDouble(6, cleanliness);
            pstmt.setDouble(7, happiness);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public ResultSet selectAll(){
        String sql = "SELECT name, type, health, hunger, thirst, cleanliness, happiness FROM character";
        ResultSet rs = null;
        try {
            Connection conn = this.connect();
            Statement stmt  = conn.createStatement();
            rs = stmt.executeQuery(sql);

        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return rs;
    }
    public void deleteRow(String name, int type) {
        String sql = "DELETE FROM character WHERE name = ?";
        Connection conn = this.connect();
        try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1 ,name);
            pstmt.executeUpdate();
        } catch(Exception e) {
            System.out.println(e);
        }
    }
}
