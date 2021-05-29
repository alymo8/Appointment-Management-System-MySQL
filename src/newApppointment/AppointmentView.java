package newApppointment;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.mysql.cj.xdevapi.Statement;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;


public class AppointmentView extends Application{

	static final String DB_URL = "jdbc:mysql://localhost:3306/";
	static final String USER = "guest";
	static final String PASS = "guest";


	Stage window;
	private Syst syst = new Syst();
	static Scene homeS;
	private ObservableList<Node> elementsOnScreen = null;



	@Override
	public void start(Stage stage) throws Exception {

		window = stage;
		File home = new File("src/newApppointment/loginS.fxml");
		String homepath = home.getAbsolutePath();

		Parent root = FXMLLoader.load(new File(homepath).toURI().toURL());
		homeS = new Scene(root, 500, 650);

		window.setTitle("FXML Welcome");
		window.setScene(homeS);
		window.show();
	}

	public Syst getSyst() {
		return this.syst;
	}

	@FXML
	private TextArea appointments;

	@FXML
	private TableView<Product> table;


	@FXML
	private TableColumn<Product, String> name_col;

	@FXML
	private TableColumn<Product, String> type_col;

	@FXML
	private TableColumn<Product, String> date_col;

	@FXML
	private TableColumn<Product, String> time_col;

	@FXML
	private TableColumn<Product, String> service_col;


	ObservableList<Product>  list = FXCollections.observableArrayList();

	@FXML
	void showAppointments(ActionEvent event) {

		name_col.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
		date_col.setCellValueFactory(new PropertyValueFactory<Product, String>("date"));
		time_col.setCellValueFactory(new PropertyValueFactory<Product, String>("time"));
		service_col.setCellValueFactory(new PropertyValueFactory<Product, String>("service"));

		table.setItems(getProduct());
	}

	@FXML
	void goToLogin(ActionEvent event) throws MalformedURLException, IOException {
		switchScene(new File("src/newApppointment/loginS.fxml"), (Stage) ((Node) event.getSource()).getScene().getWindow());
	}

	public void switchScene(File file, Stage window) throws MalformedURLException, IOException {
		String homepath = file.getAbsolutePath();

		Parent root = FXMLLoader.load(new File(homepath).toURI().toURL());
		homeS = new Scene(root, 500, 650);

		window.setTitle("FXML Welcome");
		window.setScene(homeS);
	}


	@FXML
	private TextField username;

	@FXML
	private TextField time;

	@FXML
	private DatePicker date;

	@FXML
	void endAppointment(ActionEvent event) {

		String text = "";
		try{ Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
		java.sql.Statement get = conn.createStatement();

		Product product = table.getSelectionModel().getSelectedItem();
		
		ResultSet myRs = get.executeQuery("select * from appointments.appointments_list"
				+ " where userId = '" + product.getName()
				+ "' and time = '" + product.getTime() + "' and date = '"+ product.getDate() + "' ;");

		while(myRs.next()) {
			text+= (myRs.getString("userid") + " " + myRs.getString("time") + " " + myRs.getString("date") + " " + myRs.getString("service")+ " ");
		}

		String[] fields = text.split(" ");

		String command = "update appointments.appointments_list "
				+ " set Status='false'"
				+ " where time = '" + fields[1] + "' and date = '" + fields[2] + "' and userId = '" + fields[0] + "' ;";
		System.out.println(command);

		java.sql.Statement update = conn.createStatement();
		update.execute(command);

		Appointment end = new AppointmentDone(fields[0], fields[3] + fields[4], true, getSyst(), fields[2], fields[1]);
		end.saveToDataBase(end);

		}
		catch(SQLException e) {
			e.printStackTrace();
		}


	}

	@FXML
	void goToHome(ActionEvent event) throws MalformedURLException, IOException {
		switchScene(new File("src/newApppointment/appointments.fxml"), (Stage) ((Node) event.getSource()).getScene().getWindow());
	}

