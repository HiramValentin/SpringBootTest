package com.ang.test.offer.domain;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "OFFER")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    // 20 characters
    private Long id;
    // Not null
    // 1 integer character, 2 decimal characters
    private BigDecimal discountPct;
    // Not null
    private Date activeFrom;
    // Nullable (Null means that this offer will be valid for undefined time)
    private Date activeUntil;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    // Not null
    // Don't load it unless necessary
    private Product product;

}
