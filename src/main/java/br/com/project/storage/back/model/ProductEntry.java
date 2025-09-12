package br.com.project.storage.back.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.project.storage.back.enums.CountingFormat;
import br.com.project.storage.back.enums.EntryStatus;
import br.com.project.storage.back.excptions.ValidationException;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ProductEntry")
public class ProductEntry implements Comparable<ProductEntry>{

    private @Column(name = "CodeEntry_id") @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Integer codeEntry;
    private @OneToOne @JoinColumn(name = "Product_id") Product product;
    private @Column Date dateEntry;
    private @Column(name = "EntryStatus") @Enumerated(EnumType.STRING) EntryStatus status;
    private @Column(nullable = false) double totalWeight;
    private @Column(nullable = false) int totalAmount;
    private @ElementCollection List<ProductAssistant> items = new ArrayList<>();
    private @Column String delivererName;
    private @Column(name = "vehiclePlate",length = 7,nullable = false)String vehiclePlate;
    private @Column(name = "Phone") int phone;
    private @OneToMany(mappedBy = "productEntry", cascade = CascadeType.ALL) List<AuxiliaryDocument> Document = new ArrayList<>();

    public ProductEntry(){

    }

    public ProductEntry(Integer codeEntry, Product product, Date dateEntry, EntryStatus status, double totalWeight,
            int totalAmount, List<ProductAssistant> items, String delivererName, String vehiclePlate, int phone,
            List<AuxiliaryDocument> document) {
        this.codeEntry = codeEntry;
        this.product = product;
        this.dateEntry = dateEntry;
        this.status = status;
        this.totalWeight = totalWeight;
        this.totalAmount = totalAmount;
        this.items = items;
        this.delivererName = delivererName;
        this.vehiclePlate = vehiclePlate;
        this.phone = phone;
        Document = document;
    }

    public Integer getCodeEntry() {
        return codeEntry;
    }

    public void setCodeEntry(Integer codeEntry) {
        this.codeEntry = codeEntry;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Date getDateEntry() {
        return dateEntry;
    }

    public void setDateEntry(Date dateEntry) {
        this.dateEntry = dateEntry;
    }

    public EntryStatus getStatus() {
        return status;
    }

    public void setStatus(EntryStatus status) {
        this.status = status;
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

    public List<ProductAssistant> getItems(){
        return items;
    }

    public void setItems(int batchCode,int quantity,int totalWeight){
        this.items.add(new ProductAssistant(batchCode,quantity,totalWeight));
    }

    public void setItems(ProductAssistant productAssistant){
        this.items.add(productAssistant);
    }

    public String getDelivererName() {
        return delivererName;
    }

    public void setDelivererName(String delivererName) {
        this.delivererName = delivererName;
    }

    public String getVehiclePlate() {
        return vehiclePlate;
    }

    public void setVehiclePlate(String vehiclePlate) {
        this.vehiclePlate = vehiclePlate;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public List<AuxiliaryDocument> getDocument() {
        return Document;
    }

    public void setDocument(AuxiliaryDocument document) {
        this.Document.add(document);
    }

    public static void validateProductEntry(ProductEntry entry) throws ValidationException {
    
    double totalWeight = entry.getItems().stream().mapToDouble(ProductAssistant::getTotalWeight).sum();

    int totalUnits = entry.getItems().stream().mapToInt(ProductAssistant::getQuantity).sum();

    CountingFormat format = entry.getProduct().getFormat();
    boolean isValid;

    switch (format) {
        case Unit:
            isValid = totalUnits == entry.getTotalAmount();
            break;
        case Weight:
            isValid = Double.compare(totalWeight, entry.getTotalWeight()) == 0;
            break;
        case WeightEndUnit:
            isValid = Double.compare(totalWeight, entry.getTotalWeight()) == 0 && totalUnits == entry.getTotalAmount();
            break;
        default:
            throw new IllegalArgumentException("Formato desconhecido: " + format);
    }

    if (!isValid) {
        throw new ValidationException(format, totalWeight, totalUnits, entry);
    }
}


    @Override
    public int compareTo(ProductEntry arg0) {
        return dateEntry.compareTo(arg0.dateEntry);
    }
    
}
