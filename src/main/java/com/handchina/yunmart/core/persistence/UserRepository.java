package com.handchina.yunmart.core.persistence;

import com.handchina.yunmart.core.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by markfredchen on 9/13/15.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
