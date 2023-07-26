package com.offer.admin.service;




import com.offer.admin.DTO.AdminDTO;
import com.offer.admin.entity.Admin;
import com.offer.admin.entity.User;
import com.offer.admin.entity.UserSettings;
import com.offer.admin.repository.AdminAddressLinkRepository;
import com.offer.admin.repository.AdminRepository;
import com.offer.admin.repository.UserRepository;
import com.offer.admin.repository.UserSettingsRepository;
import com.offer.admin.viewDTO.AdminViewDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
public class AdminServiceImpl implements AdminService {
	@Autowired
	UserRepository userRepository;
	    @Autowired
		UserSettingsRepository userSettingRepository;
	    
		/*
		 * @Autowired AddressDetailsRepository addressDetailsRepository;
		 */
	    @Autowired
		AdminAddressLinkRepository adminAddressLinkRepository;
	    
	    @Autowired
		AdminRepository adminRepository;
	
	@Override
	public AdminViewDTO saveAdmin(AdminDTO adminDTO) {


    //add in user table
    User users = User.builder()
            .email(adminDTO.getEmailId())
            .firstName(adminDTO.getFirstName())
            .lastName(adminDTO.getLastName())
            .creationDate(new Date())
            .userType("Admin")
            .build();
    User user1 = userRepository.save(users);

    System.out.println("password....." + adminDTO.getPassword());

    //add in user setting table
    UserSettings userSetting = UserSettings.builder()
            .password(new BCryptPasswordEncoder().encode(adminDTO.getPassword()))
            .email(adminDTO.getEmailId())
            .emailValInd(false)
            .userType("Admin")
            .userId(user1.getId())
            .build();
    userSettingRepository.save(userSetting);

    Admin admin = new Admin();
    setPropertyOnInput(adminDTO, admin);
    admin.setUserId(user1.getId());
    Admin dbAdmin = adminRepository.save(admin);

    return AdminViewDTO.from(dbAdmin, false);

	}
	
	 private void setPropertyOnInput(AdminDTO adminDTO, Admin admin) {
	        log.info("adminDTO....." + adminDTO);

	        //admin.setName(adminDTO.getName());
	        admin.setEmailId(adminDTO.getEmailId());
	        // admin.setPassword(adminDTO.getPassword());
	        admin.setUserType(adminDTO.getUserType());
	        admin.setLanguage(adminDTO.getLanguage());
	        admin.setOrgId(adminDTO.getOrgId());
	        admin.setCreateAt(new Date());
	        admin.setActive(true);
	        admin.setDialCode(adminDTO.getDialCode());
	        admin.setMobileNo(adminDTO.getMobileNo());
	        admin.setCreatedBy(adminDTO.getCreatorId());
	        admin.setImageId(adminDTO.getImageId());
	        admin.setFirstName(adminDTO.getFirstName());
	        admin.setMiddleName(adminDTO.getMiddleName());
	        admin.setLastName(adminDTO.getLastName());

	        String middleName2 = " ";
	        String lastName2 = "";

	        if (!StringUtils.isEmpty(adminDTO.getLastName())) {

	            lastName2 = adminDTO.getLastName();
	        }
	        if (adminDTO.getMiddleName() != null && adminDTO.getMiddleName().length() > 0) {


	            middleName2 = adminDTO.getMiddleName();
	            admin.setName(adminDTO.getFirstName() + " " + middleName2 + " " + lastName2);
	        } else {

	            admin.setName(adminDTO.getFirstName() + " " + lastName2);
	        }

	        adminRepository.save(admin);

	    }


	@Override
	public AdminViewDTO doLogin(AdminDTO adminDTO) {

        log.info("dto pwd... " + adminDTO.getPassword());
        // log.info("hash pwd... " + UtilService.hashPassword(userDTO.getPassword()));
        AdminViewDTO adminViewDTO = new AdminViewDTO();
        Admin admin = adminRepository.getAdminByEmailId(adminDTO.getEmailId());
        log.info("db pwd... " + admin.getPassword());
        //HomePage homepage = homePageRepo.findAll().get(0);
        // System.out.println("logo......."+homepage.getLogo());

        if (null != admin) {
            if (admin.getPassword().equals(adminDTO.getPassword())) {
                log.info("pwd match");

                adminViewDTO = AdminViewDTO.from(admin, false);
               // Stationary stationary = stationaryRepo.findAll().get(0);
                //adminViewDTO.setLogoId(stationary.getLogo());
            } else {
                log.info("password not match");
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid credential");
            }
        } else {
            // log.info("password not match");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid user");
        }
        return adminViewDTO;
    
	}
	
}