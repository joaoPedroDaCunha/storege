package br.com.project.storage.back.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.project.storage.back.model.ProductEntry;
import br.com.project.storage.back.service.ProductEntryService;

@Controller
public class ProductEntreyController {
    private @Autowired ProductEntryService productEntryService;

    public Set<ProductEntry> getAll(){
        return productEntryService.getAll();
    }

    public void Post(ProductEntry productEntry){
        productEntryService.Post(productEntry);
    }

    public void Put(ProductEntry productEntry){
        productEntryService.Put(productEntry);
    }

    public void Delete(ProductEntry productEntry){
        productEntryService.Delete(productEntry);
    }

    public void Delete(Integer idInteger){
        productEntryService.Delete(idInteger);
    }

}
