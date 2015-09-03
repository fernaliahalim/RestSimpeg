package api.simpeg.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import api.simpeg.model.Absensi;
import api.simpeg.model.Pegawai;
import api.simpeg.simpeg_api.dao.ApiAksesDAO;
import api.simpeg.simpeg_api.dao.AplikasiAPIDAO;
import api.simpeg.simpeg_api.dao.LogAPIDAO;
import api.simpeg.simpeg_api.dao.MethodAPIDAO;
import api.simpeg.simpeg_api.model.Api_akses;
import api.simpeg.simpeg_api.model.Aplikasi_api;

@Repository
public class AbsensiDAOJDBCTemplateImpl implements AbsensiDAO{
	private DataSource dataSaource;
	
	@Autowired
	public void setDataSource(DataSource dataSource){
		this.dataSaource = dataSource;
	}
	
	@Override
	public List<Absensi> get_hadir(String unit_kerja, String tgl, String api_key){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        MethodAPIDAO methodDAO = ctx.getBean("methodAPIDAO", MethodAPIDAO.class);
        AplikasiAPIDAO aplikasiDAO = ctx.getBean("aplikasiAPIDAO", AplikasiAPIDAO.class);
        LogAPIDAO logDAO = ctx.getBean("logAPIDAO", LogAPIDAO.class);
        ApiAksesDAO apiDAO = ctx.getBean("apiAksesDAO", ApiAksesDAO.class);
        
        //atribut untuk mengambil data aplikasi dan method
		int id_method;
		String api_keydb;
		int id_aplikasi;
		int id_app;
		String nama_method = "absensi/get_hadir";
		Aplikasi_api aplikasi_api = new Aplikasi_api();
		Api_akses app_akses = new Api_akses();
		
		aplikasi_api = aplikasiDAO.get_aplikasi_detail(api_key);
		id_aplikasi = aplikasi_api.getId_aplikasi();
		api_keydb = aplikasi_api.getApi_key(); 
		
		id_method = methodDAO.get_id(nama_method);
		
		app_akses = apiDAO.get_by_method(id_method);
		id_app = app_akses.getId_aplikasi();
		
		//atribut untuk menampilkan data absensi yang hadir
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSaource);
		List<Absensi> absList = new ArrayList<Absensi>();
		Absensi abs_error = new Absensi();
		
