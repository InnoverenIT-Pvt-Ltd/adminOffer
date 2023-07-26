package com.offer.admin.DTO;

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
public class AddressDetailsDTO {

    private String addressId;

    private String address1;

    private String address2;

    private String city;

    private String state;

    private String street;

    private String pinCode;

    private String country;

    private String location;

    private String addressType;

    private float longitude;

    private float latitude;

    private String emailId;

    private String phoneNo;

    private String mobileNo;

    private java.util.Date date;

    private String firstName;

    private String lastName;

    private String countryDialCode;

}
