package seedu.noknock.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.noknock.commons.exceptions.IllegalValueException;
import seedu.noknock.model.person.IC;
import seedu.noknock.model.person.Name;
import seedu.noknock.model.person.NextOfKin;
import seedu.noknock.model.person.Patient;
import seedu.noknock.model.person.Ward;
import seedu.noknock.model.session.CaringSession;
import seedu.noknock.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Patient}.
 */
class JsonAdaptedPatient {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String ward;
    private final String ic;
    private final List<JsonAdaptedNextOfKin> nextOfKins = new ArrayList<>();
    private final List<JsonAdaptedTag> tags = new ArrayList<>();
    private final List<JsonAdaptedCaringSession> caringSessions = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPatient} with the given patient details.
     */
    @JsonCreator
    public JsonAdaptedPatient(@JsonProperty("name") String name, @JsonProperty("ward") String ward,
                              @JsonProperty("ic") String ic,
                              @JsonProperty("tags") List<JsonAdaptedTag> tags,
                              @JsonProperty("nextOfKins") List<JsonAdaptedNextOfKin> nextOfKins,
                              @JsonProperty("sessions") List<JsonAdaptedCaringSession> sessions) {
        this.name = name;
        this.ward = ward;
        this.ic = ic;
        if (tags != null) {
            this.tags.addAll(tags);
        }
        if (nextOfKins != null) {
            this.nextOfKins.addAll(nextOfKins);
        }
        if (sessions != null) {
            this.caringSessions.addAll(sessions);
        }
    }

    /**
     * Converts a given {@code Patient} into this class for Jackson use.
     */
    public JsonAdaptedPatient(Patient source) {
        name = source.getName().fullName;
        ic = source.getIC().toString();
        ward = source.getWard().toString();
        tags.addAll(source.getTags().stream()
            .map(JsonAdaptedTag::new)
            .toList());
        nextOfKins.addAll(source.getNextOfKinList().stream()
            .map(JsonAdaptedNextOfKin::new)
            .toList());
        caringSessions.addAll(source.getCaringSessionList().stream()
            .map(JsonAdaptedCaringSession::new)
            .toList());
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Patient toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tags) {
            personTags.add(tag.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (ward == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Ward.class.getSimpleName()));
        }
        if (!Ward.isValidWard(ward)) {
            throw new IllegalValueException(Ward.MESSAGE_CONSTRAINTS);
        }
        final Ward modelWard = new Ward(ward);

        if (ic == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, IC.class.getSimpleName()));
        }
        if (!IC.isValidIC(ic)) {
            throw new IllegalValueException(IC.MESSAGE_CONSTRAINTS);
        }
        final IC modelIC = new IC(ic);

        final Set<Tag> modelTags = new HashSet<>(personTags);

        final List<NextOfKin> personNextOfKins = new ArrayList<>();
        for (JsonAdaptedNextOfKin nok : nextOfKins) {
            personNextOfKins.add(nok.toModelType());
        }
        final List<CaringSession> modelCaringSessions = new ArrayList<>();
        for (JsonAdaptedCaringSession nok : caringSessions) {
            modelCaringSessions.add(nok.toModelType());
        }
        return new Patient(modelName, modelWard, modelIC, modelTags)
                .withNextOfKinList(personNextOfKins)
                .withCaringSessionList(modelCaringSessions);
    }

}
