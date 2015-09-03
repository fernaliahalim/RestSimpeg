package api.simpeg.controllers;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import api.simpeg.dao.PegawaiDAO;
import api.simpeg.model.Pegawai;

@RestController
public class PegawaiRestController{
    
    @RequestMapping("/pegawai/find_all")
    @ResponseBody
    List<Pegawai> pegawai_find_all(@RequestParam(value="api_key") String api_key){
    	ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        PegawaiDAO pegawaiDAO = ctx.getBean("pegawaiDAO", PegawaiDAO.class);
    	List<Pegawai> pgwList = pegawaiDAO.find_all(api_key);
    	return pgwList;
    }
    
    @RequestMapping("/pegawai/find_by_nama")
    @ResponseBody
    List<Pegawai> pegawai_find_by_nama(@RequestParam(value="nama") String nama, @RequestParam(value="api_key") String api_key){
    	ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        PegawaiDAO pegawaiDAO = ctx.getBean("pegawaiDAO", PegawaiDAO.class);
    	List<Pegawai> pgwList = pegawaiDAO.find_by_nama(nama, api_key);
    	return pgwList;
    }
    
    @RequestMapping("/pegawai/find_by_nip")
    @ResponseBody
    List<Pegawai> pegawai_find_by_nip(@RequestParam(value="nip") String nip, @RequestParam(value="api_key") String api_key){
    	ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        PegawaiDAO pegawaiDAO = ctx.getBean("pegawaiDAO", PegawaiDAO.class);
    	List<Pegawai> pgwList = pegawaiDAO.find_by_nip(nip, api_key);
    	return pgwList;
    }
    
    @RequestMapping("/pegawai/find_by_unit_kerja")
    @ResponseBody
    List<Pegawai> pegawai_find_by_unit_kerja(@RequestParam(value="unit_kerja") String unit_kerja, @RequestParam(value="api_key") String api_key){
    	ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        PegawaiDAO pegawaiDAO = ctx.getBean("pegawaiDAO", PegawaiDAO.class);
    	List<Pegawai> pgwList = pegawaiDAO.find_by_unit_kerja(unit_kerja, api_key);
    	return pgwList;
    }
}
