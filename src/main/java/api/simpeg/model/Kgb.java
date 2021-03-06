package api.simpeg.model;

public class Kgb{
	private int id;
	private String nama;
	private String nip;
	private String golongan;
	private String tmt_sebelumnya;
	private String unit_kerja;
	private String status;
	
	//attribute for error handling
	private int request_status=100;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNama() {
		return nama;
	}
	public void setNama(String nama) {
		this.nama = nama;
	}
	public String getGolongan() {
		return golongan;
	}
	public void setGolongan(String golongan) {
		this.golongan = golongan;
	}
	public String getTmt_sebelumnya() {
		return tmt_sebelumnya;
	}
	public void setTmt_sebelumnya(String tmt_sebelumnya) {
		this.tmt_sebelumnya = tmt_sebelumnya;
	}
	public String getUnit_kerja() {
		return unit_kerja;
	}
	public void setUnit_kerja(String unit_kerja) {
		this.unit_kerja = unit_kerja;
	}
	public String getNip() {
		return nip;
	}
	public void setNip(String nip) {
		this.nip = nip;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
		return "{id=" + id + ", nama=" + nama + ", nip =" + nip + ", golongan=" + golongan + ", tmt_sebelumnya=" + tmt_sebelumnya + ", unit_kerja=" + unit_kerja + ", request_status" + request_status + "}";
	}
	
	public String ReqtoString(){
		return "[{\"request_status\":" + request_status + "}]";
	}
}