package com.minerva.domain.entities.customer;

import com.minerva.domain.entities.Entity;
import com.minerva.domain.exceptions.EntityRestoreException;
import com.minerva.domain.exceptions.InvalidDomainArgumentException;
import com.minerva.domain.valueObject.PhoneNumber;
import com.minerva.domain.services.Result;
import com.minerva.domain.exceptions.DomainException;
import com.minerva.domain.valueObject.id.CustomerName;

import java.time.LocalDateTime;
import java.util.Optional;

public class Customer extends Entity<CustomerId> {
    private final CustomerName customerName;
    // Puede ser null
    private PhoneNumber phoneNumber;
    // ------------
    private final LocalDateTime registrationDate;

    public Customer(String name, String phoneNumber) throws InvalidDomainArgumentException {
        CustomerName tempId = new CustomerName(name);
        super(tempId);
        this.customerName = new CustomerName(name);
        if (phoneNumber != null) {
            this.phoneNumber = new PhoneNumber(phoneNumber);
        }
        this.registrationDate = LocalDateTime.now();
    }

    public Customer(String customerName, LocalDateTime registrationDate, String phoneNumber) {
        CustomerName tempId;
        try {
            tempId = new CustomerName(customerName);
            this.customerName = tempId;
            this.registrationDate = registrationDate;
            if (phoneNumber != null) {
                this.phoneNumber = new PhoneNumber(phoneNumber);
            }
        } catch (DomainException e) {
            throw new EntityRestoreException("Error al crear el cliente: " + e.getMessage(), e);
        }
        super(tempId);
    }

    public CustomerName getCustomerName() {
        return customerName;
    }

    public Optional<PhoneNumber> getPhoneNumber() {
        return Optional.ofNullable(phoneNumber);
    }

    public Result<Void> updatePhoneNumber(String newPhoneNumber) {
        try {
            this.phoneNumber = new PhoneNumber(newPhoneNumber);
            return Result.success(null);
        } catch (InvalidDomainArgumentException e) {
            return Result.fail(e.getMessage());
        }
    }

    public void removePhoneNumber() {
        this.phoneNumber = null;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

}
