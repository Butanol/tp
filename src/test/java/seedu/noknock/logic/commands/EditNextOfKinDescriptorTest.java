package seedu.noknock.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.noknock.logic.commands.CommandTestUtil.DESC_DAUGHTER;
import static seedu.noknock.logic.commands.CommandTestUtil.DESC_GRANDPA;
import static seedu.noknock.logic.commands.CommandTestUtil.VALID_NAME_GRANDPA;
import static seedu.noknock.logic.commands.CommandTestUtil.VALID_PHONE_GRANDPA;
import static seedu.noknock.logic.commands.CommandTestUtil.VALID_RELATION_GRANDPA;

import org.junit.jupiter.api.Test;

import seedu.noknock.testutil.EditNextOfKinDescriptorBuilder;




public class EditNextOfKinDescriptorTest {
    @Test
    public void equals() {
        // same values -> returns true
        EditNextOfKinCommand.EditNextOfKinDescriptor descriptorWithSameValues = new EditNextOfKinCommand
                .EditNextOfKinDescriptor(DESC_DAUGHTER);
        assertEquals(DESC_DAUGHTER, descriptorWithSameValues);

        // same object -> returns true
        assertEquals(DESC_DAUGHTER, DESC_DAUGHTER);

        // null -> returns false
        assertFalse(DESC_DAUGHTER.equals(null));

        // different types -> returns false
        assertFalse(DESC_DAUGHTER.equals(5));

        // different values -> returns false
        assertFalse(DESC_DAUGHTER.equals(DESC_GRANDPA));

        // different name -> returns false
        EditNextOfKinCommand.EditNextOfKinDescriptor editedDaughter = new EditNextOfKinDescriptorBuilder(DESC_DAUGHTER)
                .withName(VALID_NAME_GRANDPA).build();
        assertFalse(DESC_DAUGHTER.equals(editedDaughter));

        // different phone -> returns false
        editedDaughter = new EditNextOfKinDescriptorBuilder(DESC_DAUGHTER).withPhone(VALID_PHONE_GRANDPA).build();
        assertFalse(DESC_DAUGHTER.equals(editedDaughter));

        // different relationship -> returns false
        editedDaughter = new EditNextOfKinDescriptorBuilder(DESC_DAUGHTER)
                .withRelationship(VALID_RELATION_GRANDPA).build();
        assertFalse(DESC_DAUGHTER.equals(editedDaughter));
    }

    @Test
    public void toStringMethod() {
        EditNextOfKinCommand.EditNextOfKinDescriptor editNextOfKinDescriptor = new EditNextOfKinCommand
                .EditNextOfKinDescriptor();
        String expected = EditNextOfKinCommand.EditNextOfKinDescriptor.class.getCanonicalName() + "{name="
                + editNextOfKinDescriptor.getName().orElse(null) + ", phone="
                + editNextOfKinDescriptor.getPhone().orElse(null) + ", relationship="
                + editNextOfKinDescriptor.getRelationship().orElse(null) + "}";
        assertEquals(expected, editNextOfKinDescriptor.toString());
    }

}
