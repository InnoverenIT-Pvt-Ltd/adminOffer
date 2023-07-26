package com.offer.admin.repository;

import com.offer.admin.entity.Admin;
import com.offer.admin.entity.AdminAddressLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;




@Repository
public interface AdminAddressLinkRepository extends JpaRepository<AdminAddressLink, String>{

	AdminAddressLink findByAdmin(Admin admin);

	
}
