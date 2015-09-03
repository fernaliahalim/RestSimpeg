package api.simpeg.controllers;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import api.simpeg.dao.AbsensiMobileDAO;
import api.simpeg.model.Absensi_mobile;

@RestController
public class AbsensiMobileRestController{
	
	@RequestMapping(value="/absensi_mobile/hadir", method=RequestMethod.POST)
	@ResponseBody
	String absensi_mobile_hadir(@RequestParam(value="id_pegawai") String id_pegawai, @RequestParam(value="nip") String nip, @RequestParam(value="password") String password, @RequestParam(value="latitude") String latitude, @RequestParam(value="longitude") String longitude, @RequestParam(value="imei") String imei, @RequestParam(value="api_key") String api_key){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        AbsensiMobileDAO absmDAO = ctx.getBean("absensiMobileDAO", AbsensiMobileDAO.class);
       // Double long_latrequired = Double.parseDouble(long_lat);
        String stts = absmDAO.hadir(id_pegawai, nip, password, latitude, longitude, imei, api_key);
        
        return stts;
	}
	
	@RequestMapping(value="/absensi_mobile/tidak_hadir", method=RequestMethod.POST)
	@ResponseBody
	String absensi_mobile_tidak_hadir(@RequestParam(value="id_pegawai") String id_pegawai, @RequestParam(value="status") String status, @RequestParam(value="api_key") String api_key){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        AbsensiMobileDAO absmDAO = ctx.getBean("absensiMobileDAO", AbsensiMobileDAO.class);
        String stts = absmDAO.tidak_hadir(id_pegawai, status, api_key);
        
        return stts;
	}
	
	@RequestMapping(value="absensi_mobile/get_attendance", method=RequestMethod.POST)
	@ResponseBody
	List<Absensi_mobile> absesnsi_mobile_get_attendance(@RequestParam(value="id_pegawai") String id_pegawai, @RequestParam(value="api_key") String api_key){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        AbsensiMobileDAO absmDAO = ctx.getBean("absensiMobileDAO", AbsensiMobileDAO.class);
        List<Absensi_mobile> absList = absmDAO.get_attendance(id_pegawai, api_key);
        
        return absList;
	}
}