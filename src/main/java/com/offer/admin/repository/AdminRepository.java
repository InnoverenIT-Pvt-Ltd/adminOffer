package com.offer.admin.repository;

import java.util.List;

import com.offer.admin.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;





@Repository
public interface AdminRepository extends JpaRepository<Admin, String>{

	Admin findByName(String name);

	Admin getAdminByEmailId(String emailId);

	Admin findAdminByEmailId(String emailId);

	

	Admin findAdminDetailsById(String adminId);

	Admin findByEmailId(String email);

	List<Admin> findByActive(boolean b);

	Admin findByUserId(String userId);
}
