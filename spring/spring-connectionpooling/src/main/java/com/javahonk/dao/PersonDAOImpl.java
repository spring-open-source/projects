package com.javahonk.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author Java Honk
 *
 */
public class PersonDAOImpl implements IPersonDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Map<String, Object>> selectAllPerson() {
		String sql = "SELECT * FROM person";
		List<Map<String, Object>> listOfPerson = jdbcTemplate.queryForList(sql);
		return listOfPerson;
	}


}
