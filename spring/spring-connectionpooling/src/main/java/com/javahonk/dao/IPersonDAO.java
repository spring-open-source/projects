package com.javahonk.dao;

import java.util.List;
import java.util.Map;

/**
 * @author Java Honk
 *
 */
public interface IPersonDAO {
	
	List<Map<String, Object>> selectAllPerson();	

}
