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
import api.simpeg.model.Pegawai_mobile;
import api.simpeg.simpeg_api.dao.ApiAksesDAO;
import api.simpeg.simpeg_api.dao.AplikasiAPIDAO;
import api.simpeg.simpeg_api.dao.LogAPIDAO;
import api.simpeg.simpeg_api.dao.MethodAPIDAO;
import api.simpeg.simpeg_api.model.Api_akses;
import api.simpeg.simpeg_api.model.Aplikasi_api;

public class PegawaiMobileDAOJDBCTemplateImpl implements PegawaiMobileDAO{
	private DataSource dataSource;
	
	@Autowired
	public void setDataSource(DataSource dataSource){
		this.dataSource = dataSource;
	}
	
	@Override
	public String cek_nip(String nip){
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String nip_pgw = null;
		
		List<Map<String, Object>> nipRows = jdbcTemplate.queryForList("SELECT  nip_baru FROM pegawai WHERE nip_baru=?", nip);
		
		for (Map<String, Object> nipRow : nipRows){
			nip_pgw = String.valueOf(nipRow.get("nip_baru"));
		}
		return nip_pgw;
	}
	
	@Override
	public List<Pegawai_mobile> login(String nip, String password, String imei, String android_api_level, String api_key){
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
		String nama_method = "pegawai_mobile/login";
		Aplikasi_api aplikasi_api = new Aplikasi_api();
		Api_akses app_akses = new Api_akses();
		
		aplikasi_api = aplikasiDAO.get_aplikasi_detail(api_key);
		id_aplikasi = aplikasi_api.getId_aplikasi();
		api_keydb = aplikasi_api.getApi_key(); 
		
		id_method = methodDAO.get_id(nama_method);
		
		app_akses = apiDAO.get_by_method(id_method);
		id_app = app_akses.getId_aplikasi();
		
		//atribut untuk menampilkan data pegawai
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Pegawai_mobile> pmList = new ArrayList<Pegawai_mobile>();
		int error = 0;
		Pegawai_mobile pegawai = new Pegawai_mobile();
		String nip_pgw = null;
		String jenkel = null;
		String cek_imei_count = null;
		String cek_imei_count2 = null;
		
		if(id_app == id_aplikasi){
			if(api_key.equals(api_keydb)){
				List<Map<String, Object>> cek_imei_db = jdbcTemplate.queryForList("SELECT imei FROM pegawai WHERE imei=?", imei);
				
				for (Map<String, Object> cek_imei_dbd : cek_imei_db){
					cek_imei_count = String.valueOf(cek_imei_dbd.get("imei"));
				}
				
				List<Map<String, Object>> cek_imei_db_user = jdbcTemplate.queryForList("SELECT imei FROM pegawai WHERE imei=? AND nip_baru=?", imei, nip);
				
				for (Map<String, Object> cek_imei_dbd_user : cek_imei_db_user){
					cek_imei_count2 = String.valueOf(cek_imei_dbd_user.get("imei"));
				}
								
				if(cek_imei_db.isEmpty()){
					//menambahkan data log_api
					logDAO.tambah(id_aplikasi, id_method);
					
					//cek nip
					nip_pgw = cek_nip(nip);
					
					//String query = "SELECT p.id_pegawai, p.nama, p.gelar_depan, p.gelar_belakang, p.jenis_kelamin, p.gol_darah, p.agama, p.tempat_lahir, p.tgl_lahir, p.alamat, p.nip_baru, p.no_karpeg, p.masa_kerja_pasif, p.pangkat_gol, p.jenjab, p.jabatan, p.eselonering, p.tgl_pensiun_dini_old, p.tgl_pensiun_dini, p.imei, p.flag_pensiun, p.password, c.id_unit_kerja, u.nama_baru FROM (pegawai as p INNER JOIN current_lokasi_kerja as c on p.id_pegawai = c.id_pegawai) INNER JOIN unit_kerja as u on c.id_unit_kerja = u.id_unit_kerja WHERE p.flag_pensiun=0 AND p.nip_baru=" + nip;
					List<Map<String, Object>> pmRows = jdbcTemplate.queryForList("SELECT p.id_pegawai, p.nama, p.gelar_depan, p.gelar_belakang, p.jenis_kelamin, p.gol_darah, p.agama, p.tempat_lahir, p.tgl_lahir, p.alamat, p.nip_baru, p.no_karpeg, p.masa_kerja_pasif, p.pangkat_gol, p.jenjab, p.jabatan, p.eselonering, p.tgl_pensiun_dini_old, p.tgl_pensiun_dini, p.imei, p.flag_pensiun, p.password, c.id_unit_kerja, u.nama_baru FROM (pegawai as p INNER JOIN current_lokasi_kerja as c on p.id_pegawai = c.id_pegawai) INNER JOIN unit_kerja as u on c.id_unit_kerja = u.id_unit_kerja WHERE p.flag_pensiun=0 AND p.nip_baru=? AND p.password=?", nip, password);
					
					for (Map<String, Object> pmRow : pmRows){
						pegawai.setId_pegawai(Integer.parseInt(String.valueOf(pmRow.get("id_pegawai"))));
						pegawai.setNama(String.valueOf(pmRow.get("nama")));
						pegawai.setGelar_depan(String.valueOf(pmRow.get("gelar_depan")));
						pegawai.setGelar_belakang(String.valueOf(pmRow.get("gelar_belakang")));
						
						if(Integer.parseInt(String.valueOf(pmRow.get("jenis_kelamin"))) == 1){
							jenkel = "Laki-laki";
						}
						else{
							jenkel = "Perempuan";
						}
						
						pegawai.setJenis_kelamin(String.valueOf(jenkel));
						pegawai.setGol_darah(String.valueOf(pmRow.get("gol_darah")));
						pegawai.setAgama(String.valueOf(pmRow.get("agama")));
						pegawai.setTempat_lahir(String.valueOf(pmRow.get("tempat_lahir")));
						pegawai.setTgl_lahir(String.valueOf(pmRow.get("tgl_lahir")));
						pegawai.setAlamat(String.valueOf(pmRow.get("alamat")));
						pegawai.setNip_baru(String.valueOf(pmRow.get("nip_baru")));
						pegawai.setNo_karpeg(String.valueOf(pmRow.get("no_karpeg")));
						pegawai.setMasa_kerja_pasif(Integer.parseInt(String.valueOf(pmRow.get("masa_kerja_pasif"))));
						pegawai.setPangkat_gol(String.valueOf(pmRow.get("pangkat_gol")));
						pegawai.setJenjab(String.valueOf(pmRow.get("jenjab")));
						pegawai.setJabatan(String.valueOf(pmRow.get("jabatan")));
						pegawai.setEseloning(String.valueOf(pmRow.get("eselonering")));
						pegawai.setTgl_pensiun_dini_old(String.valueOf(pmRow.get("tgl_pensiun_dini_old")));
						pegawai.setTgl_pensiun_dini(String.valueOf(pmRow.get("tgl_pensiun_dini")));
						pegawai.setImei(String.valueOf(pmRow.get("imei")).toString());
						pegawai.setUnit_kerja(String.valueOf(pmRow.get("nama_baru")));
						pegawai.setPassword(String.valueOf(pmRow.get("password")));
					}
					
					if(nip.equals(nip_pgw)){
						if(password.equals(pegawai.getPassword())){
							if(imei.equals(pegawai.getImei())){
		
								pegawai.setImei("ok,"+imei);
							}
							else if(!(imei.equals(pegawai.getImei()))){
								Long imei_long = Long.parseLong(imei);
								//String sql = "UPDATE pegawai SET imei=" + imei_long + " WHERE nip_baru='" + nip + "'";
								
								jdbcTemplate.update("UPDATE pegawai SET imei=? WHERE nip_baru=?", imei_long, nip);
								pegawai.setImei("different,"+imei);
							}
							else{
								pmList.clear();
							}
						}
						else{
							pegawai.setRequest_status(103);
						}
					}
					else{
						pegawai.setRequest_status(102);
					}
				}
				else{
					if(cek_imei_db_user.isEmpty()){
						pegawai.setRequest_status(111);
					}
					else{
						//menambahkan data log_api
						logDAO.tambah(id_aplikasi, id_method);
						
						//cek nip
						nip_pgw = cek_nip(nip);
						
						//String query = "SELECT p.id_pegawai, p.nama, p.gelar_depan, p.gelar_belakang, p.jenis_kelamin, p.gol_darah, p.agama, p.tempat_lahir, p.tgl_lahir, p.alamat, p.nip_baru, p.no_karpeg, p.masa_kerja_pasif, p.pangkat_gol, p.jenjab, p.jabatan, p.eselonering, p.tgl_pensiun_dini_old, p.tgl_pensiun_dini, p.imei, p.flag_pensiun, p.password, c.id_unit_kerja, u.nama_baru FROM (pegawai as p INNER JOIN current_lokasi_kerja as c on p.id_pegawai = c.id_pegawai) INNER JOIN unit_kerja as u on c.id_unit_kerja = u.id_unit_kerja WHERE p.flag_pensiun=0 AND p.nip_baru=" + nip;
						List<Map<String, Object>> pmRows = jdbcTemplate.queryForList("SELECT p.id_pegawai, p.nama, p.gelar_depan, p.gelar_belakang, p.jenis_kelamin, p.gol_darah, p.agama, p.tempat_lahir, p.tgl_lahir, p.alamat, p.nip_baru, p.no_karpeg, p.masa_kerja_pasif, p.pangkat_gol, p.jenjab, p.jabatan, p.eselonering, p.tgl_pensiun_dini_old, p.tgl_pensiun_dini, p.imei, p.flag_pensiun, p.password, c.id_unit_kerja, u.nama_baru FROM (pegawai as p INNER JOIN current_lokasi_kerja as c on p.id_pegawai = c.id_pegawai) INNER JOIN unit_kerja as u on c.id_unit_kerja = u.id_unit_kerja WHERE p.flag_pensiun=0 AND p.nip_baru=? AND p.password=?", nip, password);
						
						for (Map<String, Object> pmRow : pmRows){
							pegawai.setId_pegawai(Integer.parseInt(String.valueOf(pmRow.get("id_pegawai"))));
							pegawai.setNama(String.valueOf(pmRow.get("nama")));
							pegawai.setGelar_depan(String.valueOf(pmRow.get("gelar_depan")));
							pegawai.setGelar_belakang(String.valueOf(pmRow.get("gelar_belakang")));
							
							if(Integer.parseInt(String.valueOf(pmRow.get("jenis_kelamin"))) == 1){
								jenkel = "Laki-laki";
							}
							else{
								jenkel = "Perempuan";
							}
							
							pegawai.setJenis_kelamin(String.valueOf(jenkel));
							pegawai.setGol_darah(String.valueOf(pmRow.get("gol_darah")));
							pegawai.setAgama(String.valueOf(pmRow.get("agama")));
							pegawai.setTempat_lahir(String.valueOf(pmRow.get("tempat_lahir")));
							pegawai.setTgl_lahir(String.valueOf(pmRow.get("tgl_lahir")));
							pegawai.setAlamat(String.valueOf(pmRow.get("alamat")));
							pegawai.setNip_baru(String.valueOf(pmRow.get("nip_baru")));
							pegawai.setNo_karpeg(String.valueOf(pmRow.get("no_karpeg")));
							pegawai.setMasa_kerja_pasif(Integer.parseInt(String.valueOf(pmRow.get("masa_kerja_pasif"))));
							pegawai.setPangkat_gol(String.valueOf(pmRow.get("pangkat_gol")));
							pegawai.setJenjab(String.valueOf(pmRow.get("jenjab")));
							pegawai.setJabatan(String.valueOf(pmRow.get("jabatan")));
							pegawai.setEseloning(String.valueOf(pmRow.get("eselonering")));
							pegawai.setTgl_pensiun_dini_old(String.valueOf(pmRow.get("tgl_pensiun_dini_old")));
							pegawai.setTgl_pensiun_dini(String.valueOf(pmRow.get("tgl_pensiun_dini")));
							pegawai.setImei(String.valueOf(pmRow.get("imei")).toString());
							pegawai.setUnit_kerja(String.valueOf(pmRow.get("nama_baru")));
							pegawai.setPassword(String.valueOf(pmRow.get("password")));
						}
						
						if(nip.equals(nip_pgw)){
							if(password.equals(pegawai.getPassword())){
								if(imei.equals(pegawai.getImei())){
			
									pegawai.setImei("ok,"+imei);
								}
								else if(!(imei.equals(pegawai.getImei()))){
									Long imei_long = Long.parseLong(imei);
									//String sql = "UPDATE pegawai SET imei=" + imei_long + " WHERE nip_baru='" + nip + "'";
									
									jdbcTemplate.update("UPDATE pegawai SET imei=? WHERE nip_baru=?", imei_long, nip);
									pegawai.setImei("different,"+imei);
								}
								else{
									pmList.clear();
								}
							}
							else{
								pegawai.setRequest_status(103);
							}
						}
						else{
							pegawai.setRequest_status(102);
						}
					}
				}
			}
		}
		else{
			pegawai.setRequest_status(101);
		}
		pmList.add(pegawai);
		return pmList;
	}
	
	@Override
	public Pegawai_mobile get_imei(String id_pegawai){
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String imei = null;
		Pegawai_mobile pgw = new Pegawai_mobile();
		
		List<Map<String, Object>> pgwRows = jdbcTemplate.queryForList("SELECT imei FROM pegawai WHERE id_pegawai=?", id_pegawai);
		
		for (Map<String, Object> pgwRow : pgwRows){
			imei = String.valueOf(pgwRow.get("imei"));
		}
		
		System.out.println(imei);
		
		pgw.setImei(imei);
		
		return pgw;
	}
}