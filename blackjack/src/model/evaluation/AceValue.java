package model.evaluation;

/**
 * <b>
 * Enumeration that fix the possible value of an ace.
 * </b>
 *
 * <p>
 * The ace can have two value, 1 or 11.
 * The value 11 is the default value.
 * </p>
 */
public enum AceValue {
    /**
     * The value of the ace is 1.
     */
    ONE(1),
    /**
     * The value of the ace is 11.
     */
    ELEVEN(11);

    /**
     * The value of the ace.
     */
    private final int value;

    /**
     * Creates a new AceValue with the given value.
     *
     * @param value The value of the ace.
     */
    AceValue(int value) {
        this.value = value;
    }

    /**
     * Returns the value of the ace.
     *
     * @return the value of the ace.
     */
    public int getValue() {
        return this.value;
    }
}
