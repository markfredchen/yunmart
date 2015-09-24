package com.handchina.yunmart.core.persistence;

import com.handchina.yunmart.core.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

/**
 * Created by markfredchen on 9/13/15.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findByMobile(String mobile);

    User findByUserOID(UUID userOID);
}
