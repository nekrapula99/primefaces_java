package com.tlm.faelec.service.maestros.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlm.faelec.model.dao.IMcampo00Dao;
import com.tlm.faelec.model.dao.IMdecai00Dao;
import com.tlm.faelec.model.dao.IMmenus00Dao;
import com.tlm.faelec.model.dao.IMopcme00Dao;
import com.tlm.faelec.model.dao.IMsegca00Dao;
import com.tlm.faelec.model.dao.IMtabla00Dao;
import com.tlm.faelec.model.dao.IMusuar00Dao;
import com.tlm.faelec.model.dao.IMusuco00Dao;
import com.tlm.faelec.service.maestros.IMGeneralService;
import com.tlm.faelecEntities.model.entities.Mcampo00;
import com.tlm.faelecEntities.model.entities.Mdecai00;
import com.tlm.faelecEntities.model.entities.Mmenus00;
import com.tlm.faelecEntities.model.entities.Mopcme00;
import com.tlm.faelecEntities.model.entities.Msegca00;
import com.tlm.faelecEntities.model.entities.Mtabla00;
import com.tlm.faelecEntities.model.entities.Mtabla00PK;
import com.tlm.faelecEntities.model.entities.Musuar00;
import com.tlm.faelecEntities.vo.Musuco00VO;


@Transactional
@Service("mgeneralService")
public class MGeneralServiceImpl implements IMGeneralService
{

	@Autowired		
	private IMmenus00Dao mmenusDao;
	@Autowired
	private IMopcme00Dao mopcmeDao;
	@Autowired
	private IMdecai00Dao mdecaiDao;
	@Autowired
	private IMusuco00Dao musucoDao;
	@Autowired
	private IMusuar00Dao musuarDao;
	@Autowired
	private IMsegca00Dao msegcaDao;
	@Autowired
	private IMtabla00Dao mtablaDao;
    @Autowired
    private IMcampo00Dao mcampoDao;
    //@Autowired
    //private IMgetma00Dao mgetmaDao;
	
	

	//Mmenus00
	@Override
	public List<Mmenus00> listMmenus00(String aplmen,String gruopg) {
		return mmenusDao.listMmenus00(aplmen,gruopg);
	}

    //Mopcme00
	@Override
	public List<Mopcme00> listMopcme00ByCriteria(Mopcme00 mopcme00,String gruopg) {
	  return mopcmeDao.listMopcme00ByCriteria(mopcme00, gruopg);
	}


	//Mdecai00
	@Override
	public List<Mdecai00> listMdecai00ByCriteria(Mdecai00 mdecai00) {	
		return mdecaiDao.listMdecai00ByCriteria(mdecai00);
	}


	@Override
	public Map<String, Object> hmMdecai00ByCriteria(Mdecai00 mdecai00) {
		return mdecaiDao.hmMdecai00ByCriteria(mdecai00);
	}

	
    //Musuco00VO
	@Override
	public List<Musuco00VO> listMusuco00ByCriteria(Musuco00VO musuco00vo) {
		return musucoDao.listMusuco00ByCriteria(musuco00vo);
	}

    
	//Musuar00
	@Override
	public Musuar00 retrieveMusuar00(Musuar00 musuar00) {		
		return musuarDao.retrieveMusuar00(musuar00);
	}

	@Override
	public Map<String, String> permisosMsegca00ByCriteria(Msegca00 msegca00) {	
		return msegcaDao.getPermiByCriteria(msegca00);
	}

	@Override
	public Mtabla00 getMtabla00(Mtabla00PK idMtabla00) {
		return mtablaDao.getMtabla00(idMtabla00);
	}
	
	@Override
	public Map<String,Mcampo00> listMcampo00(Mcampo00 mcampo00)
	{
		return mcampoDao.listMcampo00(mcampo00);
	}

	/*@Override
	public Mgetma00 findMgetma00ById(String idtrma) {
		return mgetmaDao.findMgetma00ById(idtrma);
	}
	*/


  
}
