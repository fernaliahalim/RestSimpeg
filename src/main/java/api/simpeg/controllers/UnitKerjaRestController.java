package api.simpeg.controllers;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import api.simpeg.dao.UnitKerjaDAO;
import api.simpeg.model.Unit_kerja;
import api.simpeg.model.Unit_kerja_mobile_request;


@RestController
public class UnitKerjaRestController{
	
	@RequestMapping(value="/unit_kerja/get_all", method=RequestMethod.POST)
	@ResponseBody
	List<Unit_kerja> uk_get_all(@RequestParam(value="api_key") String api_key){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        UnitKerjaDAO ukDAO = ctx.getBean("unitKerjaDAO", UnitKerjaDAO.class);
    	List<Unit_kerja> ukList = ukDAO.get_all(api_key);
    	return ukList;
	}
	
	@RequestMapping(value="/unit_kerja/get_by_unit_kerja", method=RequestMethod.POST)
	@ResponseBody
	Unit_kerja uk_get_by_unit_kerja(@RequestParam(value="unit_kerja") String unit_kerja, @RequestParam(value="api_key") String api_key){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        UnitKerjaDAO ukDAO = ctx.getBean("unitKerjaDAO", UnitKerjaDAO.class);
        Unit_kerja uk = ukDAO.get_by_unit_kerja(unit_kerja, api_key);
        return uk;
	}
	
	@RequestMapping(value="/unit_kerja/get_id_nama", method=RequestMethod.POST)
	@ResponseBody
	List<Unit_kerja_mobile_request> uk_get_id_nama(@RequestParam(value="api_key") String api_key){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        UnitKerjaDAO ukDAO = ctx.getBean("unitKerjaDAO", UnitKerjaDAO.class);
    	List<Unit_kerja_mobile_request> ukList = ukDAO.get_id_nama(api_key);
    	return ukList;
	}
	
	@RequestMapping(value="/unit_kerja/get_location_uk", method=RequestMethod.POST)
	@ResponseBody
	String uk_get_location_uk(@RequestParam(value="id_pegawai") String id_pegawai, @RequestParam(value="api_key") String api_key){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        UnitKerjaDAO ukDAO = ctx.getBean("unitKerjaDAO", UnitKerjaDAO.class);
    	String uk = ukDAO.get_location_uk(id_pegawai, api_key);
    	return uk;
	}
}