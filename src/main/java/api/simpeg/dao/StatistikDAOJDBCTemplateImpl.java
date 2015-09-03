package api.simpeg.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import api.simpeg.model.Statistik;
import api.simpeg.simpeg_api.dao.ApiAksesDAO;
import api.simpeg.simpeg_api.dao.AplikasiAPIDAO;
import api.simpeg.simpeg_api.dao.LogAPIDAO;
import api.simpeg.simpeg_api.dao.MethodAPIDAO;
import api.simpeg.simpeg_api.model.Aplikasi_api;

public class StatistikDAOJDBCTemplateImpl implements StatistikDAO{
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource){
		this.dataSource = dataSource;
	}
	
	@Override
	public List<Statistik> getStatisticBidangPendidikan(String api_key){
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
		String nama_method = "statistic/getStatisticBidangPendidikan";
		Aplikasi_api aplikasi_api = new Aplikasi_api();
		
		aplikasi_api = aplikasiDAO.get_aplikasi_detail(api_key);
		id_aplikasi = aplikasi_api.getId_aplikasi();
		api_keydb = aplikasi_api.getApi_key(); 
		
		id_method = methodDAO.get_id(nama_method);
		
		id_app = apiDAO.get_status(id_aplikasi, id_method);
		
		//atribut untuk menampilkan data unit kerja
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Statistik> statList = new ArrayList<Statistik>();
		Statistik stat_error = new Statistik();
		
		if(id_app>0){
			if(api_key.equals(api_keydb)){
				//menambahkan data log_api
				logDAO.tambah(id_aplikasi, id_method);
			
				List<Map<String, Object>> statRows = jdbcTemplate.queryForList("select count(*) as value, bidang as attribute from pendidikan_terakhir  inner join pegawai on pegawai.id_pegawai = pendidikan_terakhir.id_pegawai inner join pendidikan on pendidikan.id_pendidikan = pendidikan_terakhir.id_pendidikan inner join bidang_pendidikan on bidang_pendidikan.id=pendidikan.id_bidang  where flag_pensiun=0  and pendidikan_terakhir.level_p<7 group by bidang ");
				
				for (Map<String, Object> statRow : statRows){
					Statistik stat = new Statistik();
					stat.setValue(Integer.parseInt(String.valueOf(statRow.get("value"))));
					stat.setAttribute(String.valueOf(statRow.get("attribute")));
					statList.add(stat);
				}
			}
			else{
				stat_error.setRequest_status(101);
				statList.add(stat_error);
			}
		}
		else{
			stat_error.setRequest_status(101);
			statList.add(stat_error);
		}
		return statList;
	}
	
	@Override
	public List<Statistik> getStatisticFungsional(String api_key){
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
		String nama_method = "statistic/getStatisticFungsional";
		Aplikasi_api aplikasi_api = new Aplikasi_api();
		
		aplikasi_api = aplikasiDAO.get_aplikasi_detail(api_key);
		id_aplikasi = aplikasi_api.getId_aplikasi();
		api_keydb = aplikasi_api.getApi_key(); 
		
		id_method = methodDAO.get_id(nama_method);
		
		id_app = apiDAO.get_status(id_aplikasi, id_method);
		
		//atribut untuk menampilkan data unit kerja
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Statistik> statList = new ArrayList<Statistik>();
		Statistik stat_error = new Statistik();
		
		if(id_app>0){
			if(api_key.equals(api_keydb)){
				//menambahkan data log_api
				logDAO.tambah(id_aplikasi, id_method);
			
				List<Map<String, Object>> statRows = jdbcTemplate.queryForList("SELECT count(*) as value, jabatan as attribute from pegawai where flag_pensiun=0 and jenjab like 'fungsional' GROUP BY jabatan");
				
				for (Map<String, Object> statRow : statRows){
					Statistik stat = new Statistik();
					stat.setValue(Integer.parseInt(String.valueOf(statRow.get("value"))));
					stat.setAttribute(String.valueOf(statRow.get("attribute")));
					statList.add(stat);
				}
			}
			else{
				stat_error.setRequest_status(101);
				statList.add(stat_error);
			}
		}
		else{
			stat_error.setRequest_status(101);
			statList.add(stat_error);
		}
		return statList;
	}
	
	@Override
	public List<Statistik> getStatisticGolongan(String api_key){
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
		String nama_method = "statistic/getStatisticGolongan";
		Aplikasi_api aplikasi_api = new Aplikasi_api();
		
		aplikasi_api = aplikasiDAO.get_aplikasi_detail(api_key);
		id_aplikasi = aplikasi_api.getId_aplikasi();
		api_keydb = aplikasi_api.getApi_key(); 
		
		id_method = methodDAO.get_id(nama_method);
		
		id_app = apiDAO.get_status(id_aplikasi, id_method);
		
		//atribut untuk menampilkan data unit kerja
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Statistik> statList = new ArrayList<Statistik>();
		Statistik stat_error = new Statistik();
		
		if(id_app>0){
			if(api_key.equals(api_keydb)){
				//menambahkan data log_api
				logDAO.tambah(id_aplikasi, id_method);
			
				List<Map<String, Object>> statRows = jdbcTemplate.queryForList("SELECT count(*) as value, pangkat_gol as attribute FROM pegawai WHERE flag_pensiun=0 GROUP BY pangkat_gol");
				
				for (Map<String, Object> statRow : statRows){
					Statistik stat = new Statistik();
					stat.setValue(Integer.parseInt(String.valueOf(statRow.get("value"))));
					stat.setAttribute(String.valueOf(statRow.get("attribute")));
					statList.add(stat);
				}
			}
			else{
				stat_error.setRequest_status(101);
				statList.add(stat_error);
			}
		}
		else{
			stat_error.setRequest_status(101);
			statList.add(stat_error);
		}
		return statList;
	}
	
	@Override
	public List<Statistik> getStatisticJabatan(String api_key){
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
		String nama_method = "statistic/getStatisticJabatan";
		Aplikasi_api aplikasi_api = new Aplikasi_api();
		
		aplikasi_api = aplikasiDAO.get_aplikasi_detail(api_key);
		id_aplikasi = aplikasi_api.getId_aplikasi();
		api_keydb = aplikasi_api.getApi_key(); 
		
		id_method = methodDAO.get_id(nama_method);
		
		id_app = apiDAO.get_status(id_aplikasi, id_method);
		
		//atribut untuk menampilkan data unit kerja
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Statistik> statList = new ArrayList<Statistik>();
		Statistik stat_error = new Statistik();
		
		if(id_app>0){
			if(api_key.equals(api_keydb)){
				//menambahkan data log_api
				logDAO.tambah(id_aplikasi, id_method);
			
				List<Map<String, Object>> statRows = jdbcTemplate.queryForList("SELECT count(*) as value, jenjab as attribute FROM pegawai where flag_pensiun=0 GROUP BY jenjab");
				
				for (Map<String, Object> statRow : statRows){
					Statistik stat = new Statistik();
					stat.setValue(Integer.parseInt(String.valueOf(statRow.get("value"))));
					stat.setAttribute(String.valueOf(statRow.get("attribute")));
					statList.add(stat);
				}
			}
			else{
				stat_error.setRequest_status(101);
				statList.add(stat_error);
			}
		}
		else{
			stat_error.setRequest_status(101);
			statList.add(stat_error);
		}
		return statList;
	}
	
	@Override
	public List<Statistik> getStatisticJenisKelamin(String api_key){
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
		String nama_method = "statistic/getStatisticJenisKelamin";
		Aplikasi_api aplikasi_api = new Aplikasi_api();
		
		aplikasi_api = aplikasiDAO.get_aplikasi_detail(api_key);
		id_aplikasi = aplikasi_api.getId_aplikasi();
		api_keydb = aplikasi_api.getApi_key(); 
		
		id_method = methodDAO.get_id(nama_method);
		
		id_app = apiDAO.get_status(id_aplikasi, id_method);
		
		//atribut untuk menampilkan data unit kerja
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Statistik> statList = new ArrayList<Statistik>();
		Statistik stat_error = new Statistik();
		
		if(id_app>0){
			if(api_key.equals(api_keydb)){
				//menambahkan data log_api
				logDAO.tambah(id_aplikasi, id_method);
			
				List<Map<String, Object>> statRows = jdbcTemplate.queryForList("SELECT count(*) as value, jenis_kelamin as attribute FROM pegawai where flag_pensiun=0 GROUP BY jenis_kelamin");
				
				for (Map<String, Object> statRow : statRows){
					Statistik stat = new Statistik();
					stat.setValue(Integer.parseInt(String.valueOf(statRow.get("value"))));
					stat.setAttribute(String.valueOf(statRow.get("attribute")));
					statList.add(stat);
				}
			}
			else{
				stat_error.setRequest_status(101);
				statList.add(stat_error);
			}
		}
		else{
			stat_error.setRequest_status(101);
			statList.add(stat_error);
		}
		return statList;
	}
	
	@Override
	public List<Statistik> getStatisticLulusanPt(String api_key){
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
		String nama_method = "statistic/getStatisticLulusanPt";
		Aplikasi_api aplikasi_api = new Aplikasi_api();
		
		aplikasi_api = aplikasiDAO.get_aplikasi_detail(api_key);
		id_aplikasi = aplikasi_api.getId_aplikasi();
		api_keydb = aplikasi_api.getApi_key(); 
		
		id_method = methodDAO.get_id(nama_method);
		
		id_app = apiDAO.get_status(id_aplikasi, id_method);
		
		//atribut untuk menampilkan data unit kerja
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Statistik> statList = new ArrayList<Statistik>();
		Statistik stat_error = new Statistik();
		
		if(id_app>0){
			if(api_key.equals(api_keydb)){
				//menambahkan data log_api
				logDAO.tambah(id_aplikasi, id_method);
			
				List<Map<String, Object>> statRows = jdbcTemplate.queryForList("select count(*) as value, institusi as attribute from pendidikan_terakhir inner join pegawai on pegawai.id_pegawai = pendidikan_terakhir.id_pegawai  inner join pendidikan on pendidikan.id_pendidikan = pendidikan_terakhir.id_pendidikan  inner join institusi_pendidikan on institusi_pendidikan.id=pendidikan.id_institusi  where flag_pensiun=0 and pendidikan_terakhir.level_p<7 group by institusi");
				
				for (Map<String, Object> statRow : statRows){
					Statistik stat = new Statistik();
					stat.setValue(Integer.parseInt(String.valueOf(statRow.get("value"))));
					stat.setAttribute(String.valueOf(statRow.get("attribute")));
					statList.add(stat);
				}
			}
			else{
				stat_error.setRequest_status(101);
				statList.add(stat_error);
			}
		}
		else{
			stat_error.setRequest_status(101);
			statList.add(stat_error);
		}
		return statList;
	}
	
	@Override
	public List<Statistik> getStatisticPendidikan(String api_key){
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
		String nama_method = "statistic/getStatisticPendidikan";
		Aplikasi_api aplikasi_api = new Aplikasi_api();
		
		aplikasi_api = aplikasiDAO.get_aplikasi_detail(api_key);
		id_aplikasi = aplikasi_api.getId_aplikasi();
		api_keydb = aplikasi_api.getApi_key(); 
		
		id_method = methodDAO.get_id(nama_method);
		
		id_app = apiDAO.get_status(id_aplikasi, id_method);
		
		//atribut untuk menampilkan data unit kerja
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Statistik> statList = new ArrayList<Statistik>();
		Statistik stat_error = new Statistik();
		
		if(id_app>0){
			if(api_key.equals(api_keydb)){
				//menambahkan data log_api
				logDAO.tambah(id_aplikasi, id_method);
			
				List<Map<String, Object>> statRows = jdbcTemplate.queryForList("SELECT count(*) as value, level_p as attribute FROM pendidikan_terakhir inner join pegawai on pegawai.id_pegawai=pendidikan_terakhir.id_pegawai where flag_pensiun=0 GROUP BY level_p");
				
				for (Map<String, Object> statRow : statRows){
					Statistik stat = new Statistik();
					stat.setValue(Integer.parseInt(String.valueOf(statRow.get("value"))));
					stat.setAttribute(String.valueOf(statRow.get("attribute")));
					statList.add(stat);
				}
			}
			else{
				stat_error.setRequest_status(101);
				statList.add(stat_error);
			}
		}
		else{
			stat_error.setRequest_status(101);
			statList.add(stat_error);
		}
		return statList;
	}
	
	@Override
	public List<Statistik> getStatisticStruktural(String api_key){
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
		String nama_method = "statistic/getStatisticStruktural";
		Aplikasi_api aplikasi_api = new Aplikasi_api();
		
		aplikasi_api = aplikasiDAO.get_aplikasi_detail(api_key);
		id_aplikasi = aplikasi_api.getId_aplikasi();
		api_keydb = aplikasi_api.getApi_key(); 
		
		id_method = methodDAO.get_id(nama_method);
		
		id_app = apiDAO.get_status(id_aplikasi, id_method);
		
		//atribut untuk menampilkan data unit kerja
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Statistik> statList = new ArrayList<Statistik>();
		Statistik stat_error = new Statistik();
		
		if(id_app>0){
			if(api_key.equals(api_keydb)){
				//menambahkan data log_api
				logDAO.tambah(id_aplikasi, id_method);
			
				List<Map<String, Object>> statRows = jdbcTemplate.queryForList("SELECT count(*) as value, jabatan.eselon as attribute from pegawai inner join jabatan on jabatan.id_j = pegawai.id_j  WHERE flag_pensiun=0 and jenjab like 'struktural' and pegawai.id_j>0 GROUP BY eselon");
				
				for (Map<String, Object> statRow : statRows){
					Statistik stat = new Statistik();
					stat.setValue(Integer.parseInt(String.valueOf(statRow.get("value"))));
					stat.setAttribute(String.valueOf(statRow.get("attribute")));
					statList.add(stat);
				}
			}
			else{
				stat_error.setRequest_status(101);
				statList.add(stat_error);
			}
		}
		else{
			stat_error.setRequest_status(101);
			statList.add(stat_error);
		}
		return statList;
	}
	
	@Override
	public List<Statistik> getStatisticUmur(String api_key){
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
		String nama_method = "statistic/getStatisticUmur";
		Aplikasi_api aplikasi_api = new Aplikasi_api();
		
		aplikasi_api = aplikasiDAO.get_aplikasi_detail(api_key);
		id_aplikasi = aplikasi_api.getId_aplikasi();
		api_keydb = aplikasi_api.getApi_key(); 
		
		id_method = methodDAO.get_id(nama_method);
		
		id_app = apiDAO.get_status(id_aplikasi, id_method);
		
		//atribut untuk menampilkan data unit kerja
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Statistik> statList = new ArrayList<Statistik>();
		Statistik stat_error = new Statistik();
		
		if(id_app>0){
			if(api_key.equals(api_keydb)){
				//menambahkan data log_api
				logDAO.tambah(id_aplikasi, id_method);
			
				List<Map<String, Object>> statRows = jdbcTemplate.queryForList("SELECT count(*)  as value, floor(datediff(curdate(),tgl_lahir)/365) as attribute FROM pegawai  WHERE flag_pensiun=0 group by floor(datediff(curdate(),tgl_lahir)/365)");
				
				for (Map<String, Object> statRow : statRows){
					Statistik stat = new Statistik();
					stat.setValue(Integer.parseInt(String.valueOf(statRow.get("value"))));
					stat.setAttribute(String.valueOf(statRow.get("attribute")));
					statList.add(stat);
				}
			}
			else{
				stat_error.setRequest_status(101);
				statList.add(stat_error);
			}
		}
		else{
			stat_error.setRequest_status(101);
			statList.add(stat_error);
		}
		return statList;
	}
}