package api.simpeg.dao;

import java.util.List;

import api.simpeg.model.Absensi_mobile;

public interface AbsensiMobileDAO{
	
	public String hadir(String id_pegawai, String nip, String password, String x, String y, String imei, String api_key);
	
	public String tidak_hadir(String id_pegawai, String status, String api_key);
	
	public List<Absensi_mobile> get_attendance(String id_pegawai, String api_key);
	
}