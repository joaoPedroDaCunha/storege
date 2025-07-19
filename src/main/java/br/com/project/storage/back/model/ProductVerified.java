package br.com.project.storage.back.model;

import java.util.ArrayList;
import java.util.List;

public class ProductVerified {
    private Product product;
    private double totalWeight;
    private int totalAmount;
    private List<ProductAssistant> items = new ArrayList<>();
    
    public ProductVerified(Product product, double totalWeight, int totalAmount, List<ProductAssistant> items) {
        this.product = product;
        this.totalWeight = totalWeight;
        this.totalAmount = totalAmount;
        this.items = items;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(double totalWeight) {
        this.totalWeight = totalWeight;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<ProductAssistant> getItems() {
        return items;
    }

    public void setItems(List<ProductAssistant> items) {
        this.items = items;
    }

}
