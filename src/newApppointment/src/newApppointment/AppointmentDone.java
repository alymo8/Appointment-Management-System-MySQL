package newApppointment;

public class AppointmentDone extends Appointment{

	public AppointmentDone(String name, String service, boolean status, Syst syst, String date, String time) {
		super(name, service, status, syst, date, time);
	}

	@Override
	protected AppointmentCanceled CancelAppointment() {
		// TODO Auto-generated method stub
		for(Appointment app: this.syst.getAppointments()) {
			if(app.name.equals(this.name) && app.service.equals(this.service) && app instanceof AppointmentDone) {

				System.out.println("The appointment has already ended, it cannot be canceled");
			}
		}
		return null;
	}

	@Override
	protected AppointmentRunning startAppointment() {
		// TODO Auto-generated method stub
		for(Appointment app: this.syst.getAppointments()) {
			if(app.name.equals(this.name) && app.service.equals(this.service) && app instanceof AppointmentDone) {

				System.out.println("The appointment has already ended");
			}
		}

		return null;
	}

	@Override
	protected AppointmentDone endAppointment() {
		// TODO Auto-generated method stub

		for(Appointment app: this.syst.getAppointments()) {
			if(app.name.equals(this.name) && app.service.equals(this.service) && app instanceof AppointmentDone) {

				System.out.println("The appointment has already ended");

			}
		}

		return null;
	}

}
