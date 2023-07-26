package com.offer.admin.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import java.util.List;

import javax.persistence.*;
@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")

public class User extends Auditable {
	
	@Id
    @GenericGenerator(name = "id", strategy = "com.offer.admin.generator.UserGenerator")
    @GeneratedValue(generator = "id")
    @Column(name = "user_id")
    private String id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone_no")
    private String phoneNo;

    @Column(name = "dial_code")
    private String dialCode;

    @Column(name = "alternative_no")
    private String alternativeNo;

    @Column(name="distance")
    private float distance;

    @Column(name = "email")
    private String email;

    @Column(name = "user_type")
    private String userType;

    @Column(name = "creation_date")
    private Date creationDate;

    @Column(name = "currency")
    private String currency;

//    @ManyToOne( cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinColumn(name = "customer_id", foreignKey = @ForeignKey(name = "customer_details_user_fk"))
//    private CustomerDetails customerDetails;
//
//    @ManyToOne( cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinColumn(name = "service_id", foreignKey = @ForeignKey(name = "service_details_user_fk"))
//    private ServiceDetails serviceDetails;
//
//    @ManyToOne( cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinColumn(name = "admin_id", foreignKey = @ForeignKey(name = "admin_user_fk"))
//    private Admin admin;
//
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<UserAddressLink> addresses;
}

