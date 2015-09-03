package api.simpeg.dao;

import java.util.List;

import api.simpeg.model.Pegawai;

public interface PegawaiDAO{
	
	public List<Pegawai> find_all(String api_key);
	
	public List<Pegawai> find_by_nama(String nama, String api_key);
	
	public List<Pegawai> find_by_nip(String nip, String api_key);
	
	public List<Pegawai> find_by_unit_kerja(String unit_kerja, String api_key);
}