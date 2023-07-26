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
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_settings")
public class UserSettings {
	
	 @Id
	    @GenericGenerator(name = "id", strategy = "com.offer.admin.generator.UserSettingsGenerator")
	    @GeneratedValue(generator = "id")

	    @Column(name = "user_settings_id")
	    private String id;

	    @Column(name = "password")
	    private String password;

	    @Column(name = "userId")
	    private String userId;

	@Column(name = "user_type")
	private String userType;

	    @Column(name = "email")
	    private String email;

	    @Column(name = "user_active_ind")
	    private boolean userActiveInd;

	    @Column(name = "email_val_ind")
	    private boolean emailValInd;

	    @Column(name = "password_active_ind")
	    private boolean passwordActiveInd;
	    
}
