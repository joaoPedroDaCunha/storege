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

    @Override
    public String toString() {
        return "Lote " + batchCode + "";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + batchCode;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ProductAssistant other = (ProductAssistant) obj;
        if (batchCode != other.batchCode)
            return false;
        return true;
    }

    
}
