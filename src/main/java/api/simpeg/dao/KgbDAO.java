package api.simpeg.dao;

import java.util.List;

import api.simpeg.model.Kgb;

public interface KgbDAO{
	
	public List<Kgb> get_all(String tahun, String bulan, String api_key);
	
	public List<Kgb> get_by_unit_kerja(String unit_kerja, String tahun, String bulan, String api_key);
}