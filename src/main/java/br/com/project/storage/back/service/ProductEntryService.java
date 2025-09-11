package br.com.project.storage.back.service;

import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.project.storage.back.model.ProductEntry;
import br.com.project.storage.back.repository.ProductEntryRepository;

@Service
public class ProductEntryService {
    private @Autowired ProductEntryRepository productEntryRepository ;

    public Set<ProductEntry> getAll(){
        Iterable<ProductEntry> productEntrey = productEntryRepository.findAll();
        Set<ProductEntry> get = new TreeSet<>();
        productEntrey.forEach(get::add);
        return get ;
    }

    public Optional<ProductEntry> getByID (Integer idInteger){
        return productEntryRepository.findById(idInteger);
    }

    public void Post(ProductEntry productEntry){
        productEntryRepository.save(productEntry);
    }

    public void Put(ProductEntry productEntry){
        productEntryRepository.save(productEntry);
    }

    public void Delete(ProductEntry productEntry){
        productEntryRepository.delete(productEntry);
    }

    public void Delete(Integer idInteger){
        productEntryRepository.deleteById(idInteger);
    }
}
