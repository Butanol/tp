package seedu.noknock.logic.commands;

import seedu.noknock.commons.core.index.Index;
import seedu.noknock.commons.util.CollectionUtil;
import seedu.noknock.commons.util.ToStringBuilder;
import seedu.noknock.logic.Messages;
import seedu.noknock.logic.commands.exceptions.CommandException;
import seedu.noknock.model.Model;
import seedu.noknock.model.person.Name;
import seedu.noknock.model.person.NextOfKin;
import seedu.noknock.model.person.Patient;
import seedu.noknock.model.person.Phone;
import seedu.noknock.model.person.Relationship;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static java.util.Objects.requireNonNull;
import static java.util.Optional.ofNullable;
import static seedu.noknock.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.noknock.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.noknock.logic.parser.CliSyntax.PREFIX_RELATIONSHIP;

/**
 * Adds a next of kin to an existing patient.
 */
public class EditNextOfKinCommand extends Command {

    public static final String COMMAND_WORD = "edit-nok";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the NOK of patient identified "
        + "by the index number used in the displayed patient list. "
        + "Existing values will be overwritten by the input values.\n"
        + "Parameters: PATIENT_INDEX (must be a positive integer) "
        + "Parameters: NOK_INDEX (must be a positive integer) "
        + "[" + PREFIX_NAME + "NAME]"
        + "[" + PREFIX_PHONE + "PHONE]"
        + "[" + PREFIX_RELATIONSHIP + "RELATIONSHIP]\n"
        + "Example: " + COMMAND_WORD + " 1 2 "
        + PREFIX_NAME + "Jane Doe "
        + PREFIX_PHONE + "98765432 "
        + PREFIX_RELATIONSHIP + "Mother";

    public static final String MESSAGE_EDIT_NOK_SUCCESS = "Edited NextOfKin: %1$s of Patient: %2$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";

    private final Index patientIndex;
    private final Index nokIndex;
    private final EditNokDescriptor editNokDescriptor;

    /**
     * Creates an AddNextOfKinCommand to add the specified next of kin to a patient.
     *
     * @param patientIndex Index of the patient in the filtered patient list.
     * @param nokIndex Index of the NOK in the NOK List
     * @param editNokDescriptor     Edited Next of Kin.
     */
    public EditNextOfKinCommand(Index patientIndex, Index nokIndex, EditNokDescriptor editNokDescriptor) {
        requireNonNull(patientIndex);
        requireNonNull(editNokDescriptor);
        this.patientIndex = patientIndex;
        this.nokIndex = nokIndex;
        this.editNokDescriptor = new EditNokDescriptor(editNokDescriptor);
    }

    private static NextOfKin createEditedNok(NextOfKin nokToEdit, EditNokDescriptor editNokDescriptor) {
        assert nokToEdit != null;

        Name updatedName = editNokDescriptor.getName().orElse(nokToEdit.getName());
        Phone updatedPhone = editNokDescriptor.getPhone().orElse(nokToEdit.getPhone());
        Relationship updatedRelationship = editNokDescriptor.getRelationship().orElse(nokToEdit.getRelationship());

        return new NextOfKin(updatedName, updatedPhone, updatedRelationship);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Patient> patientList = model.getFilteredPersonList();

        if (patientIndex.getZeroBased() >= patientList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Patient patient = patientList.get(patientIndex.getZeroBased());
        List<NextOfKin> nokList = patient.getNextOfKinList();

        if (nokIndex.getZeroBased() >= nokList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_NOK_DISPLAYED_INDEX);
        }

        NextOfKin nokToEdit = nokList.get(nokIndex.getZeroBased());
        NextOfKin editedNok = createEditedNok(nokToEdit, editNokDescriptor);
        nokList.set(nokIndex.getZeroBased(), editedNok);

        return new CommandResult(String.format(MESSAGE_EDIT_NOK_SUCCESS,
            Messages.format(editedNok), Messages.format(patient)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof EditNextOfKinCommand otherAddCommand)) {
            return false;
        }
        return patientIndex.equals(otherAddCommand.patientIndex)
            && editNokDescriptor.equals(otherAddCommand.editNokDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .add("patientIndex", patientIndex)
            .add("editNokDescriptor", editNokDescriptor)
            .toString();
    }
    public static class EditNokDescriptor {
        private Name name;
        private Phone phone;
        private Relationship relationship;

        public EditNokDescriptor() {
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditNokDescriptor(EditNokDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setRelationship(toCopy.relationship);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, relationship);
        }

        public Optional<Name> getName() {
            return ofNullable(name);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Phone> getPhone() {
            return ofNullable(phone);
        }

        public void setPhone(Phone phone) {
            this.phone = phone;
        }
        public Optional<Relationship> getRelationship() {
            return ofNullable(relationship);
        }

        public void setRelationship(Relationship relationship) {
            this.relationship = relationship;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditNokDescriptor otherEditNokDescriptor)) {
                return false;
            }

            return Objects.equals(name, otherEditNokDescriptor.name)
                    && Objects.equals(phone, otherEditNokDescriptor.phone)
                    && Objects.equals(relationship, otherEditNokDescriptor.relationship);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("name", name)
                    .add("phone", phone)
                    .add("relationship", relationship)
                    .toString();
        }
    }
}
