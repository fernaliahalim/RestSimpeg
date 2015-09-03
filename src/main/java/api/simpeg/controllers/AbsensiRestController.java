package api.simpeg.controllers;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import api.simpeg.dao.AbsensiDAO;
import api.simpeg.model.Absensi;

@RestController
public class AbsensiRestController{
	
	@RequestMapping("/absensi/get_hadir")
    @ResponseBody
    List<Absensi> absensi_get_hadir(@RequestParam(value="unit_kerja") String unit_kerja, @RequestParam(value="tgl") String tgl, @RequestParam(value="api_key") String api_key){
    	ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        AbsensiDAO absensiDAO = ctx.getBean("absensiDAO", AbsensiDAO.class);
    	List<Absensi> absList = absensiDAO.get_hadir(unit_kerja,tgl,api_key);
    	return absList;
    }
	
	@RequestMapping("/absensi/get_sakit")
    @ResponseBody
    List<Absensi> absensi_get_sakit(@RequestParam(value="unit_kerja") String unit_kerja, @RequestParam(value="tgl") String tgl, @RequestParam(value="api_key") String api_key){
    	ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        AbsensiDAO absensiDAO = ctx.getBean("absensiDAO", AbsensiDAO.class);
    	List<Absensi> absList = absensiDAO.get_sakit(unit_kerja,tgl,api_key);
    	return absList;
    }
	
	@RequestMapping("/absensi/get_izin")
    @ResponseBody
    List<Absensi> absensi_get_ijin(@RequestParam(value="unit_kerja") String unit_kerja, @RequestParam(value="tgl") String tgl, @RequestParam(value="api_key") String api_key){
    	ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        AbsensiDAO absensiDAO = ctx.getBean("absensiDAO", AbsensiDAO.class);
    	List<Absensi> absList = absensiDAO.get_ijin(unit_kerja,tgl,api_key);
    	return absList;
    }
	
	@RequestMapping("/absensi/get_alpha")
    @ResponseBody
    List<Absensi> absensi_get_alpha(@RequestParam(value="unit_kerja") String unit_kerja, @RequestParam(value="tgl") String tgl, @RequestParam(value="api_key") String api_key){
    	ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        AbsensiDAO absensiDAO = ctx.getBean("absensiDAO", AbsensiDAO.class);
    	List<Absensi> absList = absensiDAO.get_alpha(unit_kerja,tgl,api_key);
    	return absList;
    }
}