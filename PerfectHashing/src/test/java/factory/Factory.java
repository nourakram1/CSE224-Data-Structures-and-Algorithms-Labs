package factory;

import util.ListUtils;

import java.util.List;

/**
 * A generic factory interface for creating instances of type {@code S}.
 * <p>
 * Implementations must provide mechanisms to:
 * <ul>
 *   <li>Create a single instance.</li>
 *   <li>Create multiple instances, either in a specified quantity or a random quantity.</li>
 *   <li>Ensure uniqueness or duplication of instances.</li>
 * </ul>
 *
 * @param <S> the type of object this factory creates
 */
public interface Factory<S> {

    /**
     * Creates a single new instance of type {@code S}.
     *
     * @return a new instance
     */
    S createInstance();

    /**
     * Creates a list of new instances of type {@code S}.
     * <p>
     * The number of instances returned is chosen at random by the implementation.
     *
     * @return a {@link List} of newly created instances, size determined at random
     */
    List<S> createInstances();

    /**
     * Creates a list of {@code n} new instances of type {@code S}.
     *
     * @param n the exact number of instances to create
     * @return a {@link List} containing {@code n} newly created instances
     * @throws IllegalArgumentException if {@code n} is negative
     */
    List<S> createInstances(int n);

    /**
     * Creates a list of unique instances of type {@code S}.
     * <p>
     * Internally this calls {@link #createInstances()} (random size) and filters out duplicates.
     *
     * @return a {@link List} of unique instances, size determined at random before filtering
     */
    default List<S> createUniqueInstances() {
        return ListUtils.unique(createInstances());
    }

    /**
     * Creates a list of unique instances of type {@code S} in the specified quantity.
     * <p>
     * Internally this calls {@link #createInstances(int)} and filters out duplicates.
     *
     * @param n the exact number of unique instances desired
     * @return a {@link List} containing up to {@code n} unique instances
     * @throws IllegalArgumentException if {@code n} is negative
     */
    default List<S> createUniqueInstances(int n) {
        if (n < 0)
            throw new IllegalArgumentException("The number of instances cannot be negative");

        return ListUtils.unique(createInstances(n));
    }

    /**
     * Given a list of unique instances, produces a new list containing duplicates.
     * <p>
     * How many duplicates and the duplication strategy (e.g., random selection,
     * fixed pattern) is determined by the implementation.
     *
     * @param uniqueInstances a {@link List} of instances assumed to be unique
     * @return a {@link List} that includes duplicates derived from the input list
     * @throws NullPointerException if {@code uniqueInstances} is null
     */
    List<S> createDuplicateInstances(List<S> uniqueInstances);
}