package com.ang.test.offer.service;

import com.ang.test.offer.domain.Offer;
import com.ang.test.offer.dto.OfferDTO;
import com.ang.test.offer.repository.OfferRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfferService {

    private OfferRepository offerRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public OfferService(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    /**
     * This method was changed to private
     */
    private List<Offer> findAll(Date activeOn) {
        if ( activeOn == null ) {
            activeOn = new Date(System.currentTimeMillis());
        }

        return offerRepository.findAll(activeOn);
    }

    //Created by Hiram
    public List<OfferDTO> findAll() {

        List<Offer> offers = offerRepository.findAll();
        if(offers.isEmpty())
            return new ArrayList<>();

        //Modified by Hiram, The data was mapped with ModelMapper
        return offers.stream().map(offer -> modelMapper.map(offer, OfferDTO.class)).collect(Collectors.toList());
    }

    public List<OfferDTO> findAllDTO(Date activeOn) {

        List<Offer> offers = findAll(activeOn);
        if(offers.isEmpty())
            return new ArrayList<>();

        //Modified by Hiram, The data was mapped with ModelMapper
        return offers.stream().map(offer -> modelMapper.map(offer, OfferDTO.class)).collect(Collectors.toList());
    }

    //The access modifer was changed to private
    private Offer save(Offer offer) {
        return offerRepository.save(offer);
    }

    public OfferDTO save(OfferDTO offerDTO) {

        //Modified by Hiram, The data was mapped with ModelMapper
        Offer offer = modelMapper.map(offerDTO, Offer.class);

        Offer saved = save(offer);

        //Modified by Hiram, The data was mapped with ModelMapper
        return modelMapper.map(saved, OfferDTO.class);
    }

    //Created by Hiram
    public void deleteOffer(Long id) {
        offerRepository.deleteById(id);
    }

}