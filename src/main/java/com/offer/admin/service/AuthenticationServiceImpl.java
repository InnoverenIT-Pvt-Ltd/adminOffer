package com.offer.admin.service;


import com.offer.admin.entity.UserSettings;
import com.offer.admin.repository.AdminRepository;
import com.offer.admin.repository.UserSettingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



import java.util.Arrays;
import java.util.List;

@Service(value = "userService")
public class AuthenticationServiceImpl implements AuthenticationService, UserDetailsService {
 
    @Autowired
    AdminRepository adminRepository;
   
    @Autowired
    UserSettingsRepository userSettingsRepository;





    @Override
    public List<SimpleGrantedAuthority> getAuthority(String emailId) {

        UserSettings userSeting = userSettingsRepository.findByEmail(emailId);

        return Arrays.asList(new SimpleGrantedAuthority("ROLE_" + userSeting.getUserType()));
    }



    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {



        UserSettings user = userSettingsRepository.findByEmail(email);



        if (user == null) {

            throw new UsernameNotFoundException("Invalid username or password.");

        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                getAuthority(email));

    }
}
