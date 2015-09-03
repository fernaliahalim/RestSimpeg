package api.simpeg.dao;

import java.util.List;

import api.simpeg.model.Absensi;

public interface AbsensiDAO{
	
	public List<Absensi> get_hadir(String unit_kerja, String tgl, String api_key);
	
	public List<Absensi> get_sakit(String unit_kerja, String tgl, String api_key);
	
	public List<Absensi> get_ijin(String unit_kerja, String tgl, String api_key);
	
	public List<Absensi> get_alpha(String unit_kerja, String tgl, String api_key);
	
}