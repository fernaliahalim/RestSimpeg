package api.simpeg.dao;

import java.util.List;

import api.simpeg.model.Unit_kerja;
import api.simpeg.model.Unit_kerja_mobile_request;

public interface UnitKerjaDAO{
	
	public String distance(String long1, String lat1, String long2, String lat2);
	
	public Unit_kerja get_location_uk(String id_pegawai);
	
	public String get_location_uk(String id_pegawai, String api_key);
	
	public List<Unit_kerja> get_all(String api_key);
	
	public Unit_kerja get_by_unit_kerja(String unit_kerja, String api_key);
	
	public List<Unit_kerja_mobile_request> get_id_nama(String api_key);
}