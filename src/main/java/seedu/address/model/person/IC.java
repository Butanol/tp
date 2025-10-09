package seedu.address.model.person;

public class IC {
    public final String ic;

    public IC(String ic) {
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

        if (!(other instanceof IC)) {
            return false;
        }

        IC otherIc = (IC) other;
        return ic.equals(otherIc.ic);
    }
}
