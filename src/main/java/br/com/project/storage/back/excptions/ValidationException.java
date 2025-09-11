package br.com.project.storage.back.excptions;

import br.com.project.storage.back.enums.CountingFormat;
import br.com.project.storage.back.model.ProductEntry;

public class ValidationException extends Exception{

    public ValidationException(String errorMessage) {
        super(errorMessage);
    }

    public ValidationException(CountingFormat format, double totalWeight, int totalUnits, ProductEntry entry) {
        super(String.format("Validação falhou para formato %s: peso calculado=%.2f vs esperado=%.2f; unidades calculadas=%d vs esperadas=%d",
        format,
        totalWeight,
        entry.getTotalWeight(),
        totalUnits,
        entry.getTotalAmount()));
    }

}
