package com.handchina.yunmart.core.persistence;

import com.handchina.yunmart.core.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by markfredchen on 9/21/15.
 */
public interface CompanyRepository extends JpaRepository<Company, Long> {
}
