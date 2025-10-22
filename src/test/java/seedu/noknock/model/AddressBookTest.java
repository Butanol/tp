package seedu.noknock.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.noknock.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.noknock.testutil.Assert.assertThrows;
import static seedu.noknock.testutil.TypicalPatients.ALICE;
import static seedu.noknock.testutil.TypicalPatients.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.noknock.model.person.Patient;
import seedu.noknock.model.person.exceptions.DuplicatePatientException;
import seedu.noknock.testutil.PatientBuilder;

public class AddressBookTest {

    private final AddressBook addressBook = new AddressBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getPatientList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        AddressBook newData = getTypicalAddressBook();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    @Test
    public void resetData_withDuplicatePatients_throwsDuplicatePatientException() {
        // Two patients with the same identity fields
        Patient editedAlice = new PatientBuilder(ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Patient> newPatients = Arrays.asList(ALICE, editedAlice);
        AddressBookStub newData = new AddressBookStub(newPatients);

        assertThrows(DuplicatePatientException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasPatient_nullPatient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasPatient(null));
    }

    @Test
    public void hasPatient_patientNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasPatient(ALICE));
    }

    @Test
    public void hasPatient_patientInAddressBook_returnsTrue() {
        addressBook.addPatient(ALICE);
        assertTrue(addressBook.hasPatient(ALICE));
    }

    @Test
    public void hasPatient_patientWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addPatient(ALICE);
        Patient editedAlice = new PatientBuilder(ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(addressBook.hasPatient(editedAlice));
    }

    @Test
    public void getPatientList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getPatientList().remove(0));
    }

    @Test
    public void toStringMethod() {
        String expected = AddressBook.class.getCanonicalName() + "{patients=" + addressBook.getPatientList() + "}";
        assertEquals(expected, addressBook.toString());
    }

    /**
     * A stub ReadOnlyAddressBook whose patients list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Patient> patients = FXCollections.observableArrayList();

        AddressBookStub(Collection<Patient> patients) {
            this.patients.setAll(patients);
        }

        @Override
        public ObservableList<Patient> getPatientList() {
            return patients;
        }
    }

}
