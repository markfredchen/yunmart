package com.handchina.yunmart.core.persistence;

import com.handchina.yunmart.core.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

/**
 * Created by markfredchen on 9/13/15.
 */
public interface AuthorityRepository extends JpaRepository<Authority, String> {
    Set<Authority> findByNameIn(Set<String> names);
}
