package newApppointment;

public class AppointmentBooked extends Appointment{

	
	public AppointmentBooked(String name, String service, boolean status, Syst syst, String date, String time) {
		super(name, service,status, syst, date, time);
		
	}

	@Override
	protected AppointmentCanceled CancelAppointment() {
		
		for(Appointment app: this.syst.getAppointments()) {
			if(app.name.equals(this.name) && app.service.equals(this.service) && app instanceof AppointmentBooked) {
				app.setStatus(false);
				updateDataBaseStatus(app);
				AppointmentCanceled canceled = new AppointmentCanceled(this.name, this.service, true, this.syst, date, time);
				syst.addAppointment(canceled);
				System.out.println("The appointment was canceled");
				saveToDataBase(canceled);
				return canceled;
			}
		
		}
		
		return null;
	}

	@Override
	protected AppointmentRunning startAppointment() {
		
		for(Appointment app: this.syst.getAppointments()) {
			if(app.name.equals(this.name) && app.service.equals(this.service) && app instanceof AppointmentBooked) {
				
				AppointmentRunning running = new AppointmentRunning(name, service, true, syst, date, time);
				syst.addAppointment(running);
				System.out.println("The appointment has succesfuly started");
				app.setStatus(false);
				updateDataBaseStatus(app);
				saveToDataBase(running);
				return running;
			}
		}
		return null;
		
	}

	@Override
	protected AppointmentDone endAppointment() {
		// TODO Auto-generated method stub
		for(Appointment app: this.syst.getAppointments()) {
			if(app.name.equals(this.name) && app.service.equals(this.service) && app instanceof AppointmentBooked) {
			System.out.println("This appointment has not started yet");
			}
		}
		return null;
	}



}
