package com.ang.test.offer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.SafeHtml;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private Long id;
    // Not null
    // 150 chars
    // Prevent XSS
    @NotNull
    @Size(max = 150)
    @SafeHtml
    private String name;
    // 10 integer positions, 2 decimal positions
    // No negative
    @Digits(integer = 10, fraction = 2)
    @DecimalMin("0.00")
    private BigDecimal price;

}
