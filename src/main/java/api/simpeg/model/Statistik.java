package api.simpeg.model;

public class Statistik{
	private int value;
	private String attribute;
	
	//attribute for error handling
	private int request_status = 100;
	
	//setter and getter
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}
	
	//method for error handling
	public int getRequest_status() {
		return request_status;
	}

	public void setRequest_status(int request_status) {
		this.request_status = request_status;
	}
	
	@Override
	public String toString(){
		return "{value=" + value + ", attribute=" + attribute + ", request_status=" + request_status + "}";
	}
	
	public String ReqtoString(){
		return "[{\"request_status\":" + request_status + "}]";
	}
}