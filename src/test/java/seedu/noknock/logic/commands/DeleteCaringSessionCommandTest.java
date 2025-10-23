package seedu.noknock.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.noknock.logic.Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX;
import static seedu.noknock.logic.Messages.MESSAGE_INVALID_SESSION_INDEX;
import static seedu.noknock.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.noknock.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.noknock.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.noknock.testutil.TypicalPatients.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.noknock.commons.core.index.Index;
import seedu.noknock.logic.Messages;
import seedu.noknock.model.Model;
import seedu.noknock.model.ModelManager;
import seedu.noknock.model.UserPrefs;
import seedu.noknock.model.date.Date;
import seedu.noknock.model.date.Time;
import seedu.noknock.model.person.Patient;
import seedu.noknock.model.session.CareType;
import seedu.noknock.model.session.CaringSession;
import seedu.noknock.model.session.Note;


public class DeleteCaringSessionCommandTest {
    private static final CaringSession VALID_SESSION =
            new CaringSession(new CareType("medication"), new Note("Check sugar"),
                    new Date("2025-12-25"), new Time("14:30"));

    @Test
    public void execute_validIndex_success() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Patient targetPatient = model.getFilteredPatientList().get(0);
        List<CaringSession> updatedList = new ArrayList<>(targetPatient.getCaringSessionList());
        updatedList.add(VALID_SESSION);
        Patient updatedPatient = targetPatient.withCaringSessionList(updatedList);
        model.setPatient(targetPatient, updatedPatient);
        model.updateFilteredPatientList(PREDICATE_SHOW_ALL_PERSONS);

        Model updatedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        DeleteCaringSessionCommand command = new DeleteCaringSessionCommand(Index.fromOneBased(1),
                Index.fromOneBased(1));

        String expectedMessage = String.format(DeleteCaringSessionCommand.MESSAGE_DELETE_SUCCESS,
                Messages.formatSession(VALID_SESSION), Messages.formatPatient(updatedPatient));

        assertCommandSuccess(command, model, expectedMessage, updatedModel);
    }

    @Test
    public void execute_invalidPatientIndex_throwsCommandException() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Patient targetPatient = model.getFilteredPatientList().get(0);
        List<CaringSession> updatedList = new ArrayList<>(targetPatient.getCaringSessionList());
        updatedList.add(VALID_SESSION);
        Patient updatedPatient = targetPatient.withCaringSessionList(updatedList);
        model.setPatient(targetPatient, updatedPatient);
        model.updateFilteredPatientList(PREDICATE_SHOW_ALL_PERSONS);
        int outOfBoundsIndex = model.getFilteredPatientList().size() + 1;

        DeleteCaringSessionCommand command =
                new DeleteCaringSessionCommand(Index.fromOneBased(outOfBoundsIndex), Index.fromOneBased(1));

        assertCommandFailure(command, model, MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidSessionIndex_throwsCommandException() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Patient targetPatient = model.getFilteredPatientList().get(0);
        List<CaringSession> updatedList = new ArrayList<>(targetPatient.getCaringSessionList());
        updatedList.add(VALID_SESSION);
        Patient updatedPatient = targetPatient.withCaringSessionList(updatedList);
        model.setPatient(targetPatient, updatedPatient);
        model.updateFilteredPatientList(PREDICATE_SHOW_ALL_PERSONS);
        int outOfBoundsIndex = model.getFilteredPatientList().size() + 1;

        DeleteCaringSessionCommand command =
                new DeleteCaringSessionCommand(Index.fromOneBased(1), Index.fromOneBased(outOfBoundsIndex));

        assertCommandFailure(command, model, MESSAGE_INVALID_SESSION_INDEX);
    }

    @Test
    public void equals() {
        DeleteCaringSessionCommand command1 = new DeleteCaringSessionCommand(Index.fromOneBased(1),
                Index.fromOneBased(1));
        DeleteCaringSessionCommand command2 = new DeleteCaringSessionCommand(Index.fromOneBased(1),
                Index.fromOneBased(1));
        DeleteCaringSessionCommand command3 = new DeleteCaringSessionCommand(Index.fromOneBased(1),
                Index.fromOneBased(2));
        DeleteCaringSessionCommand command4 = new DeleteCaringSessionCommand(Index.fromOneBased(2),
                Index.fromOneBased(1));

        // same object -> true
        assertTrue(command1.equals(command1));

        // same values -> true
        assertTrue(command1.equals(command2));

        // different session index -> false
        assertFalse(command1.equals(command3));

        //different patient index -> false
        assertFalse(command1.equals(command4));

        // different type -> false
        assertFalse(command1.equals(5));

        // null -> false
        assertFalse(command1.equals(null));
    }
}
