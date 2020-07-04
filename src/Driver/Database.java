package Driver;
/*
package Class;
import java.sql.*;

public class Database {
	
	
	public static void main(String[] args) throws Exception
	{
		String url = "jdbc:mysql://localhost:3306/dealership?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=GMT";
		String username = "root";
		String pass = "root";
		String query = "select username from staff where staffid=1";
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, username, pass);
		
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		
		rs.next();
		String namefromdb = rs.getString("username");
		
		System.out.println(namefromdb);
		
		st.close();
		con.close();
	}
	
    
}

*/