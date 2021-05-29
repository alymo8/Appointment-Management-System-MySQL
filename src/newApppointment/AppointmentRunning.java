package newApppointment;

public class AppointmentRunning extends Appointment{

	public AppointmentRunning(String name, String service, boolean status, Syst syst, String date, String time) {
		super(name, service,status, syst, date, time);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Appointment CancelAppointment() {
		// TODO Auto-generated method stub
		
		for(Appointment app: this.syst.getAppointments()) {
			if(app.name.equals(name) && app.service.equals(service) && app instanceof AppointmentRunning) {
				
				System.out.println("The appointment has already started, it cannot be canceled");
			}
		}
		
		return this;
	}

	@Override
	protected AppointmentRunning startAppointment() {
		// TODO Auto-generated method stub
		
		for(Appointment app: this.syst.getAppointments()) {
			if(app.name.equals(name) && app.service.equals(service) && app instanceof AppointmentRunning) {
				
				System.out.println("The appointment has already started");
			}
		}
		return null;
	}

	@Override
	protected AppointmentDone endAppointment() {
		// TODO Auto-generated method stub
		for(Appointment app: this.syst.getAppointments()) { 
			if(app.name.equals(name) && app.service.equals(service) && app instanceof AppointmentRunning) {
				app.setStatus(false);
				updateDataBaseStatus(app);
				AppointmentDone done = new AppointmentDone(name,service,true, syst, date, time);
				syst.addAppointment(done);
				System.out.println("Appointment succesfuly ended");
				saveToDataBase(done);
				return done;
			}
		}
		return null;
	}

}
