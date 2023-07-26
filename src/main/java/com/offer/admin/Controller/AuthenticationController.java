
package com.offer.admin.Controller;

import javax.servlet.http.HttpServletRequest;

import com.offer.admin.DTO.LoginDTO;
import com.offer.admin.DTO.TokenRes;
import com.offer.admin.config.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;





@RestController

@CrossOrigin(maxAge = 3600)

@RequestMapping("/token")
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private TokenProvider jwtTokenUtil;

	@RequestMapping(value = "/generate-token", method = RequestMethod.POST)
	public ResponseEntity<?> register(@RequestBody LoginDTO loginUser, HttpServletRequest request) {

		System.out.println("inside generate token...");

		System.out.println("user name ... " + loginUser.getUsername());
		System.out.println("password ... " + loginUser.getPassword());

		try {

			final Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword()));

			SecurityContextHolder.getContext().setAuthentication(authentication);

			final TokenRes token = jwtTokenUtil.generateToken(authentication);
			//final String token = jwtTokenUtil.generateToken(authentication);
			if (null != token ) {

				return ResponseEntity.ok(token);
			} else {
				return new ResponseEntity<String>("You have entered an invalid username or password",
						HttpStatus.FORBIDDEN);

			}

		} catch (Exception e) {
			e.printStackTrace();

			return new ResponseEntity<String>("You have entered an invalid username or password ",
					HttpStatus.FORBIDDEN);
		}

	}

}
