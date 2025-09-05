package team.bytephoria.extractorpickaxe.extractor;

/**
 * Represents the possible outcomes of using or decrementing an extractor item.
 * <p>
 * This enum is used by {@link team.bytephoria.extractorpickaxe.service.ExtractorService} to indicate the result of operations
 * such as decrementing uses or validating an item.
 */
public enum ExtractorUseResult {

    /**
     * The item is not an extractor and cannot be used as one.
     */
    NOT_EXTRACTOR,

    /**
     * The extractor has unlimited uses and cannot be decremented.
     */
    UNLIMITED_USES,

    /**
     * The extractor was consumed/destroyed after the last use.
     */
    CONSUMED,

    /**
     * The extractor was successfully used and its remaining uses were decremented.
     */
    SUCCESS
}
