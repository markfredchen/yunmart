package com.handchina.yunmart.core.persistence.security;

import com.handchina.yunmart.core.domain.secuirty.ForgetPasswordToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Created by markfredchen on 9/15/15.
 */
public interface ForgetPasswordTokenRepository extends JpaRepository<ForgetPasswordToken, UUID> {
}
