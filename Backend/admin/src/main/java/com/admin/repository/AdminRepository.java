package com.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.admin.entity.AdminDetails;

@Repository
public interface AdminRepository extends JpaRepository<AdminDetails, Long>{

	
	@Query(value = "select * from admindb.admin_details where user_name = ?1",nativeQuery = true)
	AdminDetails findByUsername(String username);

}
