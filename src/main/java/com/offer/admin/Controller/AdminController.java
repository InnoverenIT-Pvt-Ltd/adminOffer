package com.offer.admin.Controller;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.offer.admin.DTO.AdminDTO;
import com.offer.admin.config.TokenProvider;
import com.offer.admin.service.AdminService;
import com.offer.admin.service.UserService;
import com.offer.admin.viewDTO.AdminViewDTO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;







@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping("admin")
public class AdminController {
	@Autowired
	AdminService adminService;
	
	 @Autowired
	 TokenProvider jwtTokenUtil;

		
		  @Autowired
		  UserService userService;
		 
	    
	    
	    
	    @PostMapping
	    @CacheEvict(value = "admin", allEntries = true)
	    public ResponseEntity<?> saveAdmin(@RequestBody AdminDTO adminDTO, HttpServletRequest request) {
	        Map map = new HashMap();
	      
	            if (!StringUtils.isEmpty(adminDTO.getEmailId())) {
	                boolean b = userService.userExistsByEmail(adminDTO.getEmailId());
	                if (b == true) {
	                    map.put("userInd", true);
	                    map.put("message", "User with same mail already exists.!!!!");
	                    return new ResponseEntity<>(map, HttpStatus.OK);
	                } else {

	                   // String creatorId = jwtTokenUtil.getUserIdFromToken(authToken);
	                   // adminDTO.setCreatorId(creatorId);

	                    AdminViewDTO adminViewDTO = adminService.saveAdmin(adminDTO);
	                    return new ResponseEntity<>(adminViewDTO, HttpStatus.OK);
	                }
	            }
	        
	        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	    }
	    
	    @PostMapping(value = "/login")
	    public ResponseEntity<AdminViewDTO> doRegister(@RequestBody AdminDTO adminDTO, HttpServletRequest request) {
	        return ResponseEntity.ok(adminService.doLogin(adminDTO));
	    }

}