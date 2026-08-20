package com.minerva.domain.entities.sale;

import com.minerva.domain.valueObject.id.ProductId;
import com.minerva.domain.valueObject.Money;

public interface ProductSale {
    ProductId getId();
    Money getCost();
    Money getPrice();
}
