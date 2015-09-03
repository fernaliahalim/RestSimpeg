package api.simpeg.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import api.simpeg.model.Absensi;
import api.simpeg.model.Unit_kerja;
import api.simpeg.model.Unit_kerja_mobile_request;
import api.simpeg.simpeg_api.dao.ApiAksesDAO;
import api.simpeg.simpeg_api.dao.AplikasiAPIDAO;
import api.simpeg.simpeg_api.dao.LogAPIDAO;
import api.simpeg.simpeg_api.dao.MethodAPIDAO;
import api.simpeg.simpeg_api.model.Aplikasi_api;

public class UnitKerjaDAOJDBCTemplateImpl implements UnitKerjaDAO{
	private DataSource dataSaource;
	
	@Autowired
	public void setDataSource(DataSource dataSource){
		this.dataSaource = dataSource;
	}
	
	@Override
	public String distance(String long1, String lat1, String long2, String lat2){
		double longitude1 = Double.parseDouble(long1);
		double longitude2 = Double.parseDouble(long2);
		double latitude1 = Double.parseDouble(lat1);
		double latitude2 = Double.parseDouble(lat2);
		
		double earthRadius = 6371000;
		double dLat = Math.toRadians(latitude2 - latitude1);
		double dLong = Math.toRadians(longitude2 - longitude1);
		double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(Math.toRadians(latitude1)) * Math.cos(Math.toRadians(latitude2)) * Math.sin(dLong / 2) * Math.sin(dLong /2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		double dist = (Double) (earthRadius * c);
		
		return String.valueOf(dist);
	}
	
	
	@Override
	public Unit_kerja get_location_uk(String id_pegawai){
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSaource);
		String id_uk = null;
		String latitude = null;
		String longitude = null;
		String latitude_outer = null;
		String longitude_outer = null;
		Unit_kerja uk = new Unit_kerja();
		
		List<Map<String, Object>> cukRows = jdbcTemplate.queryForList("SELECT id_unit_kerja FROM current_lokasi_kerja WHERE id_pegawai=?", id_pegawai);
		
		for (Map<String, Object> cukRow : cukRows){
			id_uk = String.valueOf(cukRow.get("id_unit_kerja"));
		}
		
		System.out.println(id_uk);
		
		List<Map<String, Object>> ukRows = jdbcTemplate.queryForList("SELECT x(long_lat) as longitude, y(long_lat) as latitude, x(long_lat_outer) as long_outer, y(long_lat_outer) as lat_outer FROM unit_kerja WHERE id_unit_kerja=?", id_uk);
		
		for (Map<String, Object> ukRow : ukRows){
			latitude = String.valueOf(ukRow.get("latitude"));
			longitude = String.valueOf(ukRow.get("longitude"));
			latitude_outer = String.valueOf(ukRow.get("lat_outer"));
			longitude_outer = String.valueOf(ukRow.get("long_outer"));
		}
		
		uk.setId_unit_kerja(Integer.parseInt(id_uk));
		uk.setLongitude(longitude);
		uk.setLatitude(latitude);
		uk.setLongitude_outer(longitude_outer);
		uk.setLatitude_outer(latitude_outer);
		
		return uk;
	}
	
	@Override
	public String get_location_uk(String id_pegawai, String api_key){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        MethodAPIDAO methodDAO = ctx.getBean("methodAPIDAO", MethodAPIDAO.class);
        AplikasiAPIDAO aplikasiDAO = ctx.getBean("aplikasiAPIDAO", AplikasiAPIDAO.class);
        LogAPIDAO logDAO = ctx.getBean("logAPIDAO", LogAPIDAO.class);
        ApiAksesDAO apiDAO = ctx.getBean("apiAksesDAO", ApiAksesDAO.class);
        
        //atribut untuk mengambil data aplikasi dan method
		int id_method;
		String api_keydb;
		int id_aplikasi;
		int id_app = 0;
		String nama_method = "unit_kerja/get_location_uk";
		Aplikasi_api aplikasi_api = new Aplikasi_api();
		
		aplikasi_api = aplikasiDAO.get_aplikasi_detail(api_key);
		id_aplikasi = aplikasi_api.getId_aplikasi();
		api_keydb = aplikasi_api.getApi_key(); 
		
		id_method = methodDAO.get_id(nama_method);
		
		id_app = apiDAO.get_status(id_aplikasi, id_method);
		
		//atribut untuk menampilkan data longitude dan latitude unit kerja
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSaource);
		Unit_kerja unit_kerjadb = new Unit_kerja();
		String id_uk = null;
		String latitude = null;
		String longitude = null;
		String nama_baru = null;
		String long_outer = null;
		String lat_outer = null;
		String distance = null;
		
