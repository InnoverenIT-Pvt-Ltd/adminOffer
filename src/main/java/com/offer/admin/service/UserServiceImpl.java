
package com.offer.admin.service;

import java.util.List;

import com.offer.admin.entity.User;
import com.offer.admin.repository.AdminRepository;
import com.offer.admin.repository.UserRepository;
import com.offer.admin.repository.UserSettingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;




import lombok.extern.slf4j.Slf4j;



@Service

@Slf4j

public class UserServiceImpl implements UserService {

    @Autowired
	UserRepository userRepository;

    @Autowired
	AdminRepository adminRepository;

   

    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    
    @Autowired
	UserSettingsRepository userSettingsRepository;


	@Override
	public boolean userExistsByEmail(String emailId) {
		 List<User> user = userRepository.findByEmail(emailId);
			if (user != null && !user.isEmpty()) {
				return true;
			}
			return false;
	}


   
}
  
  
 