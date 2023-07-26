package com.offer.admin.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
@Table(name = "address_details")
@AllArgsConstructor
@NoArgsConstructor

public class AddressDetails extends Auditable {

    @Id
    @GenericGenerator(name = "id", strategy = "com.offer.admin.generator.AddressDetailsGenerator")
    @GeneratedValue(generator = "id")

    @Column(name = "address_id")
    private String id;

    @Column(name = "address1")
    private String address1;

    @Column(name = "address2")
    private String address2;

    @Column(name = "city")
    private String city;

    @Column(name = "country")
    private String country;

    @Column(name = "state")
    private String state;

    @Column(name = "street")
    private String street;

    @Column(name = "pincode")
    private String pincode;

    @Column(name = "location")
    private String location;

    @Column(name = "org_id")
    private String orgId;

    @Column(name = "address_type")
    private String addressType;

    @Column(name = "longitude")
    private float longitude;

    @Column(name = "latitude")
    private float latitude;

    @Column(name = "country_dial_code")
    private String country_dial_code;

    @Column(name = "phone_number")
    private String phoneNumber;

}
