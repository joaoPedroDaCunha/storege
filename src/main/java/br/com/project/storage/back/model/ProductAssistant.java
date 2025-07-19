package br.com.project.storage.back.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class ProductAssistant {
    private int batchCode;
    private int quantity;
    private int totalWeight;
    
    public ProductAssistant(int batchCode, int quantity, int totalWeight) {
        this.batchCode = batchCode;
        this.quantity = quantity;
        this.totalWeight = totalWeight;
    }

    public int getBatchCode() {
        return batchCode;
    }

    public void setBatchCode(int batchCode) {
        this.batchCode = batchCode;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(int totalWeight) {
        this.totalWeight = totalWeight;
    }

    
}
