package ca.mcgill.ecse321.petshelter.controller;

import ca.mcgill.ecse321.petshelter.dto.DonationDTO;
import ca.mcgill.ecse321.petshelter.model.Donation;
import ca.mcgill.ecse321.petshelter.repository.UserRepository;
import ca.mcgill.ecse321.petshelter.service.DonationService;
import ca.mcgill.ecse321.petshelter.service.EmailingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
public class DonationController {
    
    @Autowired
    private DonationService donationService;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private EmailingService emailingService;
    
    @GetMapping(value = {"/donation"})
    public List<DonationDTO> getAllDonations() {
        return donationService.getAllUsers().stream().map(this::convertToDto).collect(Collectors.toList());
    }
    
    public DonationDTO convertToDto(Donation donation) {
        System.out.println(donation.toString());
        DonationDTO donationDTO = new DonationDTO();
        donationDTO.setDate(donation.getDate());
        donationDTO.setTime(donation.getTime());
        donationDTO.setAmount(donation.getAmount());
        try {
            donationDTO.setUser(donation.getUser().getUserName());
        } catch (NullPointerException e) {
            donationDTO.setUser(null); //occurs when it is an anonymous donor, no account.
        }
        return donationDTO;
    }
    
    
    //also, we need to figure out how we can donate without having a user account. 
    @PostMapping(value = {"/donation"})
    public ResponseEntity<?> createDonation(@RequestBody DonationDTO donationDTO) throws IllegalArgumentException {
        //System.out.println(amount.toString());
        Donation donation = donationService.createDonation(donationDTO);
        try {
            if (donation.getUser() != null) {
                donationDTO.setUser(donation.getUser().getUserName());
                emailingService.donationConfirmation(donationDTO);
            }
            donationDTO.setTime(donation.getTime());
            donationDTO.setDate(donation.getDate());
            donationDTO.setAmount(donation.getAmount());
            return new ResponseEntity<>(donationDTO, HttpStatus.OK);
            //todo, send email to the user
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
}
