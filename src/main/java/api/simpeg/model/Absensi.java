package api.simpeg.model;

public class Absensi{
	private int id;
	private String unit_kerja;
	private int jumlah;
	private int jml_pgw;
	
	//attribute for error handling
	private int request_status=100;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUnit_kerja() {
		return unit_kerja;
	}

	public void setUnit_kerja(String unit_kerja) {
		this.unit_kerja = unit_kerja;
	}

	public int getJumlah() {
		return jumlah;
	}

	public void setJumlah(int jumlah) {
		this.jumlah = jumlah;
	}
	
	public int getJml_pgw() {
		return jml_pgw;
	}

	public void setJml_pgw(int jml_pgw) {
		this.jml_pgw = jml_pgw;
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
		return "{id=" + id + ", unit_kerja=" + unit_kerja + ", jumlah =" + jumlah + ", jml_pegawai =" + jml_pgw + ", request_status=" + request_status + "}";
	}
	
	public String ReqtoString(){
		return "[{\"request_status\":" + request_status + "}]";
	}
}