package newApppointment;

public class Product {
	
	private String name;
	private String type;
	private String date;
	private String service;
	private String time;
	
	public Product() {
		this.name = "";
		this.type = "";
		this.date = "";
		this.service = "";
		this.time = "";
		this.date = "";
	}

	public Product(String name, String type, String date, String time, String service) {
		super();
		this.name = name;
		this.type = type;
		this.date = date;
		this.time = time;
		this.service = service;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	
}