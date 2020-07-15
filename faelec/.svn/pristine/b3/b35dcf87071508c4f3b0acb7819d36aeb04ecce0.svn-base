package com.tlm.faelec.service.maestros;

import java.util.List;
import java.util.Map;

import com.tlm.faelecEntities.model.entities.Mcampo00;
//import com.tlm.faelec.model.entities.Mconca00;
import com.tlm.faelecEntities.model.entities.Mdecai00;
import com.tlm.faelecEntities.model.entities.Mmenus00;
import com.tlm.faelecEntities.model.entities.Mopcme00;
import com.tlm.faelecEntities.model.entities.Msegca00;
import com.tlm.faelecEntities.model.entities.Mtabla00;
import com.tlm.faelecEntities.model.entities.Mtabla00PK;
import com.tlm.faelecEntities.model.entities.Musuar00;
import com.tlm.faelecEntities.vo.Musuco00VO;


/**
 * 
 * @author avelasquez
 *
 */
public interface IMGeneralService {

		//Mmenus00
		public List<Mmenus00> listMmenus00(String aplmen,String gruopg);
		
		//Mopcme00
		public List<Mopcme00> listMopcme00ByCriteria(Mopcme00 mopcme00, String gruopg);
		
		//Mdecai00
		public List<Mdecai00> listMdecai00ByCriteria(Mdecai00 mdecai00);
		public Map<String, Object> hmMdecai00ByCriteria(Mdecai00 mdecai00);
		
		//Musucu00		
		public List<Musuco00VO> listMusuco00ByCriteria(Musuco00VO musuco00VO);
		
		//Musuar00
		public Musuar00 retrieveMusuar00(Musuar00 musuar00);
		
		//Msegca00
		public Map<String, String> permisosMsegca00ByCriteria(Msegca00 msegca00);
		
		//Mtabla00
		public Mtabla00 getMtabla00(Mtabla00PK idMtabla00);	
		
		//Mcampo00
		public Map<String,Mcampo00> listMcampo00(Mcampo00 mcampo00); 
		
		//Mgetma00
		//public Mgetma00 findMgetma00ById(String idtrma);
		
		//Mdecai00
//		public List<Msegca00> listMsegca00ByCriteria(Msegca00 msegca00);
//		public Map<String, String> permisosMsegca00ByCriteria(Msegca00 msegca00);
}
