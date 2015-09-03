package api.simpeg.controllers;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import api.simpeg.dao.PostDAO;
import api.simpeg.model.Post;

@RestController
public class PostRestController{
	
	@RequestMapping(value="/post/get_timeline", method=RequestMethod.POST)
	@ResponseBody
	List<Post> post_get_timeline(@RequestParam(value="jumlah") String jumlah, @RequestParam(value="api_key") String api_key){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		PostDAO postDAO = ctx.getBean("postDAO", PostDAO.class);
		List<Post> postList = postDAO.get_timeline(jumlah, api_key);
		
		return postList;
	}
	
	@RequestMapping(value="post/add_comments", method=RequestMethod.POST)
	@ResponseBody
	String post_add_comments(@RequestParam(value="msg") String msg, @RequestParam(value="id_pegawai") String id_pegawai, @RequestParam(value="parent_id") String parent_id, @RequestParam(value="api_key") String api_key){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		PostDAO postDAO = ctx.getBean("postDAO", PostDAO.class);
		String status = postDAO.add_comment(msg, id_pegawai, parent_id, api_key);
		
		return status;
	}
	
	@RequestMapping(value="post/add_message", method=RequestMethod.POST)
	@ResponseBody
	String post_add_message(@RequestParam(value="msg") String msg, @RequestParam(value="id_pegawai") String id_pegawai, @RequestParam(value="api_key") String api_key){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		PostDAO postDAO = ctx.getBean("postDAO", PostDAO.class);
		String status = postDAO.add_message(msg, id_pegawai, api_key);
		
		return status;
	}
}