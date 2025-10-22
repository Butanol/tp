package seedu.noknock.model;

import javafx.collections.ObservableList;
import seedu.noknock.model.person.Patient;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the patients list.
     * This list will not contain any duplicate patients.
     */
    ObservableList<Patient> getPatientList();

}
