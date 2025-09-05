package team.bytephoria.extractorpickaxe.utils.exception;

public final class NonInstantiableClassException extends UnsupportedOperationException {

    public NonInstantiableClassException() {
        super("This class cannot be instantiated.");
    }

}