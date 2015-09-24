package com.handchina.yunmart.web.rest.resource.adapter;

import com.handchina.yunmart.core.domain.DomainObject;
import com.handchina.yunmart.web.rest.resource.BaseResource;

/**
 * Created by markfredchen on 9/18/15.
 */
public interface ResourceAdapter<T extends DomainObject, S extends BaseResource> {
    T toDomainObject(S s);

    S toResource(T t);
}
