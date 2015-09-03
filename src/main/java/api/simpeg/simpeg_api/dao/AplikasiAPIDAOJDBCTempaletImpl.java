package api.simpeg.simpeg_api.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import api.simpeg.simpeg_api.model.Aplikasi_api;

public class AplikasiAPIDAOJDBCTempaletImpl implements AplikasiAPIDAO{
	private DataSource dataSource;
	
	@Autowired
	public void setDataSource(DataSource dataSource){
		this.dataSource = dataSource;
	}
	
	@Override
	public List<Aplikasi_api> get_all(){
		//String query = "SELECT * FROM aplikasi";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Aplikasi_api> aplikasiList = new ArrayList<Aplikasi_api>();
		
		List<Map<String, Object>> aplikasiRows = jdbcTemplate.queryForList("SELECT * FROM aplikasi");
		
		for (Map<String, Object> aplikasiRow : aplikasiRows){
			Aplikasi_api aplikasi_api = new Aplikasi_api();
			aplikasi_api.setId_aplikasi(Integer.parseInt(String.valueOf(aplikasiRow.get("id_aplikasi"))));
			aplikasi_api.setNama_aplikasi(String.valueOf(aplikasiRow.get("nama_aplikasi")));
			aplikasi_api.setApi_key(String.valueOf(aplikasiRow.get("api_key")));
			aplikasiList.add(aplikasi_api);
		}
		return aplikasiList;
	}
	
	@Override
	public Aplikasi_api get_aplikasi_detail(String api_key){
		//String query = "SELECT * FROM aplikasi WHERE api_key='" + api_key + "'" ;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		Aplikasi_api aplikasi_api = new Aplikasi_api();
		
		List<Map<String, Object>> aplikasiRows = jdbcTemplate.queryForList( "SELECT * FROM aplikasi WHERE api_key=?", api_key);
		
		for (Map<String, Object> aplikasiRow : aplikasiRows){
			aplikasi_api.setId_aplikasi(Integer.parseInt(String.valueOf(aplikasiRow.get("id_aplikasi"))));
			aplikasi_api.setNama_aplikasi(String.valueOf(aplikasiRow.get("nama_aplikasi")));
			aplikasi_api.setApi_key(String.valueOf(aplikasiRow.get("api_key")));
		}
		return aplikasi_api;
	}
}