package api.simpeg.model;

public class Comment{
	private int id_post;
	private String msg;
	private int id_pegawai;
	private String nama;
	private String gelar_belakang;
	private String kapan;
	private String parent_id;
	
	//attribute for error handling
	private int request_status=100;

	public int getId_post() {
		return id_post;
	}

	public void setId_post(int id_post) {
		this.id_post = id_post;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getId_pegawai() {
		return id_pegawai;
	}

	public void setId_pegawai(int id_pegawai) {
		this.id_pegawai = id_pegawai;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getGelar_belakang() {
		return gelar_belakang;
	}

	public void setGelar_belakang(String gelar_belakang) {
		this.gelar_belakang = gelar_belakang;
	}

	public String getKapan() {
		return kapan;
	}

	public void setKapan(String kapan) {
		this.kapan = kapan;
	}

	public String getParent_id() {
		return parent_id;
	}

	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
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
		return "{id_post=" + id_post + ", msg=" + msg + ", id_pegawai =" + id_pegawai + ", nama=" + nama + ", gelar_belakang=" + gelar_belakang + ", kapan=" + kapan + ", parent_id=" + parent_id + ", request_status=" + request_status + "}";
	}
	
	public String ReqtoString(){
		return "[{\"request_status\":" + request_status + "}]";
	}
}