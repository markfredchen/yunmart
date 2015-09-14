package com.handchina.yunmart.core.persistence;

import com.handchina.yunmart.core.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by markfredchen on 9/13/15.
 */
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findOneByName(String name);
}
