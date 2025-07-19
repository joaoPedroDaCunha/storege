package br.com.project.storage.back.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.project.storage.back.model.ProductEntry;

public interface ProductEntryRepository extends CrudRepository<ProductEntry,Integer> {
    
}
