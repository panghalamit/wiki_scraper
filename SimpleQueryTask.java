import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class SimpleQueryTask {

Connection conn = null;

  public static void main() {

    try {
        conn =
           DriverManager.getConnection("jdbc:mysql://localhost/test?" +
                                       "user=root&password=vanwilder");

        // Do something with the Connection
        System.out.println("Works");

    } catch (SQLException ex) {
        // handle any errors
        System.out.println("SQLException: " + ex.getMessage());
        System.out.println("SQLState: " + ex.getSQLState());
        System.out.println("VendorError: " + ex.getErrorCode());
    }
  }

}
