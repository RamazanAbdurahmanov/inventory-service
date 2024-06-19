package az.efiqa.inventory_service.exceptions;

public class ItemAlreadyExistsException extends RuntimeException {
    public ItemAlreadyExistsException(String message) {
        super(message);

    }
}
