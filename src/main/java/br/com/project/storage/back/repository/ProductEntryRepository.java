package br.com.project.storage.back.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.project.storage.back.model.ProductEntry;

@Repository
public interface ProductEntryRepository extends CrudRepository<ProductEntry,Integer> {
    
}
