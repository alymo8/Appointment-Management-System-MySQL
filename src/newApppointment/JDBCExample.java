package newApppointment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;

public class JDBCExample {
   static final String DB_URL = "jdbc:mysql://localhost:3306/";
   static final String USER = "guest";
   static final String PASS = "guest";

   public static void main(String[] args) {
	      // Open a connection
	      try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
	         Statement stmt = conn.createStatement();
	      ) {		      
	         String sql = "CREATE DATABASE Appointments";
	         stmt.executeUpdate(sql);
	         System.out.println("Database created successfully...");   	  
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } 
	   }
//   public static void main(String[] args) {
//      // Open a connection
//      try{ Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
//         Statement get = conn.createStatement();
//         Statement insert = conn.createStatement();
//         String command = "insert into info "
//        		 +" (id,name)"
//        		 +" values ('234535466','helloooo')"; 
//      insert.execute(command);
//      System.out.println("insert complete");
//         
//    		 ResultSet myRs = get.executeQuery("select * from info");
//
//    	while(myRs.next()) {
//       		System.out.println(myRs.getString("id") + ", " + myRs.getString("name"));
//       	}
//      Statement create = conn.createStatement();
//         String sql = "CREATE DATABASE Appointments";
//         ResultSet myRs = create.executeQuery(sql);
//          System.out.println("Created with success");
//      } catch (SQLException e) {
//         e.printStackTrace();
//      } 
//   }
}