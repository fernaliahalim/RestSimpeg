package api.simpeg.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import api.simpeg.model.Comment;
import api.simpeg.simpeg_api.dao.ApiAksesDAO;
import api.simpeg.simpeg_api.dao.AplikasiAPIDAO;
import api.simpeg.simpeg_api.dao.LogAPIDAO;
import api.simpeg.simpeg_api.dao.MethodAPIDAO;
import api.simpeg.simpeg_api.model.Aplikasi_api;

public class CommentDAOJDBCTemplateImpl implements CommentDAO{
	private DataSource dataSource;
	
	@Autowired
	public void setDataSource(DataSource dataSource){
		this.dataSource = dataSource;
	}
	
	@Override
	public List<Comment> get_comments(String parent_id, String api_key){
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
		String nama_method = "post/get_comments";
		Aplikasi_api aplikasi_api = new Aplikasi_api();
		
		aplikasi_api = aplikasiDAO.get_aplikasi_detail(api_key);
		id_aplikasi = aplikasi_api.getId_aplikasi();
		api_keydb = aplikasi_api.getApi_key(); 
		
		id_method = methodDAO.get_id(nama_method);
		
		id_app = apiDAO.get_status(id_aplikasi, id_method);
		
		//atribut untuk menampilkan data comment
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Comment> commentList = new ArrayList<Comment>();
		Comment comment_error = new Comment();
		
		if(id_app>0){
			if(api_key.equals(api_keydb)){
				//menambahkan data log_api
				logDAO.tambah(id_aplikasi, id_method);
				
				List<Map<String, Object>> commentRows = jdbcTemplate.queryForList("SELECT 	post.id_post, post.msg, post.id_pegawai, pegawai.nama, pegawai.gelar_belakang, UNIX_TIMESTAMP(post.kapan) as kapan, post.parent_id FROM post, pegawai WHERE post.id_pegawai = pegawai.id_pegawai  AND post.parent_id  = ? ORDER BY post.id_post ASC", parent_id);
				
				for (Map<String, Object> commentRow : commentRows){
					Comment comment = new Comment();
					comment.setId_post(Integer.parseInt(String.valueOf(commentRow.get("id_post"))));
					comment.setMsg(String.valueOf(commentRow.get("msg")));
					comment.setId_pegawai(Integer.parseInt(String.valueOf(commentRow.get("id_pegawai"))));
					comment.setNama(String.valueOf(commentRow.get("nama")));
					comment.setGelar_belakang(String.valueOf(commentRow.get("gelar_belakang")));
					comment.setKapan(String.valueOf(commentRow.get("kapan")));
					comment.setParent_id(String.valueOf(commentRow.get("parent_id")));
					commentList.add(comment);
				}
			}
			else{
				comment_error.setRequest_status(101);
				commentList.add(comment_error);
			}
		}
		else{
			comment_error.setRequest_status(101);
			commentList.add(comment_error);
		}
		return commentList;
	}
}