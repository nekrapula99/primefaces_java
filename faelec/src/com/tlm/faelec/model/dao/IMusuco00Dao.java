package com.tlm.faelec.model.dao;

import java.util.List;

import com.tlm.faelecEntities.model.entities.Musuco00;
import com.tlm.faelecEntities.vo.Musuco00VO;



public interface IMusuco00Dao extends GenericDAO<Musuco00> {

	public List<Musuco00VO> listMusuco00ByCriteria(Musuco00VO musuco00VO);
}
