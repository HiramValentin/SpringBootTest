package com.ang.test.offer;

import com.ang.test.offer.domain.Offer;
import com.ang.test.offer.domain.Product;
import com.ang.test.offer.dto.OfferDTO;
import com.ang.test.offer.repository.OfferRepository;
import com.ang.test.offer.service.OfferService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
public class OfferIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private OfferService offerService;

    @Test
    public void createNewOffer() throws Exception {

        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");

        int startSize = offerService.findAllDTO(df.parse("09/02/2022")).size();

        Product product = new Product(null, "Atari", new BigDecimal("500.00"));
        entityManager.persist(product);
        entityManager.flush();

        Offer offer = new Offer(null, new BigDecimal("0.20"), df.parse("09/01/2022"), df.parse("09/22/2022"), product);
        entityManager.persist(offer);
        entityManager.flush();

        int endSize = offerService.findAllDTO(df.parse("09/02/2022")).size();
        assertEquals(startSize + 1, endSize);
    }

    @Test
    public void deleteOffer() {

        int startSize = offerService.findAll().size();

        List<OfferDTO> offers = offerService.findAll();
        assertTrue("There are no offers", !offers.isEmpty());

        Long id = offers.get(0).getId();

        offerService.deleteOffer(id);


        int endSize = offerService.findAll().size() + 1;

        assertTrue("Element not deleted", startSize == endSize);
    }
}
