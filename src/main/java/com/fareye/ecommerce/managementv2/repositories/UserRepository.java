package com.fareye.ecommerce.managementv2.repositories;

import com.fareye.ecommerce.managementv2.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByUsername(String username);

    List<User> findByRole(String role);
}
