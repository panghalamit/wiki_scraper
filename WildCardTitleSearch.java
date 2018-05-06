import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.io.IOException;
import java.sql.ResultSetMetaData;
class WildCardTitleSearch {

static Connection conn = null;
static PreparedStatement pstmt = null;
static ResultSet rs = null;
  public static void main(String[] args) {
    try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception ex) {
            // handle the error
	    System.out.println("Where is Jdbc?");
	    ex.printStackTrace();
	    return;
        }
    try {
        conn =
           DriverManager.getConnection("jdbc:mysql://localhost:3306/wiki", "root", "vanwilder");

        // Do something with the Connection
        System.out.println("Input title search keywords");

	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	
	String article_title = reader.readLine();
	pstmt = conn.prepareStatement("SELECT Title FROM articles WHERE Title Like ?");
	pstmt.setString(1, article_title+"%");
	rs = pstmt.executeQuery();
	ResultSetMetaData rs_md = rs.getMetaData();
        int num_columns = rs_md.getColumnCount();
	while(rs.next()) {
		for(int i = 1; i<=num_columns; i++) {
			if(i != 1) System.out.print("\n");
			System.out.println(rs_md.getColumnName(i));
			System.out.println(rs.getString(i));
		}
		System.out.print("\n");
	}
    } catch (SQLException ex) {
        // handle any errors
        System.out.println("SQLException: " + ex.getMessage());
        System.out.println("SQLState: " + ex.getSQLState());
        System.out.println("VendorError: " + ex.getErrorCode());
    } catch (IOException io) {
	 io.printStackTrace();
	}
  }

}
