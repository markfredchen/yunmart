package com.handchina.yunmart.core.exception;

import java.util.UUID;
import java.util.function.Supplier;

/**
 * Created by markfredchen on 3/26/15.
 */
public class ObjectNotFoundException extends RuntimeException implements Supplier<RuntimeException> {

    private static final long serialVersionUID = 1L;

    public ObjectNotFoundException(Class targetObject, Long targetObjectID) {
        super(targetObject.getSimpleName() + "[id=" + targetObjectID + "] not found");
    }

    public ObjectNotFoundException(Class targetObject, UUID targetObjectOID) {
        super(targetObject.getSimpleName() + "[id=" + targetObjectOID + "] not found");
    }

    @Override
    public RuntimeException get() {
        return this;
    }
}
