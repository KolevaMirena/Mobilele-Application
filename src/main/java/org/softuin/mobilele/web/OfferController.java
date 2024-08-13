package org.softuin.mobilele.web;

import org.softuin.mobilele.model.dto.CreateOfferDTO;
import org.softuin.mobilele.service.OfferService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/offers")
public class OfferController {


    private final OfferService offerService;

    public OfferController(OfferService offerService){
        this.offerService = offerService;
    }


    @GetMapping("/all")
    public String all(){

        return "offers";
    }

    @GetMapping("/add")
    public String addOffer(){

        return "offer-add";
    }

    @PostMapping("")
    public String addOffer(CreateOfferDTO createOfferDTO){

        offerService.createOffer(createOfferDTO);

        return "index";

    }


    @GetMapping("/{uuid}/details")
    public String details(@PathVariable("uuid") String uuid ){

        return "details";


    }



}

