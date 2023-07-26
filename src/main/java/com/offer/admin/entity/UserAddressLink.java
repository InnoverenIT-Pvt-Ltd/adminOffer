package com.offer.admin.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
@Builder
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_address_link")

public class UserAddressLink extends Auditable{
	
	@Id
    @GenericGenerator(name = "id", strategy = "com.offer.admin.generator.UserAddressLinkGenerator")
    @GeneratedValue(generator = "id")
    @Column(name = "user_address_id")
    private String id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", foreignKey = @ForeignKey(name = "address_user_fk"))
    private AddressDetails address;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "user_address_fk"))
    private User user;
}
