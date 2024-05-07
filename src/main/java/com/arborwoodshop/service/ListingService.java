package com.arborwoodshop.service;

import com.arborwoodshop.model.Listing;
import com.arborwoodshop.model.User;
import com.arborwoodshop.repository.ListingRepository;
import org.springframework.stereotype.Service;

@Service
public class ListingService {

    private final ListingRepository listingRepo;

    public ListingService(ListingRepository listingRepo){
        this.listingRepo = listingRepo;
    }

    public void createListing(Listing listing, Long userId){
        User user = new User();
        user.setId(userId);
        listing.setUser(user);

        listingRepo.save(listing);
    }
}
