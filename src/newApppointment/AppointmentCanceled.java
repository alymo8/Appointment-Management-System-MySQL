package newApppointment;

public class AppointmentCanceled extends Appointment {
	Syst syst;
	public AppointmentCanceled(String name, String service, boolean status, Syst syst, String date, String time) {
		super(name, service, status, syst, date, time);

	}

	protected boolean CreateAppointment(String name, String service, String status) {
		// TODO Auto-generated method stub
		System.out.println("This appointment was already canceled, please reserve a new appointment");
		return false;
	}
	


	@Override
	protected AppointmentCanceled CancelAppointment() {
		// TODO Auto-generated method stub
		
		for(Appointment app: this.syst.getAppointments()) {
			if(app.name.equals(this.name) && app.service.equals(this.service) && app instanceof AppointmentCanceled) {
			System.out.println("This appointment was already canceled");
			return this;
			}
		}
		
		
		return this;
	}

	@Override
	protected AppointmentRunning startAppointment() {
		// TODO Auto-generated method stub
		
		for(Appointment app: this.syst.getAppointments()) {
			if(app.name.equals(this.name) && app.service.equals(this.service) && app instanceof AppointmentCanceled) {
			System.out.println("This appointment was already canceled, it cannot be started");
			
			}
		}
		
		return null;
	}

	@Override
	protected AppointmentDone endAppointment() {
		// TODO Auto-generated method stub
	
		for(Appointment app: this.syst.getAppointments()) {
			if(app.name.equals(this.name) && app.service.equals(this.service) && app instanceof AppointmentCanceled) {
			System.out.println("This appointment was already canceled, it cannot be ended");
			}
		}
		return null;
	}
	
	
	

}
