package com.arborwoodshop.repository;

import com.arborwoodshop.model.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListCrudRepository;

public interface ItemRepository extends ListCrudRepository<Item, Long> {



}
