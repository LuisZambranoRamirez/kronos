package com.minerva.domain.entities.userAction;

import java.time.LocalDateTime;

import com.minerva.domain.constants.Permission;
import com.minerva.domain.entities.Entity;
import com.minerva.domain.entities.user.UserId;
import com.minerva.domain.valueObject.id.Id;
import com.minerva.domain.exceptions.NullValueException;
import com.minerva.domain.valueObject.id.UserActionIdImpl;
import com.minerva.domain.valueObject.id.UserName;

public class UserAction extends Entity<UserActionId> {
    private final UserId userId;
    private final Permission permission;
    private final Entity<?> entity;
    
    private final LocalDateTime registrationDate;

    public UserAction(UserName userName, Permission permission, Entity<?> entity) throws NullValueException {
        if (permission == null) throw new NullValueException("El permiso no puede ser nulo.");
        if (entity == null) throw new NullValueException("El ID de la entidad no puede ser nulo.");

        super(UserActionIdImpl.generate());
        this.permission = permission;
        this.userId = userName;
        this.entity = entity;
        this.registrationDate = LocalDateTime.now();
    }

    public UserId getUserId() {
        return userId;
    }

    public Permission getPermission() {
        return permission;
    }

    public Id<?> getEntityId() {
        return entity.getId();
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }
}
