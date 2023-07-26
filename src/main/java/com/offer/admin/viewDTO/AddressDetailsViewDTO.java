package com.offer.admin.viewDTO;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;


import com.offer.admin.entity.AddressDetails;
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

public class AddressDetailsViewDTO {
	
	   @JsonProperty("addressId")
	    private String addressId;

	    @JsonProperty("address1")
	    private String address1;

	    @JsonProperty("address2")
	    private String address2;

	    @JsonProperty("city")
	    private String city;

	    @JsonProperty("country")
	    private String country;

	    @JsonProperty("state")
	    private String state;

	    @JsonProperty("street")
	    private String street;

	    @JsonProperty("pinCode")
	    private String pinCode;

	    @JsonProperty("location")
	    private String location;

	    @JsonProperty("addressType")
	    private String addressType;

	    @JsonProperty("longitude")
	    private float longitude;

	    @JsonProperty("latitude")
	    private float latitude;

	    @JsonProperty("creationDate")
	    private Date creationDate;

	    @JsonProperty("countryDialCode")
	    private String countryDialCode;
	    
	  /*  public AddressDetailsViewDTO(String addressId, String address1, String address2, String city, String country, String state,String street, String pinCode, String location,String addressType
	    		, float longitude,float latitude,String countryDialCode) {
	        this.addressId = addressId;
	        this.address1 = address1;
	        this.address2 = address2;
	        this.city = city;
	        this.country = country;
	        this.state = state;
	        this.street=street;
	        this.pinCode = pinCode;
	        this.location = location;
	        this.addressType = addressType;
	        this.longitude = longitude;
	        this.latitude = latitude;
	        //this.creationDate=creationDate;
	        this.countryDialCode=countryDialCode;
	       
	    }*/

    public static AddressDetailsViewDTO from(AddressDetails addressDetails) {
        AddressDetailsViewDTO viewDTO;
        if (addressDetails == null) {
            viewDTO = null;
        } else {
            viewDTO = AddressDetailsViewDTO.builder()
                    .addressId(addressDetails.getId())
                    .address1(addressDetails.getAddress1())
                    .address2(addressDetails.getAddress2())
                    .city(addressDetails.getCity())
                    .state(addressDetails.getState())
                    .street(addressDetails.getStreet())
                    .pinCode(addressDetails.getPincode())
                    .location(addressDetails.getLocation())
                    .addressType(addressDetails.getAddressType())
                    .longitude(addressDetails.getLongitude())
                    .latitude(addressDetails.getLatitude())
                    .creationDate(addressDetails.getCreateAt())
                    .countryDialCode(addressDetails.getCountry_dial_code())
                    .country(addressDetails.getCountry())
                    .build();
        }
        return viewDTO;
    }

   
}
