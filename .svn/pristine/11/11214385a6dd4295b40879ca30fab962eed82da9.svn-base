package api.simpeg.simpeg_api.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import api.simpeg.simpeg_api.model.Api_akses;
import api.simpeg.simpeg_api.model.Aplikasi_api;

public class ApiAksesDAOJDBCTemplateImpl implements ApiAksesDAO{
	
	private DataSource dataSource;
	
	@Autowired
	public void setDataSource(DataSource dataSource){
		this.dataSource = dataSource;
	}
	
	@Override
	public Api_akses get_by_method(int id_method){
		//String query = "SELECT * FROM api_akses WHERE id_method=" + id_method;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		Api_akses aa = new Api_akses();
		
		List<Map<String, Object>> apiRows = jdbcTemplate.queryForList("SELECT * FROM api_akses WHERE id_method=?", id_method);
		
		for (Map<String, Object> apiRow : apiRows){
			aa.setId(Integer.parseInt(String.valueOf(apiRow.get("id_api_akses"))));
			aa.setId_aplikasi(Integer.parseInt(String.valueOf(apiRow.get("id_aplikasi"))));
			aa.setId_method(Integer.parseInt(String.valueOf(apiRow.get("id_method"))));
		}
		return aa;
	}
	
	@Override
	public int get_status(int id_aplikasi, int id_method){
		String query = "SELECT * FROM api_akses WHERE id_aplikasi=" + id_aplikasi + " AND id_method=" + id_method;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		int status = 0;
		
		List<Map<String, Object>> Rows = jdbcTemplate.queryForList("SELECT * FROM api_akses WHERE id_aplikasi=? AND id_method=?", id_aplikasi, id_method);
		
		for (Map<String, Object> apiRow : Rows){
			Api_akses aa = new Api_akses();
			aa.setId(Integer.parseInt(String.valueOf(apiRow.get("id_api_akses"))));
			aa.setId_aplikasi(Integer.parseInt(String.valueOf(apiRow.get("id_aplikasi"))));
			aa.setId_method(Integer.parseInt(String.valueOf(apiRow.get("id_method"))));
			
			status = 1;
		}
		return status;
	}
}