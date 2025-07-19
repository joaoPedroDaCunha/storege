package br.com.project.storage.back.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.project.storage.back.model.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product,Integer>{

    
} 
