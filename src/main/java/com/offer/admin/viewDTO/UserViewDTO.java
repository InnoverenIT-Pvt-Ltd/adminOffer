package com.offer.admin.viewDTO;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


import com.offer.admin.entity.AddressDetails;
import com.offer.admin.entity.User;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Slf4j
@Builder
@JsonPropertyOrder({"userId", "firstName", "middleName", "lastName", "dialCode1", "phoneNo", "dialCode", "alternateNo", "dialCode1", "designation", "work", "functionName", "level", "functionName", "merchantDetails"})

public class UserViewDTO {
	
	private String userId;

    private String firstName;

    private String middleName;

    private String lastName;

    private String phoneNo;

    private String dialCode;

    private String alternateNo;

    private String dialCode1;

    private float distance;

    private String email;

    private String designation;

    private String work;

    private String functionName;

    private String level;

    private String userType;

    private String confirmEmail;

    private String name;

    private boolean approveInd;

    private String currency;

    private String currencyName;

    private boolean openInd;
    
    private String orgId;
    
    private String adminId;
    
    private String serviceId;
    
    private String contactId;

    private String customerId;

    private String requirementId;
    
    private String imageId;
    
    private String adminImageId;
    
    private String customerImageId;
    
    private String logoId;
    
    private String fullName;
    
    private String language;

    private List<AddressDetailsViewDTO> addresses = new ArrayList<>();

    public UserViewDTO(String userId,  String firstName, String middleName, String lastName,
                       String phoneNo, String dialCode, String alternateNo,String email,String userType,float distance){
        super();
        this.userId = userId;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.phoneNo = phoneNo;
        this.dialCode = dialCode;
        this.alternateNo = alternateNo;
        this.email = email;
        this.userType = userType;
        this.distance=distance;
    }

    public static UserViewDTO from(User user, boolean setExtendedProperties) {
        UserViewDTO userViewDTO;
        if (user == null) {
            userViewDTO = null;
        } else {
            log.info("user ............" + user.getId());
            userViewDTO = new UserViewDTO(user.getId(), user.getFirstName(),
                    user.getMiddleName(), user.getLastName(), user.getPhoneNo(), user.getDialCode(),
                    user.getAlternativeNo(), user.getEmail(),  user.getUserType(),user.getDistance());

            List<AddressDetailsViewDTO> addressList =
                    user.getAddresses().stream().map(userAddressLink -> {
                        AddressDetails address = userAddressLink.getAddress();
                        return AddressDetailsViewDTO.from(address);
                    }).collect(Collectors.toList());
            log.info("addressList... " + addressList);
            userViewDTO.setAddresses(addressList);
        }
        return userViewDTO;
    }
}


