package br.com.project.storage.back.service;

import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.project.storage.back.model.Product;
import br.com.project.storage.back.repository.ProductRepository;

@Service
public class ProductService {
    private @Autowired ProductRepository ProductRepository;

    public Set<Product> getAll() {
        Iterable<Product> products = ProductRepository.findAll();
        Set<Product> get = new TreeSet<>();
        products.forEach(get::add);
        return get;
    }
    public void Post(Product Product){
        ProductRepository.save(Product);
    }
    public Optional<Product> getByName(Integer idProduct){
        return ProductRepository.findById(idProduct);
    }
    public void delete(Integer idProduct){
        ProductRepository.deleteById(idProduct);
    }
}
