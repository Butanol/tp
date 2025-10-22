package seedu.noknock.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.noknock.commons.exceptions.IllegalValueException;
import seedu.noknock.model.date.Date;
import seedu.noknock.model.date.Time;
import seedu.noknock.model.session.CareType;
import seedu.noknock.model.session.CaringSession;
import seedu.noknock.model.session.Note;

/**
 * Jackson-friendly version of {@link CaringSession}
 */
public class JsonAdaptedCaringSession {
    private final String date;
    private final String time;
    private final String careType;
    private final String note;
    /**
     * Constructs a {@code JsonAdaptedNextOfKin} with the given next-of-kin details.
     */
    @JsonCreator
    public JsonAdaptedCaringSession(@JsonProperty("date") String date,
                                @JsonProperty("time") String time,
                                @JsonProperty("careType") String careType,
                                    @JsonProperty("note") String note) {
        this.date = date;
        this.time = time;
        this.careType = careType;
        this.note = note;
    }

    /**
     * Converts a given {@code JsonAdaptedCaringSession} into this class for Jackson use.
     */
    public JsonAdaptedCaringSession(CaringSession source) {
        date = source.getDate().toString();
        time = source.getTime().toString();
        careType = source.getCareType().toString();
        note = source.getNote().toString();
    }
    /**
     * Converts this Jackson-friendly adapted next-of-kin object into the model's
     * {@code JsonAdaptedCaringSession} object.
     * @throws IllegalValueException if there were any data constraints violated in the adapted caring session.
     */
    public CaringSession toModelType() throws IllegalValueException {
        if (date == null || !Date.isValidDate(date)) {
            throw new IllegalValueException(Date.MESSAGE_CONSTRAINTS);
        }
        final Date modelDate = new Date(date);
        if (time == null || !Time.isValidTime(time)) {
            throw new IllegalValueException(Time.MESSAGE_CONSTRAINTS);
        }
        final Time modelTime = new Time(time);
        if (careType == null || !CareType.isValidCareType(careType)) {
            throw new IllegalValueException(CareType.MESSAGE_CONSTRAINTS);
        }
        final CareType modelCareType = new CareType(careType);
        if (note == null || !Note.isValidNote(note)) {
            throw new IllegalValueException(Note.MESSAGE_CONSTRAINTS);
        }
        final Note modelNote = new Note(note);
        return new CaringSession(modelCareType, modelNote, modelDate, modelTime);
    }
}
