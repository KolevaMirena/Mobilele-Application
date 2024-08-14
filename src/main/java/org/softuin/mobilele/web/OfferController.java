package org.softuin.mobilele.web;

import jakarta.validation.Valid;
import org.softuin.mobilele.model.dto.CreateOfferDTO;
import org.softuin.mobilele.model.enums.EngineEnum;
import org.softuin.mobilele.service.BrandService;
import org.softuin.mobilele.service.OfferService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.UUID;

@Controller
@RequestMapping("/offer")
public class OfferController {


    private final OfferService offerService;
    private final BrandService brandService;
    public OfferController(OfferService offerService, BrandService brandService){
        this.offerService = offerService;
        this.brandService = brandService;
    }


    @ModelAttribute("engines")
    public EngineEnum[] engines(){
        return EngineEnum.values();
    }

    @GetMapping("/add")
    public String addOffer(Model model){

        if(!model.containsAttribute("createOfferDTO")){
            model.addAttribute("createOfferDTO", CreateOfferDTO.empty());
        }

        model.addAttribute("brands", brandService.getAllBrands());

        return "offer-add";
    }

    @PostMapping("/add")
    public String addOffer(@Valid CreateOfferDTO createOfferDTO, BindingResult bindingResult,
                                    RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("createOfferDTO", createOfferDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.createOfferDTO", bindingResult);
            return "redirect:/offer/add";
        }
        UUID newOfferId = offerService.createOffer(createOfferDTO);
        return "redirect:/offer/" + newOfferId ;
        }

    @GetMapping("/{uuid}")
    public String details(@PathVariable("uuid") String uuid ){

        return "details";
    }

}

