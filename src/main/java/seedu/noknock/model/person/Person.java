package seedu.noknock.model.person;

import static seedu.noknock.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.noknock.commons.util.ToStringBuilder;

/**
 * Represents a generic person in the system.
 * This is an abstract base class for {@link Patient} and {@link NextOfKin}.
 *
 * <p>Identity is defined by the {@link Name} field. All person instances are
 * guaranteed to have a non-null, validated name. Subclasses may introduce
 * additional fields (e.g., ward, IC, relationship) but this class enforces
 * the core identity contract.</p>
 *
 * <p>Immutability: All fields in this class are immutable. Any extending subclass
 * should preserve immutability wherever possible.</p>
 */
public abstract class Person {
    /** The unique name representing the identity of this person. */
    private final Name name;

    /**
     * Constructs a {@code Person} with the given {@code Name}.
     * The provided name must not be null.
     *
     * @param name a valid, non-null {@link Name} object.
     */
    public Person(Name name) {
        requireAllNonNull(name);
        this.name = name;
    }

    /**
     * Returns the {@link Name} of this person.
     */
    public Name getName() {
        return name;
    }
    /**
     * Returns {@code true} if both persons have the same {@link Name}.
     * This provides a weaker notion of equality, used for detecting potential duplicates
     * without requiring all data fields to match.
     *
     * @param otherPerson the other person to compare against.
     * @return true if both represent the same person by name, false otherwise.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName());
    }

    /**
     * Returns {@code true} if both objects are {@code Person} instances with the same
     * identity fields. This defines a stronger notion of equality and is used for exact
     * matching (e.g., in data structures or unit tests).
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return name.equals(otherPerson.name);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .toString();
    }

}
