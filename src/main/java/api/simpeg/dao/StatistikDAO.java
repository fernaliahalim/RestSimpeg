package api.simpeg.dao;

import java.util.List;

import api.simpeg.model.Statistik;

public interface StatistikDAO{
	
	public List<Statistik> getStatisticBidangPendidikan(String api_key);
	
	public List<Statistik> getStatisticFungsional(String api_key);
	
	public List<Statistik> getStatisticGolongan(String api_key);
	
	public List<Statistik> getStatisticJabatan(String api_key);
	
	public List<Statistik> getStatisticJenisKelamin(String api_key);
	
	public List<Statistik> getStatisticLulusanPt(String api_key);
	
	public List<Statistik> getStatisticPendidikan(String api_key);
	
	public List<Statistik> getStatisticStruktural(String api_key);
	
	public List<Statistik> getStatisticUmur(String api_key);
	
}