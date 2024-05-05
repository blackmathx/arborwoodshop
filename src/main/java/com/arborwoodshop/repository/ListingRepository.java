package com.arborwoodshop.repository;

import com.arborwoodshop.model.Listing;
import org.springframework.data.repository.ListCrudRepository;

public interface ListingRepository extends ListCrudRepository<Listing, Long> {

}
