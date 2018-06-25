package sample;

import com.sun.rowset.CachedRowSetImpl;

import java.sql.*;

public class DBUtil {
    private static final String url = "jdbc:postgresql://localhost:5555/student";
    private static final String user = "student";
    private static final String password = "wsiz#1234";
    private static final String JDBC_DRIVER = "org.postgresql.Driver";
    private static Connection conn = null;


    /**
     * Connect to the PostgreSQL database
     *
     * @return a Connection object
     */
    public static Connection dbConnect() throws ClassNotFoundException {

        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("Gdzie jest twój Sterownik JDBC ?");
            e.printStackTrace();
            throw e;
        }

        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }

    public static void dbDisconnect() throws SQLException {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Disconnected with the PostgreSQL server successfully.");
            }
        }catch (SQLException e){
            throw e;
        }
    }
    // Wykonanie zapytania do bazy danych
    public static ResultSet dbExecuteQuery(String queryStmt) throws SQLException, ClassNotFoundException {
        Statement stmt = null;
        ResultSet resultSet = null;
        CachedRowSetImpl crs = null;
        try {
            // Połączenie z bazą danych, utworzenie zapytaia, wykonanie zapytania
            dbConnect();
            stmt = conn.createStatement();
            resultSet = stmt.executeQuery(queryStmt);
            crs = new CachedRowSetImpl();
            crs.populate(resultSet);
        }catch (SQLException e){
            System.out.println("Problem accurred at executeQuery operation: " + e);
            throw  e;
        }finally {
            if (resultSet != null) resultSet.close();
            if (stmt != null) stmt.close();
            dbDisconnect();
        }
        return crs;
    }
    // Wykonanie aktualizacji do bazy danych
    public static void dbExecuteUpdate (String sqlStmt) throws SQLException, ClassNotFoundException {
        Statement stmt = null;
        try {
            dbConnect();
            stmt = conn.createStatement();
            stmt.executeUpdate(sqlStmt);
        }catch (SQLException e) {
            System.out.println("Problem accured at executeUpdate operation: " + e);
            throw e;
        }finally {
            if (stmt != null){
                stmt.close();
            }
            dbDisconnect();
        }
    }
    // Wykonanie zapytania do bazy danych
    public static ResultSet  dbPreparedStatementExecuteQuery(String sqlstmt) throws SQLException, ClassNotFoundException {
        PreparedStatement pst = null;
        ResultSet rs = null;
        CachedRowSetImpl crs = null;
        try {
            dbConnect();
            pst = conn.prepareStatement(sqlstmt);
            rs = pst.executeQuery();
            crs = new CachedRowSetImpl();
            crs.populate(rs);
        }catch (SQLException e) {
            System.out.println("Problem accured at executeQuery (PreparedStatement): \n" + e);
            throw e;
        }finally {
            if (pst != null)pst.close();
            dbDisconnect();
        }
        return crs;
    }

    public static void getParts() {

        String SQL = "SELECT * FROM serwispc1.czesci";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SQL)) {
            // display actor information
            displayParts(rs);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void displayParts(ResultSet rs) throws SQLException {
        while (rs.next()) {
            System.out.println(
                            rs.getString("id_czesc") + "\t" +
                            rs.getString("nazwa") + "\t" +
                            rs.getString("koszt") + "\t" +
                            rs.getString("czasnaprawy") +  "\t" +
                            rs.getString("sposobnaprawy"));

        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        DBUtil dbUtil = new DBUtil();
        dbUtil.dbConnect();
        dbUtil.getParts();
        dbUtil.dbDisconnect();

    }
}
