package api.simpeg.dao;

import java.util.List;

import api.simpeg.model.Post;

public interface PostDAO{
	
	public int komentar(String parent_id);
	
	public List<Post> get_timeline(String jumlah, String api_key);
	
	public String add_comment(String msg, String id_pegawai, String parent_id, String api_key);
	
	public String add_message(String msg, String id_pegawai, String api_key);
}