package newApppointment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/*
 * https://docs.oracle.com/javafx/2/ui_controls/table-view.htm#CJAGAAEE
 */

public class ViewTest extends Application {


	static final String DB_URL = "jdbc:mysql://localhost:3306/";
	static final String USER = "aly";
	static final String PASS = "aly";

	private Stage window;
	private	Button button; 
	private Scene scene;

	private TableView<Product> table; 


	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		window.setTitle("JavaFX - thenewboston");

		//Name column
		TableColumn<Product, String> nameColumn = new TableColumn<>("Name");
		nameColumn.setMinWidth(200);
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

		//Price column
		TableColumn<Product, Double> priceColumn = new TableColumn<>("Service");
		priceColumn.setMinWidth(100);
		priceColumn.setCellValueFactory(new PropertyValueFactory<>("service"));

		//Quantity column
		TableColumn<Product, String> quantityColumn = new TableColumn<>("Date");
		quantityColumn.setMinWidth(100);
		quantityColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

		// instantiate TableView, add products data, define columns
		table = new TableView<>();
		table.setItems(getProduct());
		table.getColumns().addAll(nameColumn, priceColumn, quantityColumn);

		// Layout
		VBox layout = new VBox(10); // 10 px espacement
		layout.setPadding(new Insets(20));
		layout.getChildren().add(table);

		scene = new Scene(layout, 500, 250);
		window.setScene(scene); 
		window.show();
	}

	// get all ofthe products
	public ObservableList<Product> getProduct() {

		try{
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

			java.sql.Statement get = conn.createStatement();



			ObservableList<Product> list = FXCollections.observableArrayList();

			ResultSet myRs = get.executeQuery("select * from appointments.appointments_list"
					+ " where status = 'true'");

			while(myRs.next()) {
				list.add(new Product(myRs.getString("userid"), 
						myRs.getString("type").substring(11), myRs.getString("date"), myRs.getString("time"), myRs.getString("service")));

			}
			
			return list;

		} catch(SQLException e) {
			e.printStackTrace();
		}
			return null;
		}

		public static void main(String[] args) {
			launch(args);
		}
	}


	//
	//import javafx.application.Application;
	//import javafx.collections.FXCollections;
	//import javafx.collections.ObservableList;
	//import javafx.scene.Scene;
	//import javafx.scene.control.TableColumn;
	//import javafx.scene.control.TableView;
	//import javafx.scene.control.cell.PropertyValueFactory;
	//import javafx.scene.layout.VBox;
	//import javafx.stage.Stage;
	//
	//public class ViewTest extends Application{
	//
	//	private Stage window;
	//	TableView<ModelTable> table;
	//	
	//	@Override
	//	public void start(Stage arg0) throws Exception {
	//		window = arg0;
	//		window.setTitle("Table Test");
	//		
	//		TableColumn<ModelTable, String> nameColumn = new TableColumn<>("Name");
	//		nameColumn.setMinWidth(200);
	//		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
	//		
	//		TableColumn<ModelTable, String> typeColumn = new TableColumn<>("Type");
	//		typeColumn.setMinWidth(200);
	//		typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
	//	
	//		TableColumn<ModelTable, String> dateColumn = new TableColumn<>("Date");
	//		dateColumn.setMinWidth(200);
	//		dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
	//	
	//		TableColumn<ModelTable, String> timeColumn = new TableColumn<>("Time");
	//		timeColumn.setMinWidth(200);
	//		timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
	//		
	//		TableColumn<ModelTable, String> serviceColumn = new TableColumn<>("Service");
	//		serviceColumn.setMinWidth(200);
	//		serviceColumn.setCellValueFactory(new PropertyValueFactory<>("service"));
	//	
	//		table = new TableView<>();
	//		table.setItems(getTable());
	//		table.getColumns().addAll(nameColumn, typeColumn, dateColumn, timeColumn, serviceColumn);
	//		
	//		VBox vBox = new VBox();
	//		vBox.getChildren().addAll(table);
	//		
	//		Scene scene = new Scene(vBox);
	//		window.setScene(scene);
	//		window.show();
	//		
	//	}
	//	
	//	public ObservableList<ModelTable> getTable(){
	//		ObservableList<ModelTable> products = FXCollections.observableArrayList();
	//		products.add(new ModelTable("aly", "booked", "2021-05-28", "10:00", "Engine-Check"));
	//		products.add(new ModelTable("loll", "canceled", "2021-05-29", "12:00", "Engine-Check"));
	//
	//		return products;
	//	}
	//
	//}