	@FXML
	void startAppointment(ActionEvent event) {

		String text = "";
		try{ Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
		java.sql.Statement get = conn.createStatement();

		Product product = table.getSelectionModel().getSelectedItem();
		
		ResultSet myRs = get.executeQuery("select * from appointments.appointments_list"
				+ " where userId = '" + product.getName()
				+ "' and time = '" + product.getTime() + "' and date = '"+ product.getDate() + "' ;");

		while(myRs.next()) {
			text+= (myRs.getString("userid") + " " + myRs.getString("time") + " " + myRs.getString("date") + " " + myRs.getString("service")+ " ");
		}

		String[] fields = text.split(" ");

		String command = "update appointments.appointments_list "
				+ " set Status='false'"
				+ " where time = '" + fields[1] + "' and date = '" + fields[2] + "' and userId = '" + fields[0] + "' ;";
		System.out.println(command);
		java.sql.Statement update = conn.createStatement();
		update.execute(command);

		Appointment running = new AppointmentRunning(fields[0], fields[3] + fields[4], true, getSyst(), fields[2], fields[1]);
		running.saveToDataBase(running);

		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private TextField name;

	@FXML
	private ComboBox<?> service;

	@FXML
	void bookAppointment(ActionEvent event) {
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());    	
		if(date.getValue().toString().compareTo(timeStamp) < 0) popupError("The date is invalid");
		
		Appointment a = new AppointmentBooked(Appointment.getCurrentUser().getUsername(), service.getValue().toString(), true, getSyst(), date.getValue().toString(), time.getText());
		a.createAppointment();
	}

	@FXML
	void cancelAppointment(ActionEvent event) {

		String text = "";
		try{ Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
		java.sql.Statement get = conn.createStatement();

		Product product = table.getSelectionModel().getSelectedItem();
		
		ResultSet myRs = get.executeQuery("select * from appointments.appointments_list"
				+ " where userId = '" + Appointment.getCurrentUser().getUsername()
				+ "' and time = '" + product.getTime() + "' and date = '"+ product.getDate() + "' ;");

		while(myRs.next()) {
			text+= (myRs.getString("userid") + " " + myRs.getString("time") + " " + myRs.getString("date") + " " + myRs.getString("service")+ " ");
		}

		String[] fields = text.split(" ");

		String command = "update appointments.appointments_list "
				+ " set Status='false'"
				+ " where time = '" + fields[1] + "' and date = '" + fields[2] + "' and userId = '" + fields[0] + "' ;";

		java.sql.Statement update = conn.createStatement();
		update.execute(command);
		System.out.println(command);

		Appointment canceled = new AppointmentCanceled(fields[0], fields[3] + " " + fields[4], true, getSyst(), fields[2], fields[1]);
		canceled.saveToDataBase(canceled);

		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		catch(Exception e) {
			popupError(e.getMessage());
		}
	}

	private Node getElementbyID(String id) {

		for (Node node : elementsOnScreen) {
			if (node.getId() != null) {

				if (node.getId().equals(id)) {
					System.out.println("node: " + node.getId());
					return node;
				}
			}
		}

		return null;
	}

	@FXML
	private ComboBox<?> viewAppointments;

	@SuppressWarnings("unchecked")
	@FXML
	void initScreenElements(MouseEvent event) throws MalformedURLException, IOException {


		if (elementsOnScreen == null) {
			Pane db = (Pane) event.getSource();
			elementsOnScreen = db.getChildren();

			ComboBox cb = (ComboBox) getElementbyID("viewAppointments");

			cb.getItems().removeAll(cb.getItems());
			cb.getItems().add("Booked");
			cb.getItems().add("Running");
			cb.getItems().add("Canceled");
			cb.getItems().add("Done");			

			elementsOnScreen = db.getChildren();

			ComboBox cb2 = (ComboBox) getElementbyID("service");

			cb2.getItems().removeAll(cb.getItems());
			cb2.getItems().add("Engine Check");
			cb2.getItems().add("Tyre Change");
			cb2.getItems().add("General Check");
			cb2.getItems().add("Other");			
		}

	}

	@SuppressWarnings("unchecked")
	@FXML
	void initScreenElements2(MouseEvent event) throws MalformedURLException, IOException {

		if (elementsOnScreen == null) {
			Pane db = (Pane) event.getSource();
			elementsOnScreen = db.getChildren();

			ComboBox cb = (ComboBox) getElementbyID("viewAppointments");

			cb.getItems().removeAll(cb.getItems());
			cb.getItems().add("Booked");
			cb.getItems().add("Running");
			cb.getItems().add("Canceled");
			cb.getItems().add("Done");			
		}
	}



	public ObservableList<Product> getProduct() {
		try{
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

			java.sql.Statement get = conn.createStatement();



			ObservableList<Product> list = FXCollections.observableArrayList();
			ResultSet myRs;
			if(Appointment.getCurrentUser().getUsername().equals("owner")) {
				myRs = get.executeQuery("select * from appointments.appointments_list"
						+ " where status = 'true' and type = 'Appointment" + viewAppointments.getValue().toString() + "';");
			}
			else {
				myRs = get.executeQuery("select * from appointments.appointments_list"
						+ " where  userId = '" + Appointment.getCurrentUser().getUsername() + "' and status = 'true' and type = 'Appointment" + viewAppointments.getValue().toString() + "';");
			}

			while(myRs.next()) {
				list.add(new Product(myRs.getString("userid"), 
						myRs.getString("type"), myRs.getString("date"), myRs.getString("time"), myRs.getString("service")));
			}

			return list;

		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@FXML
	private PasswordField password;

	@FXML
	void doSignUp(ActionEvent event) throws SQLException {
		try{
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);			
			
			java.sql.Statement insert = conn.createStatement();
			System.out.println("insert into appointments.user"
					+ " values '" + username.getText() + "', '" + password.getText() +  "' ;");
			
			String command ="insert into appointments.user (username,password)"
					+ " values ('" + username.getText() + "', '" 
					+ password.getText() +  "') ;";
			
			insert.execute(command);
			
		}catch(SQLException e) {
			System.out.println(e.getMessage());
			popupError("This username already exists");
		}

	}

	

	public void popupError(String text) {
		Alert box = new Alert(AlertType.ERROR);
		box.setHeaderText(null);
		box.setContentText(text);
		box.show();
	}

	@FXML
	void login(ActionEvent event) throws MalformedURLException, IOException {

		if(username.getText().equals("owner") && password.getText().equals("owner")) {
			Appointment.setCurrentUser(new User("owner", "owner"));
			switchScene(new File("src/newApppointment/ownerS.fxml"), (Stage) ((Node) event.getSource()).getScene().getWindow());
			return;
		}
		else {
			try {
				Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

				java.sql.Statement get = conn.createStatement();

				ResultSet myRs = get.executeQuery("select username from appointments.user"
						+ " where username = '" + username.getText() + "' and password = '" + password.getText() + "' ;");
				String text = "";
				while(myRs.next()) text += myRs.getString("username");
				
				if(text.equals("")) {
					popupError("Wrong username/password");
					return;
				}
				
				Appointment.setCurrentUser(new User(username.getText(), password.getText()));

				switchScene(new File("src/newApppointment/appointments.fxml"), (Stage) ((Node) event.getSource()).getScene().getWindow());
			} catch(SQLException e) {
				popupError("Wrong username/password");
			}
		}

	}


}
