package com.arborwoodshop.repository;

import org.springframework.data.repository.ListCrudRepository;
import com.arborwoodshop.model.Item;

public interface ItemRepository extends ListCrudRepository<Item, Long> {

}
