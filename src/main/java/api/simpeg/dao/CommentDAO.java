package api.simpeg.dao;

import java.util.List;

import api.simpeg.model.Comment;

public interface CommentDAO{
	
	public List<Comment> get_comments(String parent_id, String api_key);
}