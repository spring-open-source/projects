package com.Maulik.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.Maulik.dto.Register;

@Transactional
public interface RegisterRepository extends CrudRepository<Register, Long>
{
	@Query("SELECT t FROM Register t where t.email = :email AND t.password = :password")
	public Register login_user(@Param("email") String email,@Param("password") String password);
	
}
