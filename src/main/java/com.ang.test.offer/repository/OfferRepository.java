package com.ang.test.offer.repository;

import com.ang.test.offer.domain.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {

    @Query("SELECT o FROM Offer o where o.activeFrom <= ?1 and (o.activeUntil is null or o.activeUntil >= ?1 ) ")
    public List<Offer> findAll(Date activeOn);
}
