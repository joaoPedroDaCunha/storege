package br.com.project.storage.back.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

@Entity
public class AuxiliaryDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Dois campos de texto para receber docType e docInfo vindos do painel
    private String docType;
    private String docInfo;

    @ManyToOne
    @JoinColumn(name = "CodeEntry_id")
    private ProductEntry productEntry;

    // Construtor obrigatório para JPA
    public AuxiliaryDocument() { }

    // Construtor usado pelo JPanel (docType, docInfo)
    public AuxiliaryDocument(String docType, String docInfo) {
        this.docType = docType;
        this.docInfo = docInfo;
    }

    public Long getId() {
        return id;
    }

    public String getDocType() {
        return docType;
    }

    public String getDocInfo() {
        return docInfo;
    }

    public ProductEntry getProductEntry() {
        return productEntry;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public void setDocInfo(String docInfo) {
        this.docInfo = docInfo;
    }

    public void setProductEntry(ProductEntry productEntry) {
        this.productEntry = productEntry;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
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
        AuxiliaryDocument other = (AuxiliaryDocument) obj;
        if (id == null) {
            return other.id == null;
        } else return id.equals(other.id);
    }

    public String getFileName() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getFileName'");
    }
}