package api.simpeg.model;

public class Unit_kerja{
	private int id_unit_kerja;
	private String nama_baru;
	private String alamat;
	private String telp;
	private int id_skpd;
	private String longitude;
	private String latitude;
	private String longitude_outer;
	private String latitude_outer;
	private String radius;
	
	//atribut for error handling
	private int request_status=100;
	
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
	public String getAlamat() {
		return alamat;
	}
	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}
	public String getTelp() {
		return telp;
	}
	public void setTelp(String telp) {
		this.telp = telp;
	}
	public int getId_skpd() {
		return id_skpd;
	}
	public void setId_skpd(int id_skpd) {
		this.id_skpd = id_skpd;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude_outer() {
		return longitude_outer;
	}
	public void setLongitude_outer(String longitude_outer) {
		this.longitude_outer = longitude_outer;
	}
	public String getLatitude_outer() {
		return latitude_outer;
	}
	public void setLatitude_outer(String latitude_outer) {
		this.latitude_outer = latitude_outer;
	}
	public String getRadius() {
		return radius;
	}
	public void setRadius(String radius) {
		this.radius = radius;
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
		return "{id=" + id_unit_kerja + ", unit_kerja=" + nama_baru + ", alamat =" + alamat + ", telp=" + telp + ", id_skpd=" + id_skpd +  ", longitude=" + longitude +  ", latitude=" + latitude +  ", longitude_outer=" + longitude_outer +  ", latitude_outer=" + latitude_outer +  ", radius=" + radius + ", request_status=" + request_status + "}";
	}
	
	public String ReqtoString(){
		return "[{\"request_status\":" + request_status + "}]";
	}
	
	public String SectoString(){
		return "[{\"id_unit_kerja\":" + id_unit_kerja + ", " + "\"unit_kerja\":\"" + nama_baru + "\", " + "\"latitude\":" + latitude + ", " + "\"longitude\":" + longitude + ", " + "\"radius\":" + radius + ", " + "\"request_status\":" + request_status + "}]";
	}
}