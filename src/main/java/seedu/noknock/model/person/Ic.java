package seedu.noknock.model.person;

public class Ic {
    public final String ic;

    public Ic(String ic) {
        this.ic = ic;
    }

    @Override
    public String toString() {
        return ic;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Ic)) {
            return false;
        }

        Ic otherIc = (Ic) other;
        return ic.equals(otherIc.ic);
    }
}
