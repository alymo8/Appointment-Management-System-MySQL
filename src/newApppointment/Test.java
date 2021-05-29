package newApppointment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Test {

	public static void main(String[] args) {

		Syst syst = new Syst();
		
		resetDatabase();
		//valid examples

		System.out.println("--------------------Valid examples-----------------------");

		Appointment app= new  AppointmentBooked("Aly", "Check",true, syst, "2021-10-02", "19:00");	
		AppointmentBooked booked = app.createAppointment();
		AppointmentCanceled canceled = booked.CancelAppointment();


		Appointment app2= new  AppointmentBooked("Loll", "second service", true, syst, "2021-10-05", "13:00");
		Appointment booked2 = app2.createAppointment();
		Appointment started = booked2.startAppointment();
		Appointment done = started.endAppointment();

		System.out.println("\n--------------------Invalid examples---------------------");

		//invalid examples

		Appointment app3= new  AppointmentBooked("Aly", "third service", true, syst, "2021-10-01", "10:00");
		Appointment booked3 = app3.createAppointment();
		Appointment done2 = booked3.endAppointment();
		System.out.println("\n");


		Appointment app4 = new AppointmentBooked("Loll", "fourth service", true, syst, "2021-10-07", "18:00");
		Appointment booked4 = app4.createAppointment();
		Appointment started2 = booked4.startAppointment();
		Appointment canceled2 = started2.CancelAppointment();
		Appointment done3 = canceled2.endAppointment();
			

		Appointment app5 = new AppointmentBooked("Aly", "fifth service", true, syst, "2021-10-09", "11:00");
		Appointment booked5 = app5.createAppointment();
		Appointment done4 = booked5.endAppointment();

	}
	
	public static void resetDatabase() {
		String DB_URL = "jdbc:mysql://localhost:3306/appointments";
		String USER = "aly";
		String PASS = "aly";
		try{ Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
		Statement get = conn.createStatement();
		Statement insert = conn.createStatement();
		
		String test= "truncate table appointments_list;\r\n";
		insert.execute(test);
		test = "commit;\n";
		insert.execute(test);
		test = "truncate table user;\n";
		insert.execute(test);
		test = "commit;\n";
		insert.execute(test);

		System.out.println("truncate complete");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} 
	}


}
