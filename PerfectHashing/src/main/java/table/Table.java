package table;

import java.util.List;

/**
 * A generic Table interface representing a collection of elements of type T.
 * <p>
 * Supports insertion, removal, and containment checks for single and batch operations.
 * Duplicate insertions and removals of non-existent elements have no effect:
 * attempting to insert an existing element or remove a non-existing element leaves the table unchanged
 * and returns {@code false} for single operations, or returns an empty list for batch operations
 * when no elements were actually inserted or removed.
 * </p>
 *
 * @param <T> the type of elements maintained by this table
 */
public interface Table<T> {
    /**
     * Inserts the specified element into the table if it is not already present.
     *
     * @param value the element to be inserted
     * @return {@code true} if the element was not already present and was inserted;
     * {@code false} if the element already existed and was not inserted
     */
    boolean insert(T value);

    /**
     * Removes the specified element from the table if it is present.
     *
     * @param value the element to be removed
     * @return {@code true} if the element existed and was removed;
     * {@code false} if the element did not exist and thus was not removed
     */
    boolean remove(T value);

    /**
     * Checks whether the specified element is contained in the table.
     *
     * @param value the element whose presence is to be tested
     * @return {@code true} if the element exists in the table;
     * {@code false} otherwise
     */
    boolean contains(T value);

    /**
     * Inserts all the elements in the given list into the table.
     * Only elements that are not already present will be added.
     *
     * @param values a list of elements to be inserted
     * @return a list of elements that were actually inserted (i.e., those not already present);
     * returns an empty list if none of the elements were inserted
     */
    default List<T> insertAll(List<T> values) {
        return values.stream().filter(this::insert).toList();
    }

    /**
     * Removes all the elements in the given list from the table.
     * Only elements that are present will be removed.
     *
     * @param values a list of elements to be removed
     * @return a list of elements that were actually removed (i.e., those that existed in the table);
     * returns an empty list if none of the elements were removed
     */
    default List<T> removeAll(List<T> values) {
        return values.stream().filter(this::remove).toList();
    }

    /**
     * Returns a list of all elements currently in the table.
     * <p>
     * The order of elements in the returned list is implementation-dependent.
     * Modifications to the returned list do not affect the contents of the table.
     * </p>
     *
     * @return a list containing all elements in the table
     */
    List<T> getElements();
}