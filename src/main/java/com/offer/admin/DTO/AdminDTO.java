package com.offer.admin.DTO;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AdminDTO {
	
	 private String firstName;
	 
	 private String middleName;

	 private String lastName;
	    
	 private String name;
	 
	 private String emailId;
	    
	 private String password;
	 
	 private String userType;
	 
	 private String language;
	 
	 private String orgId;
	 
	 private String creationDate;
	 
	 private String mobileNo;
	 
	 private String dialCode;
	 
	 private String creatorId;
	 
	 private String imageId;
	 
	 private boolean activeInd;
	 
	 @NotNull(message = "Admin Address can't be null")
	 private List<AddressDetailsDTO> addressDetails;
	 
	 
}
	 
	