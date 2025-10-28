package br.com.project.storage.back.excptions;

public class ProductMismatchException extends RuntimeException{

    public ProductMismatchException(String string) {
        super(string);
    }

}
