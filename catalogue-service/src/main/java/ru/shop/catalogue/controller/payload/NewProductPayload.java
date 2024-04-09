package ru.shop.catalogue.controller.payload;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record NewProductPayload(
        @NotNull(message = "{catalogue.products.create.errors.productName_size_is_null}")
        @Size(min = 3, max = 30, message = "{catalogue.products.create.errors.productName_size_is_invalid}")
        String productName,
        @Size(max = 1000,  message = "{catalogue.products.create.errors.description_size_is_invalid}")
        String description) {
}
