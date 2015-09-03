package api.simpeg.controllers;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import api.simpeg.dao.StatistikDAO;
import api.simpeg.model.Statistik;

@RestController
public class StatistikRestController{
	
	@RequestMapping(value="statistic/getStatisticBidangPendidikan", method=RequestMethod.POST)
	@ResponseBody
	List<Statistik> statistic_getStatisticBidangPendidikan(@RequestParam(value="api_key") String api_key){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		StatistikDAO statDAO = ctx.getBean("statistikDAO", StatistikDAO.class);
		List<Statistik> stat = statDAO.getStatisticBidangPendidikan(api_key);
		
		return stat;
	}
	
	@RequestMapping(value="statistic/getStatisticFungsional", method=RequestMethod.POST)
	@ResponseBody
	List<Statistik> statistic_getStatisticFungsional(@RequestParam(value="api_key") String api_key){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		StatistikDAO statDAO = ctx.getBean("statistikDAO", StatistikDAO.class);
		List<Statistik> stat = statDAO.getStatisticFungsional(api_key);
		
		return stat;
	}
	
	
	@RequestMapping(value="statistic/getStatisticGolongan", method=RequestMethod.POST)
	@ResponseBody
	List<Statistik> statistic_getStatisticGolongan(@RequestParam(value="api_key") String api_key){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		StatistikDAO statDAO = ctx.getBean("statistikDAO", StatistikDAO.class);
		List<Statistik> stat = statDAO.getStatisticGolongan(api_key);
		
		return stat;
	}
	
	@RequestMapping(value="statistic/getStatisticJabatan", method=RequestMethod.POST)
	@ResponseBody
	List<Statistik> statistic_getStatisticJabatan(@RequestParam(value="api_key") String api_key){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		StatistikDAO statDAO = ctx.getBean("statistikDAO", StatistikDAO.class);
		List<Statistik> stat = statDAO.getStatisticJabatan(api_key);
		
		return stat;
	}
	
	@RequestMapping(value="statistic/getStatisticJenisKelamin", method=RequestMethod.POST)
	@ResponseBody
	List<Statistik> statistic_getStatisticJenisKelamin(@RequestParam(value="api_key") String api_key){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		StatistikDAO statDAO = ctx.getBean("statistikDAO", StatistikDAO.class);
		List<Statistik> stat = statDAO.getStatisticJenisKelamin(api_key);
		
		return stat;
	}
	
	@RequestMapping(value="statistic/getStatisticLulusanPt", method=RequestMethod.POST)
	@ResponseBody
	List<Statistik> statistic_getStatisticLulusanPt(@RequestParam(value="api_key") String api_key){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		StatistikDAO statDAO = ctx.getBean("statistikDAO", StatistikDAO.class);
		List<Statistik> stat = statDAO.getStatisticLulusanPt(api_key);
		
		return stat;
	}
	
	@RequestMapping(value="statistic/getStatisticPendidikan", method=RequestMethod.POST)
	@ResponseBody
	List<Statistik> statistic_getStatisticPendidikan(@RequestParam(value="api_key") String api_key){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		StatistikDAO statDAO = ctx.getBean("statistikDAO", StatistikDAO.class);
		List<Statistik> stat = statDAO.getStatisticPendidikan(api_key);
		
		return stat;
	}
	
	@RequestMapping(value="statistic/getStatisticStruktural", method=RequestMethod.POST)
	@ResponseBody
	List<Statistik> statistic_getStatisticStruktural(@RequestParam(value="api_key") String api_key){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		StatistikDAO statDAO = ctx.getBean("statistikDAO", StatistikDAO.class);
		List<Statistik> stat = statDAO.getStatisticStruktural(api_key);
		
		return stat;
	}
	
	@RequestMapping(value="statistic/getStatisticUmur", method=RequestMethod.POST)
	@ResponseBody
	List<Statistik> statistic_getStatisticUmur(@RequestParam(value="api_key") String api_key){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		StatistikDAO statDAO = ctx.getBean("statistikDAO", StatistikDAO.class);
		List<Statistik> stat = statDAO.getStatisticUmur(api_key);
		
		return stat;
	}
}