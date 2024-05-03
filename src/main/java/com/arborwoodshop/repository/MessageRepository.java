package com.arborwoodshop.repository;

import com.arborwoodshop.model.Message;
import org.springframework.data.repository.ListCrudRepository;

public interface MessageRepository extends ListCrudRepository<Message, Long> {

}
