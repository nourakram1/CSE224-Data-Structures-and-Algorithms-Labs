package representation;

/**
 * A functional interface for producing a binary representation of an object.
 * <p>
 * For any given implementation class C, all instances of C must produce binary strings
 * of identical length when invoking {@link #getBinaryRepresentation()}.
 * </p>
 *
 * @implNote Implementations should ensure that padding or grouping is
 * added consistently so that every returned string for a given class has
 * the same number of characters.
 */
@FunctionalInterface
public interface BinaryRepresentable {
    /**
     * Generates a binary (base-2) string representation of this object.
     * <p>
     * The returned string must consist only of the characters '0' and '1',
     * and for a given implementation class, every instance must return
     * a string of the same fixed length.
     * </p>
     *
     * @return a {@code String} of '0's and '1's representing this object's
     * value in binary form, with consistent length across instances
     */
    String getBinaryRepresentation();
}