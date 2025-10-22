package seedu.noknock.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class WardTest {

    @Test
    public void constructor_validWard_success() {
        Ward ward = new Ward("2A");
        assertEquals("2A", ward.getRoom());
    }

    @Test
    public void isValidWard_validWard_returnsTrue() {
        assertTrue(Ward.isValidWard("2A"));
        assertTrue(Ward.isValidWard("12B"));
        assertTrue(Ward.isValidWard("99Z"));
    }

    @Test
    public void isValidWard_invalidWard_returnsFalse() {
        // empty or null
        assertFalse(Ward.isValidWard(""));
        assertFalse(Ward.isValidWard(" "));

        // missing alphabet
        assertFalse(Ward.isValidWard("2"));
        assertFalse(Ward.isValidWard("12"));

        // missing number
        assertFalse(Ward.isValidWard("A"));
        assertFalse(Ward.isValidWard("AA"));

        // wrong format
        assertFalse(Ward.isValidWard("A2"));
        assertFalse(Ward.isValidWard("2A1"));
        assertFalse(Ward.isValidWard("0A"));
        assertFalse(Ward.isValidWard("-2A"));
    }

    @Test
    public void equals() {
        Ward ward1 = new Ward("3B");
        Ward ward2 = new Ward("3B");
        Ward ward3 = new Ward("4C");

        // same values -> true
        assertTrue(ward1.equals(ward2));

        // same object -> true
        assertTrue(ward1.equals(ward1));

        // different values -> false
        assertFalse(ward1.equals(ward3));

        // null → false
        assertFalse(ward1.equals(null));

        // different type -> false
        assertFalse(ward1.equals("3B"));
    }

    @Test
    public void toString_correctOutput() {
        Ward ward = new Ward("7D");
        assertEquals("7D", ward.toString());
    }
}
