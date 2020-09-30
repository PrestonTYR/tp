package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's weight in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidWeight(String)}
 */
public class Weight {

    public static final String MESSAGE_CONSTRAINTS =
            "Weight (kg) should only contain positive numbers with 1 decimal place, and it should not be blank";
    public static final String WEIGHT_UNIT = "kg";

    public final String value;

    /**
     * Constructs a {@code weight}.
     *
     * @param weight A valid weight.
     */
    public Weight(String weight) {
        requireNonNull(weight);
        checkArgument(isValidWeight(weight), MESSAGE_CONSTRAINTS);
        this.value = weight;
    }

    /**
     * Returns true if a given string is a valid weight.
     */
    public static boolean isValidWeight(String test) {
        try {
            // check if specified with 1 decimal place
            if (!hasDotAsSecondLastCharacter(test)) {
                return false;
            }

            // check if valid number
            Double weight = Double.parseDouble(test);

            if (weight <= 0) {
                return false;
            }

            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean hasDotAsSecondLastCharacter(String test) {
        if (!test.contains(".")) {
            return false;
        }
        int indexOfDot = test.indexOf(".");
        if (indexOfDot != test.length() - 2) {
            return false;
        }
        return true;
    }


    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Weight // instanceof handles nulls
                && value.equals(((Weight) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
