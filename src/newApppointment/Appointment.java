package newApppointment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class Appointment {

	String DB_URL = "jdbc:mysql://localhost:3306/appointments";
	String USER = "guest";
	String PASS = "guest";

	String name;
	String service;
	Syst syst;
	private boolean status;
	String date;
	String time;
	//status is false if it is not valid anymore
	//ex: if appointment a is booked and we cancel it, the status of AppointmentBooked a is false
	public Appointment(String name, String Service, boolean status, Syst syst, String date, String time) {
		this.name=name;
		this.service=Service;
		this.status=status;
		this.syst=syst;
		this.date = date;
		this.time = time;
	}


	public boolean getStatus() {
		return this.status;
	}

	public void setStatus(boolean newStatus) {
		this.status=newStatus;
	}


	//when an appointment is created it's state is booked
	protected AppointmentBooked createAppointment() {


		saveServiceAndUser(this);
		saveToDataBase(this);
		return (AppointmentBooked) this;
	}

	protected void saveServiceAndUser(Appointment a) {
		String DB_URL = "jdbc:mysql://localhost:3306/appointments";
		String USER = "aly";
		String PASS = "aly";
		try{ Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
		Statement get = conn.createStatement();
		Statement insert = conn.createStatement();
		
		
		String command = "insert into user "
				+" (username)"
				+" values ('"+ a.name +"')";  
		System.out.println(command);
		insert.execute(command);
		System.out.println("insert complete");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} 
		
		try{ Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
		Statement get = conn.createStatement();
		Statement insert = conn.createStatement();
		
		
		
		String command = "insert into service "
				+" (serviceName)"
				+" values ('"+  a.service + "')";  
		System.out.println(command);
		insert.execute(command);
		System.out.println("insert complete");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} 
	}


	protected void saveToDataBase(Appointment a) {
		// Open a connection
		String DB_URL = "jdbc:mysql://localhost:3306/appointments";
		String USER = "aly";
		String PASS = "aly";
		try{ Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
		Statement get = conn.createStatement();
		Statement insert = conn.createStatement();
		
		
		
		String command = "insert into appointments_list "
				+" (type,service,status, userId, date, time)"
				+" values ('" + a.getClass().toString().substring(22) + "','" + a.service + "','" + Boolean.toString(a.getStatus()) + "','" + a.name + "','" + a.date + "','" + a.time + "')";  
		System.out.println(command);
		insert.execute(command);
		System.out.println("insert complete");

		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}

	
	
	protected void updateDataBaseStatus(Appointment a) {
	//	a.status = !a.status;

		try{ Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
		Statement update = conn.createStatement();
		String command = "update appointments_list "
				+ " set Status='" + a.status +"'"
				+ " where Service = '" + a.service + "' and Type = '" + a.getClass().toString().substring(22) + "' ;";
		System.out.println(command);
		update.execute(command);
		} catch (SQLException e) {
			e.printStackTrace();
		} 

	}
	
	protected void updateDataBaseTimeAndDate(Appointment a) {
		//	a.status = !a.status;

			try{ Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement update = conn.createStatement();
			String command = "update appointments_list "
					+ " set Status='" + a.status +"'"
					+ " where Service = '" + a.service + "' and Type = '" + a.getClass().toString().substring(22) + "' ;";
			System.out.println(command);
			update.execute(command);
			} catch (SQLException e) {
				e.printStackTrace();
			} 

		}
	

	protected abstract Appointment CancelAppointment();

	protected abstract AppointmentRunning startAppointment();

	protected abstract AppointmentDone endAppointment();


	static User currentUser;

	public static void setCurrentUser(User user) {
		currentUser = user;
	}

	public static User getCurrentUser() {
		return currentUser;
	}


}






