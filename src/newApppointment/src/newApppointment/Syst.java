package newApppointment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Syst {

	  private List<Appointment> appointments;
	
	public Syst() {
		appointments = new ArrayList<Appointment>();
		
	}
	
	 public Appointment getAppointment(int index)
	  {
	    Appointment appointment = appointments.get(index);
	    return appointment;
	  }
	

	
	 public boolean removeAppointment(Appointment a) {
			for(Appointment all: getAppointments()) {
				if(all.equals(a)) {
					getAppointments().remove(a);
					return true;
				}
			}
			
			return false;
			
		}
	
	

	
	public List<Appointment> getAppointments()
	  {
	    return appointments;
	  }
	
	
	 public void addAppointment(Appointment a)
	  {
		 appointments.add(a);
	    
	  }

	

}
