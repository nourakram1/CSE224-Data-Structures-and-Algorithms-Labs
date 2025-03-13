package sort.lang;

import lombok.Getter;
import lombok.Setter;

/**
 * A wrapper class for {@link Number} that implements {@link Comparable}.
 * This class allows numbers of different types to be compared using
 * their double value representation.
 * <p>
 * It extends {@link Number} and overrides its methods to delegate calls
 * to the underlying {@code Number} instance.
 * </p>
 * <p>
 * The comparison is performed using {@link Math#signum(double)}, ensuring
 * consistent ordering based on the numerical value.
 * </p>
 */
@Getter
@Setter
public class ComparableNumber extends Number implements Comparable<ComparableNumber> {

    private Number number;

    public ComparableNumber(Number number) {
        this.number = number;
    }

    /**
     * Compares this {@code ComparableNumber} with another based on their
     * double value representation.
     *
     * @param o the {@code ComparableNumber} to compare with
     * @return a negative integer, zero, or a positive integer as this number
     *         is less than, equal to, or greater than the specified number
     */
    public int compareTo(ComparableNumber o) {
        return (int) Math.signum(number.doubleValue() - o.getNumber().doubleValue());
    }

    @Override
    public int intValue() {
        return number.intValue();
    }

    @Override
    public long longValue() {
        return number.longValue();
    }

    @Override
    public float floatValue() {
        return number.floatValue();
    }

    @Override
    public double doubleValue() {
        return number.doubleValue();
    }

    @Override
    public String toString() {
        return String.valueOf(number);
    }
}
