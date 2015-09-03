package api.simpeg.model;

public class Unit_kerja_mobile_request{
	//atribut
	private int id_unit_kerja;
	private String nama_baru;
	
	//atribut for error handling
	private int request_status = 100;

	public int getId_unit_kerja() {
		return id_unit_kerja;
	}

	public void setId_unit_kerja(int id_unit_kerja) {
		this.id_unit_kerja = id_unit_kerja;
	}

	public String getNama_baru() {
		return nama_baru;
	}

	public void setNama_baru(String nama_baru) {
		this.nama_baru = nama_baru;
	}

	public int getRequest_status() {
		return request_status;
	}

	public void setRequest_status(int request_status) {
		this.request_status = request_status;
	}
	
	@Override
	public String toString(){
		return "{id=" + id_unit_kerja + ", unit_kerja=" + nama_baru + ", request_status=" + request_status + "}";
	}
	
	public String ReqtoString(){
		return "[{\"request_status\":" + request_status + "}]";
	}
}