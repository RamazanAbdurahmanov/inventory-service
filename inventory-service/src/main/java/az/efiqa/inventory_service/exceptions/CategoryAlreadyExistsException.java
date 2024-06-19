package az.efiqa.inventory_service.exceptions;

public class CategoryAlreadyExistsException extends RuntimeException{
    public CategoryAlreadyExistsException(String message) {
        super(message);
    }
}
