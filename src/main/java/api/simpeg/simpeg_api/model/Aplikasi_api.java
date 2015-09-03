package api.simpeg.simpeg_api.model;

public class Aplikasi_api{
	private int id_aplikasi;
	private String nama_aplikasi;
	private String api_key;
	
	public int getId_aplikasi() {
		return id_aplikasi;
	}
	public void setId_aplikasi(int id_aplikasi) {
		this.id_aplikasi = id_aplikasi;
	}
	public String getNama_aplikasi() {
		return nama_aplikasi;
	}
	public void setNama_aplikasi(String nama_aplikasi) {
		this.nama_aplikasi = nama_aplikasi;
	}
	public String getApi_key() {
		return api_key;
	}
	public void setApi_key(String api_key) {
		this.api_key = api_key;
	}
	
	@Override
	public String toString(){
		return "{id=" + id_aplikasi + ", nama_aplikasi=" + nama_aplikasi + ", api_key =" + api_key + "}";
	}
}