package org.example.microservice.entity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface bookRepository extends CrudRepository<book, Long> {
}
