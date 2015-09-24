package com.handchina.yunmart.core.domain.common;

import com.handchina.yunmart.core.domain.DomainObject;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

/**
 * Created by markfredchen on 9/15/15.
 */
@MappedSuperclass
abstract public class Category extends DomainObject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long categoryID;

    @NotEmpty
    @Column(unique = true)
    protected String name;

    public Long getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Long categoryID) {
        this.categoryID = categoryID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
