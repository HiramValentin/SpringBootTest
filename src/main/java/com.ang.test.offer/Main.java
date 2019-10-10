package com.ang.test.offer;

import com.ang.test.offer.domain.Offer;
import com.ang.test.offer.dto.OfferDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.math.BigDecimal;

@SpringBootApplication
@ComponentScan("com.ang.test.offer")
public class Main {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        modelMapper.createTypeMap(Offer.class, OfferDTO.class)
                .addMappings(
                        // that should be simplified.
                        new PropertyMap<Offer, OfferDTO>() {
                            @Override
                            protected void configure() {
                                using(ctx -> generateDiscountedPrice(
                                        ((Offer) ctx.getSource()).getProduct().getPrice(),
                                        ((Offer) ctx.getSource()).getDiscountPct())
                                ).map(source, destination.getDiscountedPrice());
                            }
                        }).addMappings(
                        // that should be simplified.
                        new PropertyMap<Offer, OfferDTO>() {
                            @Override
                            protected void configure() {
                                using(ctx -> generateSaving(
                                        ((Offer) ctx.getSource()).getProduct().getPrice(),
                                        ((Offer) ctx.getSource()).getDiscountPct())
                                ).map(source, destination.getSaving());
                            }
                        });

        // test that all fields are mapped
        modelMapper.validate();
        return modelMapper;
    }

    private BigDecimal generateSaving(BigDecimal price, BigDecimal discountPct) {

        return price.multiply(discountPct);
    }

    private BigDecimal generateDiscountedPrice(BigDecimal price, BigDecimal discountPct) {

        return price.multiply(new BigDecimal("1.00").subtract(discountPct));
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
