package br.com.project.storage.back.controller;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.project.storage.back.enums.CountingFormat;
import br.com.project.storage.back.model.Product;
import br.com.project.storage.back.service.ProductService;

@Controller
public class ProductController {
    private @Autowired ProductService productService;
    
    public Set<Product> getAll(){
        return productService.getAll();
    }
    public void post(Product product){
        productService.Post(product);
    }
    public void post(int codeForn,String name,double weight,CountingFormat format,int unit){
        productService.Put(new Product(codeForn,name,weight,format,unit));
    }
    public void put(Product product){
        productService.Put(product);
    }
    public Optional<Product> getByName(Integer idProduct){
        return productService.getByName(idProduct);
    }
    public void deleteById(Integer idProduct){
        productService.delete(idProduct);
    }
}
