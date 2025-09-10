package br.com.project.storage.back.model;

import br.com.project.storage.back.enums.CountingFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Product")
public class Product implements Comparable<Product>{
    
    private @Column(name = "Product_id") @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Integer codeProduct ;
    private @Column(name = "Forn_code") int codeProductForn;
    private @Column(name = "Product",length = 30 , nullable = false) String nameProduct;
    private @Column(name = "BaseWeight") double baseWeight;
    private @Column(nullable = false) @Enumerated(EnumType.STRING) CountingFormat format;
    private @Column(name = "BaseUnit") int baseUnit;

    public Product(){

    }

    public Product(int codeProduct, int codeProductForn, String nameProduct, double baseWeight, CountingFormat format,
            int baseUnit) {
        this.codeProduct = codeProduct;
        this.codeProductForn = codeProductForn;
        this.nameProduct = nameProduct;
        this.baseWeight = baseWeight;
        this.format = format;
        this.baseUnit = baseUnit;
    }

    public Product( int codeProductForn, String nameProduct, double baseWeight, CountingFormat format,
            int baseUnit) {
        this.codeProductForn = codeProductForn;
        this.nameProduct = nameProduct;
        this.baseWeight = baseWeight;
        this.format = format;
        this.baseUnit = baseUnit;
    }


    public int getCodeProduct() {
        return codeProduct;
    }

    public void setCodeProduct(int codeProduct) {
        this.codeProduct = codeProduct;
    }

    public int getCodeProductForn() {
        return codeProductForn;
    }

    public void setCodeProductForn(int codeProductForn) {
        this.codeProductForn = codeProductForn;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public double getBaseWeight() {
        return baseWeight;
    }

    public void setBaseWeight(double baseWeight) {
        this.baseWeight = baseWeight;
    }

    public CountingFormat getFormat() {
        return format;
    }

    public void setFormat(CountingFormat format) {
        this.format = format;
    }

    public int getBaseUnit() {
        return baseUnit;
    }

    public void setBaseUnit(int baseUnit) {
        this.baseUnit = baseUnit;
    }
    
    @Override
    public int compareTo(Product arg0) {
        return nameProduct.compareToIgnoreCase(arg0.getNameProduct());
    }

    @Override
    public String toString() {
        return this.getNameProduct();
    }   

}
