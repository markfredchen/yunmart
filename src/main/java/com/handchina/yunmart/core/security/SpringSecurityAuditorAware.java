package com.handchina.yunmart.core.security;

import com.handchina.yunmart.constant.Constants;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

/**
 * Implementation of AuditorAware based on Spring Security.
 */
@Component
public class SpringSecurityAuditorAware implements AuditorAware<String> {

    @Override
    public String getCurrentAuditor() {
        String username = SecurityUtils.getCurrentLogin();
        return (username != null ? username : Constants.SYSTEM_USER);
    }
}
