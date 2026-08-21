package com.minerva.domain.entities.userAction;

import java.time.LocalDateTime;

import com.minerva.domain.constants.Permission;
import com.minerva.domain.entities.Entity;
import com.minerva.domain.valueObject.id.Id;
import com.minerva.domain.exceptions.NullValueException;
import com.minerva.domain.valueObject.id.UserActionIdImpl;
import com.minerva.domain.valueObject.id.UserName;

public class UserAction extends Entity<UserActionId> {
    private final UserName userName;
    private final Permission permission;
    private final Id<?> entityId;
    
    private final LocalDateTime registrationDate;

    public UserAction(UserName userName, Permission permission, Id<?> entityId) throws NullValueException {
        if (permission == null) throw new NullValueException("El permiso no puede ser nulo.");
        if (entityId == null) throw new NullValueException("El ID de la entidad no puede ser nulo.");

        super(UserActionIdImpl.generate());
        this.permission = permission;
        this.userName = userName;
        this.entityId = entityId;
        this.registrationDate = LocalDateTime.now();
    }

    public UserName getUserName() {
        return userName;
    }

    public Permission getPermission() {
        return permission;
    }

    public Id<?> getEntityId() {
        return entityId;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }
}