		if(id_app>0){
			if(api_key.equals(api_keydb)){
				//menambahkan data log_api
				logDAO.tambah(id_aplikasi, id_method);
				
				List<Map<String, Object>> cukRows = jdbcTemplate.queryForList("SELECT id_unit_kerja FROM current_lokasi_kerja WHERE id_pegawai=?", id_pegawai);
				
				for (Map<String, Object> cukRow : cukRows){
					id_uk = String.valueOf(cukRow.get("id_unit_kerja"));
				}
		
				List<Map<String, Object>> ukRows = jdbcTemplate.queryForList("SELECT x(long_lat) as longitude, y(long_lat) as latitude, nama_baru, x(long_lat_outer) as long_outer, y(long_lat_outer) as lat_outer FROM unit_kerja WHERE id_unit_kerja=?", id_uk);
				
				for (Map<String, Object> ukRow : ukRows){
					latitude = String.valueOf(ukRow.get("latitude"));
					longitude = String.valueOf(ukRow.get("longitude"));
					nama_baru = String.valueOf(ukRow.get("nama_baru"));
					long_outer = String.valueOf(ukRow.get("long_outer"));
					lat_outer = String.valueOf(ukRow.get("lat_outer"));
				}
				
				unit_kerjadb.setId_unit_kerja(Integer.parseInt(id_uk));
				unit_kerjadb.setNama_baru(nama_baru);
				unit_kerjadb.setLatitude(latitude);
				unit_kerjadb.setLongitude(longitude);
				
				if(longitude.equals("null") || long_outer.equals("null")){
					unit_kerjadb.setRadius(null);
					unit_kerjadb.setRequest_status(110);
				}
				else{
					distance = distance(longitude, latitude, long_outer, lat_outer);
					unit_kerjadb.setRadius(String.valueOf(distance));
					unit_kerjadb.setRequest_status(100);
				}
			}
			else{
				unit_kerjadb.setRequest_status(101);
			}
		}
		else{
			unit_kerjadb.setRequest_status(101);
		}
		return unit_kerjadb.SectoString();
	}
	
	@Override
	public List<Unit_kerja> get_all(String api_key){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        MethodAPIDAO methodDAO = ctx.getBean("methodAPIDAO", MethodAPIDAO.class);
        AplikasiAPIDAO aplikasiDAO = ctx.getBean("aplikasiAPIDAO", AplikasiAPIDAO.class);
        LogAPIDAO logDAO = ctx.getBean("logAPIDAO", LogAPIDAO.class);
        ApiAksesDAO apiDAO = ctx.getBean("apiAksesDAO", ApiAksesDAO.class);
        
        //atribut untuk mengambil data aplikasi dan method
		int id_method;
		String api_keydb;
		int id_aplikasi;
		int id_app = 0;
		String nama_method = "unit_kerja/get_all";
		Aplikasi_api aplikasi_api = new Aplikasi_api();
		
		aplikasi_api = aplikasiDAO.get_aplikasi_detail(api_key);
		id_aplikasi = aplikasi_api.getId_aplikasi();
		api_keydb = aplikasi_api.getApi_key(); 
		
		id_method = methodDAO.get_id(nama_method);
		
		id_app = apiDAO.get_status(id_aplikasi, id_method);
		
		//atribut untuk menampilkan data unit kerja
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSaource);
		List<Unit_kerja> ukList = new ArrayList<Unit_kerja>();
		Unit_kerja uk_error = new Unit_kerja();
		
		if(id_app>0){
			if(api_key.equals(api_keydb)){
				//menambahkan data log_api
				logDAO.tambah(id_aplikasi, id_method);
			
				//String query = "SELECT * FROM unit_kerja WHERE tahun=SUBSTR(CURDATE(),1,4)";
				
				List<Map<String, Object>> ukRows = jdbcTemplate.queryForList("SELECT id_unit_kerja, nama_baru, alamat, telp, id_skpd, x(long_lat) as longitude, y(long_lat) as latitude, x(long_lat_outer) as longitude_outer, y(long_lat_outer) as latitude_outer FROM unit_kerja WHERE tahun=(SELECT max(tahun) FROM unit_kerja) AND id_unit_kerja = id_skpd ORDER BY nama_baru");
				
				for (Map<String, Object> ukRow : ukRows){
					Unit_kerja unit_kerja = new Unit_kerja();
					unit_kerja.setId_unit_kerja(Integer.parseInt(String.valueOf(ukRow.get("id_unit_kerja"))));
					unit_kerja.setNama_baru(String.valueOf(ukRow.get("nama_baru")));
					unit_kerja.setAlamat(String.valueOf(ukRow.get("alamat")));
					unit_kerja.setTelp(String.valueOf(ukRow.get("telp")));
					unit_kerja.setId_skpd(Integer.parseInt(String.valueOf(ukRow.get("id_skpd"))));
					unit_kerja.setLongitude(String.valueOf(ukRow.get("longitude")));
					unit_kerja.setLatitude(String.valueOf(ukRow.get("latitude")));
					unit_kerja.setLongitude_outer(String.valueOf(ukRow.get("longitude_outer")));
					unit_kerja.setLatitude_outer(String.valueOf(ukRow.get("latitude_outer")));
					if(unit_kerja.getLongitude().equals("null") || unit_kerja.getLongitude_outer().equals("null")){
						unit_kerja.setRadius(null);
						uk_error.setRequest_status(110);
						ukList.add(uk_error);
					}
					else{
						unit_kerja.setRadius(distance(unit_kerja.getLongitude(), unit_kerja.getLatitude(), unit_kerja.getLongitude_outer(), unit_kerja.getLatitude_outer()));
						uk_error.setRequest_status(100);
						ukList.add(uk_error);
					}
					ukList.add(unit_kerja);
				}
			}
			else{
				uk_error.setRequest_status(101);
				ukList.add(uk_error);
			}
		}
		else{
			uk_error.setRequest_status(101);
			ukList.add(uk_error);
		}
		return ukList;
	}
	
	@Override
	public Unit_kerja get_by_unit_kerja(String unit_kerja, String api_key){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        MethodAPIDAO methodDAO = ctx.getBean("methodAPIDAO", MethodAPIDAO.class);
        AplikasiAPIDAO aplikasiDAO = ctx.getBean("aplikasiAPIDAO", AplikasiAPIDAO.class);
        LogAPIDAO logDAO = ctx.getBean("logAPIDAO", LogAPIDAO.class);
        ApiAksesDAO apiDAO = ctx.getBean("apiAksesDAO", ApiAksesDAO.class);
        
        //atribut untuk mengambil data aplikasi dan method
		int id_method;
		String api_keydb;
		int id_aplikasi;
		int id_app = 0;
		String nama_method = "unit_kerja/get_by_unit_kerja";
		Aplikasi_api aplikasi_api = new Aplikasi_api();
		
		aplikasi_api = aplikasiDAO.get_aplikasi_detail(api_key);
		id_aplikasi = aplikasi_api.getId_aplikasi();
		api_keydb = aplikasi_api.getApi_key(); 
		
		id_method = methodDAO.get_id(nama_method);
		
		id_app = apiDAO.get_status(id_aplikasi, id_method);
		
		//atribut untuk menampilkan data unit kerja
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSaource);
		Unit_kerja unit_kerjadb = new Unit_kerja();
		
		if(id_app>0){
			if(api_key.equals(api_keydb)){
				//menambahkan data log_api
				logDAO.tambah(id_aplikasi, id_method);
		
				//String query = "SELECT * FROM unit_kerja WHERE nama_baru like '" + unit_kerja + "%'";
				
				List<Map<String, Object>> ukRows = jdbcTemplate.queryForList("SELECT id_unit_kerja, nama_baru, alamat, telp, id_skpd, x(long_lat) as longitude, y(long_lat) as latitude, x(long_lat_outer) as longitude_outer, y(long_lat_outer) as latitude_outer FROM unit_kerja WHERE nama_baru like ?", unit_kerja + "%");
				
				for (Map<String, Object> ukRow : ukRows){
					unit_kerjadb.setId_unit_kerja(Integer.parseInt(String.valueOf(ukRow.get("id_unit_kerja"))));
					unit_kerjadb.setNama_baru(String.valueOf(ukRow.get("nama_baru")));
					unit_kerjadb.setAlamat(String.valueOf(ukRow.get("alamat")));
					unit_kerjadb.setTelp(String.valueOf(ukRow.get("telp")));
					unit_kerjadb.setId_skpd(Integer.parseInt(String.valueOf(ukRow.get("id_skpd"))));
					String long_db = String.valueOf(ukRow.get("longitude"));
					unit_kerjadb.setLongitude(long_db);
					String lat_db = String.valueOf(ukRow.get("latitude"));
					unit_kerjadb.setLatitude(lat_db);
					String long_db_outer = String.valueOf(ukRow.get("longitude_outer"));
					unit_kerjadb.setLongitude_outer(long_db_outer);
					String lat_db_outer = String.valueOf(ukRow.get("latitude_outer"));
					unit_kerjadb.setLatitude_outer(lat_db_outer);
					if(long_db.equals("null") || long_db_outer.equals("null")){
						unit_kerjadb.setRadius(null);
						unit_kerjadb.setRequest_status(110);
					}
					else{
						unit_kerjadb.setRadius(distance(long_db, lat_db, long_db_outer, lat_db_outer));
						unit_kerjadb.setRequest_status(100);
					}
				}
			}
			else{
				unit_kerjadb.setRequest_status(101);
			}
		}
		else{
			unit_kerjadb.setRequest_status(101);
		}
		return unit_kerjadb;
	}
	
	@Override
	public List<Unit_kerja_mobile_request> get_id_nama(String api_key){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        MethodAPIDAO methodDAO = ctx.getBean("methodAPIDAO", MethodAPIDAO.class);
        AplikasiAPIDAO aplikasiDAO = ctx.getBean("aplikasiAPIDAO", AplikasiAPIDAO.class);
        LogAPIDAO logDAO = ctx.getBean("logAPIDAO", LogAPIDAO.class);
        ApiAksesDAO apiDAO = ctx.getBean("apiAksesDAO", ApiAksesDAO.class);
        
        //atribut untuk mengambil data aplikasi dan method
		int id_method;
		String api_keydb;
		int id_aplikasi;
		int id_app = 0;
		String nama_method = "unit_kerja/get_id_nama";
		Aplikasi_api aplikasi_api = new Aplikasi_api();
		
		aplikasi_api = aplikasiDAO.get_aplikasi_detail(api_key);
		id_aplikasi = aplikasi_api.getId_aplikasi();
		api_keydb = aplikasi_api.getApi_key(); 
		
		id_method = methodDAO.get_id(nama_method);
		
		id_app = apiDAO.get_status(id_aplikasi, id_method);
		
		//atribut untuk menampilkan data unit kerja
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSaource);
		List<Unit_kerja_mobile_request> ukList = new ArrayList<Unit_kerja_mobile_request>();
		Unit_kerja_mobile_request uk_error = new Unit_kerja_mobile_request();
		
		if(id_app>0){
			if(api_key.equals(api_keydb)){
				//menambahkan data log_api
				logDAO.tambah(id_aplikasi, id_method);
			
				//String query = "SELECT * FROM unit_kerja WHERE tahun=SUBSTR(CURDATE(),1,4)";
				
				List<Map<String, Object>> ukRows = jdbcTemplate.queryForList("SELECT * FROM unit_kerja WHERE tahun=(SELECT max(tahun) FROM unit_kerja) AND id_unit_kerja = id_skpd ORDER BY nama_baru");
				
				for (Map<String, Object> ukRow : ukRows){
					Unit_kerja_mobile_request unit_kerja = new Unit_kerja_mobile_request();
					unit_kerja.setId_unit_kerja(Integer.parseInt(String.valueOf(ukRow.get("id_unit_kerja"))));
					unit_kerja.setNama_baru(String.valueOf(ukRow.get("nama_baru")));
					ukList.add(unit_kerja);
				}
			}
			else{
				uk_error.setRequest_status(101);
				ukList.add(uk_error);
			}
		}
		else{
			uk_error.setRequest_status(101);
			ukList.add(uk_error);
		}
		return ukList;
	}
}