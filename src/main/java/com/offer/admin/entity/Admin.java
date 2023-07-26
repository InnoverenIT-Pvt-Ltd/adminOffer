package com.offer.admin.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "admin")
public class Admin extends Auditable {

	@Id
    @GenericGenerator(name = "id", strategy = "com.offer.admin.generator.AdminGenerator")
    @GeneratedValue(generator = "id")

    @Column(name = "admin_id")
    private String id;

    @Column(name = "name")
    private String name;
    
    @Column(name = "email_id")
    private String emailId;
    
    @Column(name = "password")
	private String password;
	
    @Column(name = "user_type")
    private String userType;
    
    @Column(name = "language")
    private String language;
    
    @Column(name = "org_id")
    private String orgId;
    
    @Column(name="mobile_no")
    private String mobileNo;
    
    @Column(name="dial_code")
    private String dialCode;
    
    @Column(name = "user_id")
    private String userId;

    @Column(name="image_id")
    private String imageId;
    
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name")
    private String lastName;

    
   /* @OneToOne(mappedBy = "admin", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private CostPerLead costPerLead;
    
    @OneToOne(mappedBy = "admin", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private EmailConfig emailConfig;*/
     
	
	  @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL, fetch =
	  FetchType.LAZY) private List<AdminAddressLink> adminAddressLink;
	 
    
}
