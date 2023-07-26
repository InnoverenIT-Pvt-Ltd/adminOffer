package com.offer.admin.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "admin_address_link")
public class AdminAddressLink {

	 @Id
	    @GenericGenerator(name = "id", strategy = "com.offer.admin.generator.AdminAddressLinkGenerator")
	    @GeneratedValue(generator = "id")

	    @Column(name = "admin_address_id")
	    private String id;

	    @Column(name = "is_primary",nullable = false)
	    private Boolean isPrimary;

		
		  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
		  
		  @JoinColumn(name = "address_id", foreignKey = @ForeignKey(name =
		  "address_merchant_fk")) private AddressDetails address;
		 

	    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	    @JoinColumn(name = "admin_id", foreignKey = @ForeignKey(name = "admin_address_fk"))
	    private Admin admin;
	
	
	
	
}
