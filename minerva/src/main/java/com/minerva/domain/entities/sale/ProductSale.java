package com.minerva.domain.entities.sale;

import com.minerva.domain.entities.product.ProductId;
import com.minerva.domain.valueObject.Money;

public interface ProductSale {
    ProductId getId();
    Money getCost();
    Money calculatePrice();
}
