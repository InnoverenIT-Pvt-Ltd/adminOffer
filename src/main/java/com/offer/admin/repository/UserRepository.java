
  package com.offer.admin.repository;
  
  import java.util.List;
import java.util.Optional;

  import com.offer.admin.entity.User;
  import org.springframework.data.jpa.repository.JpaRepository;
import
 // org.springframework.security.core.userdetails.UserDetails; import
  org.springframework.stereotype.Repository;




  
  @Repository public interface UserRepository extends JpaRepository<User, String> {
  
  User findUserByEmail(String email);
  
  User getUserByEmail(String email);
  
  Optional<User> findById(String id);

List<User> findByEmail(String emailId);
  
  }
 