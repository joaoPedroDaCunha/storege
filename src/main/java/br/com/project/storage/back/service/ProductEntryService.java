package br.com.project.storage.back.service;

import java.util.Comparator;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.project.storage.back.enums.EntryStatus;
import br.com.project.storage.back.model.ProductEntry;
import br.com.project.storage.back.repository.ProductEntryRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductEntryService {
    private @Autowired ProductEntryRepository productEntryRepository ;

    public Set<ProductEntry> getAll(){
        Iterable<ProductEntry> productEntrey = productEntryRepository.findAll();
        Set<ProductEntry> get = new TreeSet<>();
        productEntrey.forEach(get::add);
        return get ;
    }

    public Set<ProductEntry> getAllWaiting() {
    return StreamSupport.stream(productEntryRepository.findAll().spliterator(), false)
        .filter(pe -> pe.getStatus() == EntryStatus.Waiting)
        .collect(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparingInt(ProductEntry::getCodeEntry))));
    }


    public Optional<ProductEntry> getById (Integer idInteger){
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

    @Transactional(readOnly = true)
    public ProductEntry findByIdWithCollections(Integer id) {
        ProductEntry entry = productEntryRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Não achou"));
        // força carregamento
        entry.getItems().size();
        entry.getDocument().size();
        return entry;
    }

}
