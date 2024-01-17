package ygorgarofalo.SpringBeU2W2D2.exceptions;

public class NotFoundException extends RuntimeException {

    public NotFoundException(long id) {
        super("Item with id: " + id + " not found");
    }
}
