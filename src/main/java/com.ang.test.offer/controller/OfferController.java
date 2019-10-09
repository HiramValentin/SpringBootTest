package com.ang.test.offer.controller;

import com.ang.test.offer.dto.OfferDTO;
import com.ang.test.offer.service.OfferService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/offer")
public class OfferController {

    private OfferService offerService;

    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<OfferDTO>> findAll() {

        List<OfferDTO> result = offerService.findAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<List<OfferDTO>> findAllActive(@RequestParam(value = "activeFrom", required = false) Date activeOn) {

        List<OfferDTO> result = offerService.findAllDTO(activeOn);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<OfferDTO> add(@Valid @RequestBody OfferDTO offerDTO) {

        offerDTO.setId(null);
        return new ResponseEntity<>(offerService.save(offerDTO), HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity<OfferDTO> edit(@Valid @RequestBody OfferDTO offerDTO) {

        // Null IDs are not allowed
        return new ResponseEntity<>(offerService.save(offerDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {

        offerService.deleteOffer(id);
    }
}