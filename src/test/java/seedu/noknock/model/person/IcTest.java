package seedu.noknock.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class IcTest {

    @Test
    public void constructor_validIC_success() {
        IC ic = new IC("S1234567A");
        assertEquals("S1234567A", ic.toString());
    }

    @Test
    public void isValidIC_validIC_returnsTrue() {
        assertTrue(IC.isValidIC("S1234567A"));
        assertTrue(IC.isValidIC("T7654321Z"));
        assertTrue(IC.isValidIC("S0000000K"));
    }

    @Test
    public void isValidIC_invalidIC_returnsFalse() {
        // empty or whitespace
        assertFalse(IC.isValidIC(""));
        assertFalse(IC.isValidIC(" "));

        // wrong starting letter
        assertFalse(IC.isValidIC("A1234567A"));
        assertFalse(IC.isValidIC("B1234567A"));

        // wrong format (missing pieces)
        assertFalse(IC.isValidIC("S123456A")); // only 6 digits
        assertFalse(IC.isValidIC("S12345678A")); // 8 digits
        assertFalse(IC.isValidIC("S1234567")); // missing alphabet
        assertFalse(IC.isValidIC("1234567A")); // missing leading S/T

        // wrong ending
        assertFalse(IC.isValidIC("S1234567@"));
        assertFalse(IC.isValidIC("S1234567a")); // lowercase alphabet not allowed
    }

    @Test
    public void equals() {
        IC ic1 = new IC("S1234567A");
        IC ic2 = new IC("S1234567A");
        IC ic3 = new IC("T7654321Z");

        // same values -> true
        assertTrue(ic1.equals(ic2));

        // same object -> true
        assertTrue(ic1.equals(ic1));

        // different values -> false
        assertFalse(ic1.equals(ic3));

        // null -> false
        assertFalse(ic1.equals(null));

        // different type -> false
        assertFalse(ic1.equals("S1234567A"));
    }

    @Test
    public void toString_correctOutput() {
        IC ic = new IC("T7654321Z");
        assertEquals("T7654321Z", ic.toString());
    }
}
