
  package com.offer.admin.service;


  import com.offer.admin.DTO.AdminDTO;
  import com.offer.admin.viewDTO.AdminViewDTO;

  public interface AdminService {
  
  AdminViewDTO saveAdmin(AdminDTO adminDTO);
  
  AdminViewDTO doLogin(AdminDTO adminDTO);
  
  // AdminViewDTO getAdmin(String id);
  
  }