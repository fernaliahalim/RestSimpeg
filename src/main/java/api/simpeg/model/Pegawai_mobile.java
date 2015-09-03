package api.simpeg.model;

public class Pegawai_mobile{
	private int id_pegawai;
	private String nama;
	private String gelar_depan;
	private String gelar_belakang;
	private String jenis_kelamin;
	private String gol_darah;
	private String agama;
	private String tempat_lahir;
	private String tgl_lahir;
	private String alamat;
	private String nip_baru;
	private String no_karpeg;
	private int masa_kerja_pasif;
	private String pangkat_gol;
	private String jenjab;
	private String jabatan;
	private String eseloning;
	private String tgl_pensiun_dini_old;
	private String tgl_pensiun_dini;
	private String password;
	private String imei;
	private String unit_kerja;
	
	//attribute for error handling
	private int request_status=100;
	
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
	public String getGelar_depan() {
		return gelar_depan;
	}
	public void setGelar_depan(String gelar_depan) {
		this.gelar_depan = gelar_depan;
	}
	public String getGelar_belakang() {
		return gelar_belakang;
	}
	public void setGelar_belakang(String gelar_belakang) {
		this.gelar_belakang = gelar_belakang;
	}
	public String getJenis_kelamin() {
		return jenis_kelamin;
	}
	public void setJenis_kelamin(String jenis_kelamin) {
		this.jenis_kelamin = jenis_kelamin;
	}
	public String getGol_darah() {
		return gol_darah;
	}
	public void setGol_darah(String gol_darah) {
		this.gol_darah = gol_darah;
	}
	public String getAgama() {
		return agama;
	}
	public void setAgama(String agama) {
		this.agama = agama;
	}
	public String getTempat_lahir() {
		return tempat_lahir;
	}
	public void setTempat_lahir(String tempat_lahir) {
		this.tempat_lahir = tempat_lahir;
	}
	public String getTgl_lahir() {
		return tgl_lahir;
	}
	public void setTgl_lahir(String tgl_lahir) {
		this.tgl_lahir = tgl_lahir;
	}
	public String getAlamat() {
		return alamat;
	}
	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}
	public String getNip_baru() {
		return nip_baru;
	}
	public void setNip_baru(String nip_baru) {
		this.nip_baru = nip_baru;
	}
	public String getNo_karpeg() {
		return no_karpeg;
	}
	public void setNo_karpeg(String no_karpeg) {
		this.no_karpeg = no_karpeg;
	}
	public int getMasa_kerja_pasif() {
		return masa_kerja_pasif;
	}
	public void setMasa_kerja_pasif(int masa_kerja_pasif) {
		this.masa_kerja_pasif = masa_kerja_pasif;
	}
	public String getPangkat_gol() {
		return pangkat_gol;
	}
	public void setPangkat_gol(String pangkat_gol) {
		this.pangkat_gol = pangkat_gol;
	}
	public String getJenjab() {
		return jenjab;
	}
	public void setJenjab(String jenjab) {
		this.jenjab = jenjab;
	}
	public String getJabatan() {
		return jabatan;
	}
	public void setJabatan(String jabatan) {
		this.jabatan = jabatan;
	}
	public String getEseloning() {
		return eseloning;
	}
	public void setEseloning(String eseloning) {
		this.eseloning = eseloning;
	}
	public String getTgl_pensiun_dini_old() {
		return tgl_pensiun_dini_old;
	}
	public void setTgl_pensiun_dini_old(String tgl_pensiun_dini_old) {
		this.tgl_pensiun_dini_old = tgl_pensiun_dini_old;
	}
	public String getTgl_pensiun_dini() {
		return tgl_pensiun_dini;
	}
	public void setTgl_pensiun_dini(String tgl_pensiun_dini) {
		this.tgl_pensiun_dini = tgl_pensiun_dini;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
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
		return "{id=" + id_pegawai + ", nama=" + nama + ", gelar_depan=" + gelar_depan + ", gelar_belakang=" + gelar_belakang + ", jenis_kelamin=" + jenis_kelamin + ", gol_darah=" + gol_darah + ", agama=" + agama + ", tempat_lahir=" + tempat_lahir + ", tgl_lahir=" + tgl_lahir + ", alamat=" + alamat + ", nip=" + nip_baru + ", no_karpeg=" + no_karpeg + ", masa_kerja_pasif=" + masa_kerja_pasif + ", pangkat_gol=" + pangkat_gol + ", jenjab =" + jenjab + ", jabatan" + jabatan + ", eseloning=" + eseloning + ", tgl_pensiun_dini_old=" + tgl_pensiun_dini_old + ", tgl_pensiun_dini=" + tgl_pensiun_dini + ", imei=" + imei + ", unit_kerja=" + unit_kerja + ", request_status=" + request_status + "}";
	}
	
	public String ReqtoString(){
		return "[{\"request_status\":" + request_status + "}]";
	}
}