package api.simpeg.controllers;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import api.simpeg.dao.CommentDAO;
import api.simpeg.model.Comment;

@RestController
public class CommentRestController{
	
	@RequestMapping(value="post/get_comments", method=RequestMethod.POST)
	@ResponseBody
	List<Comment> post_get_comments(@RequestParam(value="parent_id") String parent_id, @RequestParam(value="api_key") String api_key){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		CommentDAO commentDAO = ctx.getBean("commentDAO",CommentDAO.class);
		List<Comment> commentList = commentDAO.get_comments(parent_id, api_key);
		
		return commentList;
	}
}