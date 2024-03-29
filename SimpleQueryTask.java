import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.sql.Statement;
import java.sql.ResultSet;
import java.io.IOException;
import java.sql.ResultSetMetaData;
class SimpleQueryTask {

static Connection conn = null;
static Statement stmt = null;
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
        System.out.println("Input article number between 1 and 10000");

	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	
	String article_num = reader.readLine();
	if(Integer.parseInt(article_num) > 10000 || Integer.parseInt(article_num) < 1) {
		System.out.println("Invalid number");
		return;
	}
	stmt = conn.createStatement();
	rs = stmt.executeQuery("SELECT Title, Body From articles Where id="+article_num);
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
