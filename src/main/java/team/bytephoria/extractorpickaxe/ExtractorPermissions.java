package team.bytephoria.extractorpickaxe;

import team.bytephoria.extractorpickaxe.utils.exception.NonInstantiableClassException;

public final class ExtractorPermissions {

    private ExtractorPermissions() {
        throw new NonInstantiableClassException();
    }

    /**
     * Permission to use the extractor pickaxe command.
     */
    public static final String COMMAND_USE = "extractorpickaxe.command.use";

}
