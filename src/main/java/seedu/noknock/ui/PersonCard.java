package seedu.noknock.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.noknock.model.person.Patient;
import seedu.noknock.model.person.Person;


/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

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
    private HBox nextOfKins;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        if (person instanceof Patient) {
            Patient patient = (Patient) person;
            ic.setText(patient.getIC().toString());
            ward.setText(patient.getWard().toString());
            patient.getTags().stream()
                    .forEach(tag -> tags.getChildren().add(new Label(tag.toString())));
            patient.getNextOfKinList().stream().forEach(
                    nextOfKin -> {
                        nextOfKins.getChildren().add(new Label(nextOfKin.toString()));
                    });
        }
    }
}
