package com.minerva.domain.entities.sale;

import com.minerva.domain.valueObject.ProductQuantity;
import com.minerva.domain.valueObject.Money;
import com.minerva.domain.valueObject.id.ProductId;
import com.minerva.domain.services.Result;
import com.minerva.domain.exceptions.DomainException;
import com.minerva.domain.exceptions.NullValueException;
import com.minerva.domain.exceptions.UnexpectedDomainException;
import com.minerva.domain.entities.Entity;
import com.minerva.domain.constants.PaymentMethod;
import com.minerva.domain.valueObject.CustomerName;
import com.minerva.domain.valueObject.id.SaleIdImpl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

public class Sale extends Entity<SaleId> {
    private final CustomerName customerName;
    private final LocalDateTime registrationDate;

    private final List<Pay>  pays =  new LinkedList<>();
    private final Map<ProductId, SaleDetail> saleDetails = new HashMap<>();

    public Sale(String customerNameId, List<SaleItem> items) throws DomainException {    
        super(SaleIdImpl.generate());
        this.customerName = new CustomerName(customerNameId);
        if (items == null) {
            throw new NullValueException("La venta debe tener al menos un item");
        }

        if (items.isEmpty()) {
            throw new DomainException("La venta debe tener al menos un item");
        }

        for (SaleItem item : items) {
            this.addDetail(
                item.productSale,
                item.unitPrice,
                item.quantity
            );
        }

        // Valores por defecto
        this.registrationDate = LocalDateTime.now();
    }

    public Sale(UUID saleId, String customerNameId, LocalDateTime registrationDate, List<SaleDetailDTO> saleDetails, List<PayDTO> pays) {
        SaleIdImpl tempId;
        try {
            tempId = new SaleIdImpl(saleId);
            this.customerName = new CustomerName(customerNameId);
            this.registrationDate = registrationDate;             
        } catch (DomainException e) {
            throw new UnexpectedDomainException("Error al crear la venta: " + e.getMessage(), e);
        }
        super(tempId);

        try {
            if (saleDetails != null && !saleDetails.isEmpty()) {
                for (SaleDetailDTO detailDTO : saleDetails) {
                    ProductId productId = new ProductId(detailDTO.productId);
                    SaleDetail saleDetail = new SaleDetail(
                        detailDTO.saleDetailId,
                        detailDTO.quantity,
                        detailDTO.unitPrice
                    );
                    this.saleDetails.put(productId, saleDetail);
                }
            } else {
                throw new DomainException("La venta debe tener al menos un detalle");
            }
        } catch (DomainException e) {
            throw new UnexpectedDomainException("Error al crear la venta: " + e.getMessage(), e);
        }

        if (pays != null) {
            for (PayDTO payDTO : pays) {
                this.pays.add(new Pay(
                    payDTO.payId,
                    payDTO.amount,
                    payDTO.paymentMethod,
                    payDTO.registrationDate
                ));
            }
        }
    }

    public record SaleItem(ProductSale productSale, BigDecimal quantity, BigDecimal unitPrice) {}

    public record SaleDetailDTO(UUID saleDetailId, UUID productId, BigDecimal quantity, BigDecimal unitPrice) {}

    public record PayDTO(UUID payId, BigDecimal amount, PaymentMethod paymentMethod, LocalDateTime registrationDate) {}

    // nota: se deberia poner un minimo de ganancia sobre el costo cuando se negocia con el cliente el precio, por el momento solo se mira si es menor que el costo
    private void addDetail(
            ProductSale productSale,
            BigDecimal negotiatedUnitPrice,
            BigDecimal quantityBigDecimal
    ) throws DomainException {

        if (saleDetails.containsKey(productSale.getId()))
            throw new DomainException("El producto ya existe en la venta. Modifique la cantidad en lugar de agregarlo nuevamente.");

        Money unitPriceMoney;

        if (negotiatedUnitPrice == null) {
            unitPriceMoney = productSale.getPrice();
        } else {
            unitPriceMoney = new Money(negotiatedUnitPrice);

            if (unitPriceMoney.isLessThan(productSale.getCost())) {
                throw new DomainException("El precio de venta no puede ser menor que el costo.");
            }
        }

        SaleDetail newDetail = new SaleDetail(
                new ProductQuantity(quantityBigDecimal),
                unitPriceMoney
        );

        saleDetails.put(productSale.getId(), newDetail);
    }

    public Result<Void> addPayment(BigDecimal amount, PaymentMethod paymentMethod) {
        if (isDueCanceled()) return Result.fail("La VENTA ya esta CANCELADA");

        Pay payCreated;
        try {
            payCreated = new Pay(new Money(amount), paymentMethod);
        } catch (DomainException e) {
            return Result.fail(e.getMessage());
        }
        
        if (payCreated.getAmount().isGreaterThan(calculateAmountDue()))
            return Result.fail("El PAGO sobrepasa la DEUDA de la VENTA.");

        pays.add(payCreated);
        return Result.success(null);
    }

    public Money calculateTotal() {
        return saleDetails.values().stream()
                .map(SaleDetail::calculateSubTotal)
                .reduce(Money.zero(), Money::add);
    }

    public Money calculateTotalPaid() {
        return pays.stream()
                .map(Pay::getAmount)
                .reduce(Money.zero(), Money::add);
    }

    public Money calculateAmountDue() {
        try {
            return calculateTotal().subtract(calculateTotalPaid());
        } catch (DomainException e) {
            throw new UnexpectedDomainException("Error al calcular el monto adeudado: " + e.getMessage(), e);
        }
    }

    public boolean isDueCanceled() {
        return calculateAmountDue().isZero();
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public CustomerName getCustomerId() {
        return customerName;
    }

    public List<PayDTO> getPays() {
        List<PayDTO> paysDTO = new ArrayList<>(pays.size());
        for (Pay pay : pays) {
            paysDTO.add(new PayDTO(
                pay.getId().value,
                pay.getAmount().value, 
                pay.getPaymentMethod(), 
                pay.getRegistrationDate()));
        }
        return paysDTO;
    }

    public List<SaleDetailDTO> getSaleDetails() {
        List<SaleDetailDTO> saleDetailDTOList = new ArrayList<>(saleDetails.size());

        saleDetails.forEach((productId, saleDetail) -> saleDetailDTOList.add(
                new SaleDetailDTO(
                        saleDetail.getId().value,
                        productId.value,
                        saleDetail.getQuantity().value,
                        saleDetail.getUnitPrice().value)));

        return saleDetailDTOList;
    }

    /*public Map<ProductId, ProductQuantity> getProductQuantities() {
        HashMap<ProductId, ProductQuantity> productIds = new HashMap<>();

        for (SaleDetail detail : saleDetails.values()) {
            productIds.put(detail.getProductId(), detail.getQuantity());
        }

        return productIds;
    }*/

}