		if(id_app == id_aplikasi){
			if(api_key.equals(api_keydb)){
				//menambahkan data log_api
				logDAO.tambah(id_aplikasi, id_method);
				
				//String query = "select u.nama_baru, o.id, count(o.status) as jumlah from ((unit_kerja as u INNER JOIN current_lokasi_kerja as c on u.id_unit_kerja = c.id_unit_kerja) inner join oasys_attendance_log as o on o.id_pegawai=c.id_pegawai ) where o.status='PRESENT' AND o.date_time like '"+ tgl +"%' AND u.nama_baru like '"+unit_kerja +"%' GROUP BY (u.nama_baru)";
				//String query2 = "SELECT count(c.id_unit_kerja) as jml FROM current_lokasi_kerja as c INNER JOIN unit_kerja as u WHERE c.id_unit_kerja=u.id_unit_kerja AND u.tahun=2015 AND u.nama_baru like '"+ unit_kerja +"%'";
				List<Map<String, Object>> absRows = jdbcTemplate.queryForList("select u.nama_baru, o.id, count(o.status) as jumlah from ((unit_kerja as u INNER JOIN current_lokasi_kerja as c on u.id_unit_kerja = c.id_unit_kerja) inner join oasys_attendance_log as o on o.id_pegawai=c.id_pegawai ) where o.status='PRESENT' AND o.date_time like ? AND u.nama_baru like ? GROUP BY (u.nama_baru)", tgl + "%", unit_kerja + "%");
				List<Map<String, Object>> jmlRows = jdbcTemplate.queryForList("SELECT count(c.id_unit_kerja) as jml FROM current_lokasi_kerja as c INNER JOIN unit_kerja as u WHERE c.id_unit_kerja=u.id_unit_kerja AND u.tahun=(SELECT max(tahun) FROM unit_kerja) AND u.nama_baru like ?", unit_kerja + "%");
				
				for (Map<String, Object> absRow : absRows){
					Absensi absensi = new Absensi();
					absensi.setId(Integer.parseInt(String.valueOf(absRow.get("id"))));
					absensi.setJumlah(Integer.parseInt(String.valueOf(absRow.get("jumlah"))));
					absensi.setUnit_kerja(String.valueOf(absRow.get("nama_baru")));
					for (Map<String, Object> jmlRow : jmlRows){
						absensi.setJml_pgw(Integer.parseInt(String.valueOf(jmlRow.get("jml"))));
					}
					absList.add(absensi);
				}
			}
			else{
				abs_error.setRequest_status(101);
				absList.add(abs_error);
			}
		}
		else{
			abs_error.setRequest_status(101);
			absList.add(abs_error);
		}
		return absList;
	}
	
	@Override
	public List<Absensi> get_sakit(String unit_kerja, String tgl, String api_key){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        MethodAPIDAO methodDAO = ctx.getBean("methodAPIDAO", MethodAPIDAO.class);
        AplikasiAPIDAO aplikasiDAO = ctx.getBean("aplikasiAPIDAO", AplikasiAPIDAO.class);
        LogAPIDAO logDAO = ctx.getBean("logAPIDAO", LogAPIDAO.class);
        ApiAksesDAO apiDAO = ctx.getBean("apiAksesDAO", ApiAksesDAO.class);
        
        //atribut untuk mengambil data aplikasi dan method
		int id_method;
		String api_keydb;
		int id_aplikasi;
		int id_app;
		String nama_method = "absensi/get_sakit";
		Aplikasi_api aplikasi_api = new Aplikasi_api();
		Api_akses app_akses = new Api_akses();
		
		aplikasi_api = aplikasiDAO.get_aplikasi_detail(api_key);
		id_aplikasi = aplikasi_api.getId_aplikasi();
		api_keydb = aplikasi_api.getApi_key(); 
		
		id_method = methodDAO.get_id(nama_method);
		
		app_akses = apiDAO.get_by_method(id_method);
		id_app = app_akses.getId_aplikasi();
		
		//atribut untuk menampilkan data absensi yang sakit
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSaource);
		List<Absensi> absList = new ArrayList<Absensi>();
		Absensi abs_error = new Absensi();
		
		if(id_app == id_aplikasi){
			if(api_key.equals(api_keydb)){
				//menambahkan data log_api
				logDAO.tambah(id_aplikasi, id_method);
			
				//String query = "select u.nama_baru, o.id, count(o.status) as jumlah from ((unit_kerja as u INNER JOIN current_lokasi_kerja as c on u.id_unit_kerja = c.id_unit_kerja) inner join oasys_absent_reason_log as o on o.id_pegawai=c.id_pegawai ) where o.status='SICK' AND o.date_time like '"+ tgl +"%' AND u.nama_baru like '"+unit_kerja +"%' GROUP BY (u.nama_baru)";
				//String query2 = "SELECT count(c.id_unit_kerja) as jml FROM current_lokasi_kerja as c INNER JOIN unit_kerja as u WHERE c.id_unit_kerja=u.id_unit_kerja AND u.tahun=2015 AND u.nama_baru like '"+ unit_kerja +"%'";
				List<Map<String, Object>> absRows = jdbcTemplate.queryForList("select u.nama_baru, o.id, count(o.status) as jumlah from ((unit_kerja as u INNER JOIN current_lokasi_kerja as c on u.id_unit_kerja = c.id_unit_kerja) inner join oasys_absent_reason_log as o on o.id_pegawai=c.id_pegawai ) where o.status='SICK' AND o.date_time like ? AND u.nama_baru like ? GROUP BY (u.nama_baru)", tgl + "%", unit_kerja + "%");
				List<Map<String, Object>> jmlRows = jdbcTemplate.queryForList("SELECT count(c.id_unit_kerja) as jml FROM current_lokasi_kerja as c INNER JOIN unit_kerja as u WHERE c.id_unit_kerja=u.id_unit_kerja AND u.tahun=(SELECT max(tahun) FROM unit_kerja) AND u.nama_baru like ? ", unit_kerja + "%");
				
				for (Map<String, Object> absRow : absRows){
					Absensi absensi = new Absensi();
					absensi.setId(Integer.parseInt(String.valueOf(absRow.get("id"))));
					absensi.setJumlah(Integer.parseInt(String.valueOf(absRow.get("jumlah"))));
					absensi.setUnit_kerja(String.valueOf(absRow.get("nama_baru")));
					for (Map<String, Object> jmlRow : jmlRows){
						absensi.setJml_pgw(Integer.parseInt(String.valueOf(jmlRow.get("jml"))));
					}
					absList.add(absensi);
				}
			}
			else{
				abs_error.setRequest_status(101);
				absList.add(abs_error);
			}
		}
		else{
			abs_error.setRequest_status(101);
			absList.add(abs_error);
		}
		return absList;
	}
	
	@Override
	public List<Absensi> get_ijin(String unit_kerja, String tgl, String api_key){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        MethodAPIDAO methodDAO = ctx.getBean("methodAPIDAO", MethodAPIDAO.class);
        AplikasiAPIDAO aplikasiDAO = ctx.getBean("aplikasiAPIDAO", AplikasiAPIDAO.class);
        LogAPIDAO logDAO = ctx.getBean("logAPIDAO", LogAPIDAO.class);
        ApiAksesDAO apiDAO = ctx.getBean("apiAksesDAO", ApiAksesDAO.class);
        
        //atribut untuk mengambil data aplikasi dan method
		int id_method;
		String api_keydb;
		int id_aplikasi;
		int id_app;
		String nama_method = "absensi/get_izin";
		Aplikasi_api aplikasi_api = new Aplikasi_api();
		Api_akses app_akses = new Api_akses();
		
		aplikasi_api = aplikasiDAO.get_aplikasi_detail(api_key);
		id_aplikasi = aplikasi_api.getId_aplikasi();
		api_keydb = aplikasi_api.getApi_key(); 
		
		id_method = methodDAO.get_id(nama_method);
		
		app_akses = apiDAO.get_by_method(id_method);
		id_app = app_akses.getId_aplikasi();
		
		//atribut untuk menampilkan data absensi izin 
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSaource);
		List<Absensi> absList = new ArrayList<Absensi>();
		Absensi abs_error = new Absensi();
		
		if(id_app == id_aplikasi){
			if(api_key.equals(api_keydb)){
				//menambahkan data log_api
				logDAO.tambah(id_aplikasi, id_method);
			
				//String query = "select u.nama_baru, o.id, count(o.status) as jumlah from ((unit_kerja as u INNER JOIN current_lokasi_kerja as c on u.id_unit_kerja = c.id_unit_kerja) inner join oasys_absent_reason_log as o on o.id_pegawai=c.id_pegawai ) where o.status='EXCUSED' AND o.date_time like '"+ tgl +"%' AND u.nama_baru like '"+unit_kerja +"%' GROUP BY (u.nama_baru)";
				//String query2 = "SELECT count(c.id_unit_kerja) as jml FROM current_lokasi_kerja as c INNER JOIN unit_kerja as u WHERE c.id_unit_kerja=u.id_unit_kerja AND u.tahun=2015 AND u.nama_baru like '"+ unit_kerja +"%'";
				List<Map<String, Object>> absRows = jdbcTemplate.queryForList("select u.nama_baru, o.id, count(o.status) as jumlah from ((unit_kerja as u INNER JOIN current_lokasi_kerja as c on u.id_unit_kerja = c.id_unit_kerja) inner join oasys_absent_reason_log as o on o.id_pegawai=c.id_pegawai ) where o.status='EXCUSED' AND o.date_time like ? AND u.nama_baru like ? GROUP BY (u.nama_baru)", tgl + "%", unit_kerja + "%");
				List<Map<String, Object>> jmlRows = jdbcTemplate.queryForList("SELECT count(c.id_unit_kerja) as jml FROM current_lokasi_kerja as c INNER JOIN unit_kerja as u WHERE c.id_unit_kerja=u.id_unit_kerja AND u.tahun=(SELECT max(tahun) FROM unit_kerja) AND u.nama_baru like ?", unit_kerja + "%");
				
				for (Map<String, Object> absRow : absRows){
					Absensi absensi = new Absensi();
					absensi.setId(Integer.parseInt(String.valueOf(absRow.get("id"))));
					absensi.setJumlah(Integer.parseInt(String.valueOf(absRow.get("jumlah"))));
					absensi.setUnit_kerja(String.valueOf(absRow.get("nama_baru")));
					for (Map<String, Object> jmlRow : jmlRows){
						absensi.setJml_pgw(Integer.parseInt(String.valueOf(jmlRow.get("jml"))));
					}
					absList.add(absensi);
				}
			}
			else{
				abs_error.setRequest_status(101);
				absList.add(abs_error);
			}
		}
		else{
			abs_error.setRequest_status(101);
			absList.add(abs_error);
		}
		return absList;
	}
	
	@Override
	public List<Absensi> get_alpha(String unit_kerja, String tgl, String api_key){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        MethodAPIDAO methodDAO = ctx.getBean("methodAPIDAO", MethodAPIDAO.class);
        AplikasiAPIDAO aplikasiDAO = ctx.getBean("aplikasiAPIDAO", AplikasiAPIDAO.class);
        LogAPIDAO logDAO = ctx.getBean("logAPIDAO", LogAPIDAO.class);
        ApiAksesDAO apiDAO = ctx.getBean("apiAksesDAO", ApiAksesDAO.class);
        
        //atribut untuk mengambil data aplikasi dan method
		int id_method;
		String api_keydb;
		int id_aplikasi;
		int id_app;
		String nama_method = "absensi/get_alpha";
		Aplikasi_api aplikasi_api = new Aplikasi_api();
		Api_akses app_akses = new Api_akses();
		
		aplikasi_api = aplikasiDAO.get_aplikasi_detail(api_key);
		id_aplikasi = aplikasi_api.getId_aplikasi();
		api_keydb = aplikasi_api.getApi_key(); 
		
		id_method = methodDAO.get_id(nama_method);
		
		app_akses = apiDAO.get_by_method(id_method);
		id_app = app_akses.getId_aplikasi();
		
		//atribut untuk menampilkan data absensi alpha
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSaource);
		List<Absensi> absList = new ArrayList<Absensi>();
		Absensi abs_error = new Absensi();
		
		if(id_app == id_aplikasi){
			if(api_key.equals(api_keydb)){
				//menambahkan data log_api
				logDAO.tambah(id_aplikasi, id_method);
			
				//String query = "select u.nama_baru, o.id, count(o.status) as jumlah from ((unit_kerja as u INNER JOIN current_lokasi_kerja as c on u.id_unit_kerja = c.id_unit_kerja) inner join oasys_absent_reason_log as o on o.id_pegawai=c.id_pegawai ) where o.status='ABSENT' AND o.date_time like '"+ tgl +"%' AND u.nama_baru like '"+unit_kerja +"%' GROUP BY (u.nama_baru)";
				//String query2 = "SELECT count(c.id_unit_kerja) as jml FROM current_lokasi_kerja as c INNER JOIN unit_kerja as u WHERE c.id_unit_kerja=u.id_unit_kerja AND u.tahun=2015 AND u.nama_baru like '"+ unit_kerja +"%'";
				List<Map<String, Object>> absRows = jdbcTemplate.queryForList("select u.nama_baru, o.id, count(o.status) as jumlah from ((unit_kerja as u INNER JOIN current_lokasi_kerja as c on u.id_unit_kerja = c.id_unit_kerja) inner join oasys_absent_reason_log as o on o.id_pegawai=c.id_pegawai ) where o.status='ABSENT' AND o.date_time like ? AND u.nama_baru like ? GROUP BY (u.nama_baru)", tgl + "%", unit_kerja + "%");
				List<Map<String, Object>> jmlRows = jdbcTemplate.queryForList("SELECT count(c.id_unit_kerja) as jml FROM current_lokasi_kerja as c INNER JOIN unit_kerja as u WHERE c.id_unit_kerja=u.id_unit_kerja AND u.tahun=(SELECT max(tahun) FROM unit_kerja) AND u.nama_baru like ?", unit_kerja + "%");
				
				for (Map<String, Object> absRow : absRows){
					Absensi absensi = new Absensi();
					absensi.setId(Integer.parseInt(String.valueOf(absRow.get("id"))));
					absensi.setJumlah(Integer.parseInt(String.valueOf(absRow.get("jumlah"))));
					absensi.setUnit_kerja(String.valueOf(absRow.get("nama_baru")));
					for (Map<String, Object> jmlRow : jmlRows){
						absensi.setJml_pgw(Integer.parseInt(String.valueOf(jmlRow.get("jml"))));
					}
					absList.add(absensi);
				}
			}
			else{
				abs_error.setRequest_status(101);
				absList.add(abs_error);
			}
		}
		else{
			abs_error.setRequest_status(101);
			absList.add(abs_error);
		}
		return absList;
	}
}