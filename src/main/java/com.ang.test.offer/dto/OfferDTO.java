package com.ang.test.offer.dto;

import com.ang.test.offer.annotations.ValidDate;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OfferDTO {

    private Long id;
    // Between 0 and 1
    @DecimalMax("1")
    @DecimalMin("0")
    private BigDecimal discountPct;
    // Mandatory
    // Output format: MM/dd/yyyy
    // Input format: MM/dd/yyyy
    @NotNull(message = "The start date can not be null, it is mandatory.")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="MM/dd/yyyy")
    private Date activeFrom;
    // Optional
    // Output format: MM/dd/yyyy
    // Input format: MM/dd/yyyy
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="MM/dd/yyyy")
    private Date activeUntil;
    // Calculated
    private BigDecimal discountedPrice;
    // Calculated
    private BigDecimal saving;
    // Product ID is mandatory
    @NotNull(message = "The product can not be null, it is mandatory.")
    private ProductDTO product;
}
