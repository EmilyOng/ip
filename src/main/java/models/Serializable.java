package models;

import exceptions.DukeException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

abstract  public class Serializable<T> {
    private static final String formatter = " \\| ";
    private final String serialized;
    private final String[] originalData;

    private static final String ERROR_MATCHING_SERIALIZABLE_REGEX = "Regex for serialized string does not match!";

    public Serializable(String[] data) {
        this.originalData = data;
        this.serialized = String.join(Serializable.formatter, data);
    }

    public Serializable(String serializedString, Pattern regexMatch) throws DukeException {
        Matcher matcher = regexMatch.matcher(serializedString);
        if (!matcher.find()) {
            throw new DukeException(Serializable.ERROR_MATCHING_SERIALIZABLE_REGEX);
        }
        this.serialized = serializedString;
        this.originalData = serializedString.split(Serializable.formatter);
    }

    /**
     * Supports the deserialization operation of the serializable object
     * @return The deserialized object
     */
    abstract public T deserialize();

    /**
     * Returns the original data passed into the Serializable constructor ie the inverse
     * operation of serialization
     * @return Original data passed into the constructor
     */
    protected String[] get() {
        return this.originalData;
    }

    @Override
    public String toString() {
        return this.serialized;
    }
}
