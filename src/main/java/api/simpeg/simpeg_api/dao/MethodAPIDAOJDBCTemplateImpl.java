package api.simpeg.simpeg_api.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import api.simpeg.simpeg_api.model.Method_api;

@Repository
public class MethodAPIDAOJDBCTemplateImpl implements MethodAPIDAO{
	
	private DataSource dataSource;
	
	@Autowired
	public void setDataSource(DataSource dataSource){
		this.dataSource = dataSource;
	}
	
	@Override
	public List<Method_api> get_by_nama(String nama){
		//String query = "SELECT * FROM method WHERE nama_method='" + nama  + "'";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Method_api> methodList = new ArrayList<Method_api>();
		
		List<Map<String, Object>> methodRows = jdbcTemplate.queryForList("SELECT * FROM method WHERE nama_method=?", nama);
		
		for (Map<String, Object> methodRow : methodRows){
			Method_api method_api = new Method_api();
			method_api.setId_method(Integer.parseInt(String.valueOf(methodRow.get("id_method"))));
			method_api.setNama_method(String.valueOf(methodRow.get("nama_method")));
			method_api.setParameter(String.valueOf(methodRow.get("parameter")));
			methodList.add(method_api);
		}
		return methodList;
	}
	
	@Override
	public int get_id(String nama){
		//String query = "SELECT * FROM method WHERE nama_method='" + nama  + "'";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		Method_api method_api = new Method_api();
		
		List<Map<String, Object>> methodRows = jdbcTemplate.queryForList("SELECT * FROM method WHERE nama_method=?", nama);
		
		for (Map<String, Object> methodRow : methodRows){
			method_api.setId_method(Integer.parseInt(String.valueOf(methodRow.get("id_method"))));
			method_api.setNama_method(String.valueOf(methodRow.get("nama_method")));
			method_api.setParameter(String.valueOf(methodRow.get("parameter")));
		}
		return method_api.getId_method();
	}
}