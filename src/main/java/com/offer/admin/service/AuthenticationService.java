package com.offer.admin.service;


import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

public interface AuthenticationService {





    List<SimpleGrantedAuthority> getAuthority(String emailId);
}
