package com.offer.admin.repository;

import com.offer.admin.entity.UserSettings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;




@Repository
public interface UserSettingsRepository extends JpaRepository<UserSettings, String> {

    UserSettings findByEmail(String email);

	UserSettings findByUserId(String userId);
}
