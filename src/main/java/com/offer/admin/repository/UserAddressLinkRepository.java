package com.offer.admin.repository;

import com.offer.admin.entity.UserAddressLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;





@Repository
public interface UserAddressLinkRepository extends JpaRepository<UserAddressLink, String> {

	UserAddressLink findByUserId(String id);

}
