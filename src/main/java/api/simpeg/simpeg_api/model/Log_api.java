package api.simpeg.simpeg_api.model;

public class Log_api{
	private int id_log;
	private int id_aplikasi;
	private int id_method;
	private String date_time;
	
	public int getId_log() {
		return id_log;
	}
	public void setId_log(int id_log) {
		this.id_log = id_log;
	}
	public int getId_aplikasi() {
		return id_aplikasi;
	}
	public void setId_aplikasi(int id_aplikasi) {
		this.id_aplikasi = id_aplikasi;
	}
	public int getId_method() {
		return id_method;
	}
	public void setId_method(int id_method) {
		this.id_method = id_method;
	}
	public String getDate_time() {
		return date_time;
	}
	public void setDate_time(String date_time) {
		this.date_time = date_time;
	}
	
	@Override
	public String toString(){
		return "{id=" + id_log + ", id_aplikasi=" + id_aplikasi + ", id_method =" + id_method + ", date_time=" + date_time + "}";
	}
}