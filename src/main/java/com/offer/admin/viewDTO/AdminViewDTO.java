
  package com.offer.admin.viewDTO;
  
  
  import com.offer.admin.entity.Admin;
  import com.offer.admin.service.UtilService;
  import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;


  
  @Getter
  
  @Setter
  
  @NoArgsConstructor
  
  @AllArgsConstructor
  
  @Builder //@JsonInclude(JsonInclude.Include.NON_NULL) 
  public class AdminViewDTO {
  
  private String adminId;
  
  private String name;
  
  private String emailId;
  
  private String password;
  
  private String userType;
  
  private String language;
  
  private String orgId;
  
  private String logoId;
  
  private String mobileNo;
  
  private String dialCode;
  
  private String creationDate;
  
  private String creatorId;
  
  private String imageId;
  
  private boolean activeInd;
  
  private String firstName;
  
  private String middleName;
  
  private String lastName;
  
  private String fullName; private String adminImageId;
  
  public static AdminViewDTO from(Admin admin, boolean setExtendedProperties) {
  AdminViewDTO adminViewDTO;
  
  if (admin == null) { adminViewDTO = null; } else { String creationDate =
  null; if (null != admin.getCreateAt()) { creationDate =
  UtilService.getISOFromDate(admin.getCreateAt()); } adminViewDTO =
  AdminViewDTO.builder() .adminId(admin.getId()) .name(admin.getName())
  .userType(admin.getUserType()) .emailId(admin.getEmailId())
  .mobileNo(admin.getMobileNo()) .dialCode(admin.getDialCode())
  .language(admin.getLanguage()) .orgId(admin.getOrgId()) .creationDate(
  creationDate) .activeInd(admin.isActive()) .adminImageId(admin.getImageId())
  .creatorId(admin.getCreatedBy()) .imageId(admin.getImageId())
  .firstName(admin.getFirstName()) .middleName(admin.getMiddleName())
  .fullName(getAdminFullName(admin.getFirstName(),admin.getLastName(),admin.
  getMiddleName())) .lastName(admin.getLastName()) .build();
  
 /* List<AddressDetailsViewDTO> addressList =admin.getAdminAddressLink().stream().map(adminAddressLink -> { AddressDetails
  address = adminAddressLink.getAddress();
  return AddressDetailsViewDTO.from(address); }).collect(Collectors.toList());*/
  
 // adminViewDTO.setAddresses(addressList); 
  if (setExtendedProperties) {
 // List<UserViewDTO> userViewDTOS = admin.getUsers().stream() .map(user ->
//  UserViewDTO.from(user, false)) .collect(Collectors.toList());
  //adminViewDTO.setUsers(userViewDTOS); 
  }
  
  }
  
  return adminViewDTO;
  
  }
  
  private static String getAdminFullName(String firstName, String lastName,
  String middleName) { String middleName2 = " "; String lastName2 = ""; if
  (!StringUtils.isEmpty(lastName)) { lastName2 = lastName; } if (middleName !=
  null && middleName.length() > 0) { middleName2 = middleName; return firstName
  + " " + middleName2 + " " + lastName2; } else { return firstName+ " " +
  lastName2; } } }
 