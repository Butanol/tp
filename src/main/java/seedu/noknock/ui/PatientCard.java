package seedu.noknock.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.noknock.model.person.NextOfKin;
import seedu.noknock.model.person.Patient;
import seedu.noknock.model.person.Person;
import seedu.noknock.model.session.CaringSession;

/**
 * An UI component that displays information of a {@code Patient}.
 */
public class PatientCard extends UiPart<Region> {

    private static final String FXML = "PatientListCard.fxml";

    public final Person person;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label ic;
    @FXML
    private Label ward;
    @FXML
    private VBox nextOfKins;
    @FXML
    private FlowPane tags;
    @FXML
    private FlowPane sessions;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PatientCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        if (person instanceof Patient) {
            Patient patient = (Patient) person;
            ic.setText(patient.getIC().toString());
            ward.setText(patient.getWard().toString());
            patient.getTags()
                .forEach(tag -> tags.getChildren().add(new Label(tag.toString())));

            // Display NOKs as numbered list
            int nokIndex = 1;
            for (NextOfKin nok : patient.getNextOfKinList()) {
                Label nokLabel = new Label(String.format("%d. %s (%s) - %s",
                    nokIndex++,
                    nok.getName().fullName,
                    nok.getRelationship().toString(),
                    nok.getPhone().toString()));
                nokLabel.setWrapText(true);
                nextOfKins.getChildren().add(nokLabel);
            }
            int sessionIndex = 1;
            for (CaringSession session : patient.getCaringSessionList()) {
                Label sessionLabel = new Label(String.format("%d. Date: %s Time: %s Type: %s (Notes: %s) ",
                        sessionIndex++,
                        session.getDate(),
                        session.getTime(),
                        session.getCareType(),
                        session.getNote()));
                sessionLabel.setWrapText(true);
                sessions.getChildren().add(sessionLabel);
            }
        }
    }
}
