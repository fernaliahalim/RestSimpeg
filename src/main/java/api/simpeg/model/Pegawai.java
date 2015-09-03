package api.simpeg.model;

public class Pegawai{
	
	private int id;
	private String nama;
	private String nip;
	private String jabatan;
	private String unit_kerja;
	
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
	public String getNip() {
		return nip;
	}
	public void setNip(String nip) {
		this.nip = nip;
	}
	public String getJabatan() {
		return jabatan;
	}
	public void setJabatan(String jabatan) {
		this.jabatan = jabatan;
	}
	public String getUnit_kerja() {
		return unit_kerja;
	}
	public void setUnit_kerja(String unit_kerja) {
		this.unit_kerja = unit_kerja;
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
		return "{id=" + id + ", nama=" + nama + ", nip =" + nip + ", jabatan=" + jabatan + ", unit_kerja=" + unit_kerja + ", request_status=" + request_status + "}";
	}
	
	public String ReqtoString(){
		return "[{\"request_status\":" + request_status + "}]";
	}
}