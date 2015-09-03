package api.simpeg.simpeg_api.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LogAPIDAOJDBCTemplateImpl implements LogAPIDAO{
	
	private DataSource dataSource;
	
	@Autowired
	public void setDataSource(DataSource dataSource){
		this.dataSource = dataSource;
	}
	
	@Override
	public void tambah(int id_aplikasi, int id_method){
		String query = "INSERT INTO log_api(id_aplikasi,id_method,date_time) VALUES(" + id_aplikasi + "," + id_method + ", now())";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		jdbcTemplate.update("INSERT INTO log_api(id_aplikasi,id_method,date_time) VALUES(?,?, now())", id_aplikasi, id_method);
	}
}