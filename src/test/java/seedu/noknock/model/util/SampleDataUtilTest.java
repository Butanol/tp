package seedu.noknock.model.util;

import org.junit.jupiter.api.Test;
import seedu.noknock.model.ReadOnlyAddressBook;
import seedu.noknock.model.person.Patient;
import seedu.noknock.testutil.AddressBookBuilder;
import seedu.noknock.testutil.PatientBuilder;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SampleDataUtilTest {
    /*
            new Patient(new Name("Alex Yeoh"), new Ward("2A"), new IC("S1234567A"),
    getTagSet("VIP", "Diabetic")),
            new Patient(new Name("Bernice Yu"), new Ward("3B"), new IC("T1234567B"),
    getTagSet("Allergic")),
            new Patient(new Name("Charlotte Oliveiro"), new Ward("2A"), new IC("S8654256A"),
    getTagSet("PostOp", "Dementia")),
            new Patient(new Name("David Li"), new Ward("4C"), new IC("S3124567A"),
    getTagSet("Isolation")),
            new Patient(new Name("Irfan Ibrahim"), new Ward("1B"), new IC("S1235767A"),
    getTagSet("Critical")),
            new Patient(new Name("Roy Balakrishnan"), new Ward("2B"), new IC("S3534657A"),
                new HashSet<>());
     */
    Patient Alex = new PatientBuilder().withName("Alex Yeoh").withWard("2A").withIC("S1234567A").withTags("VIP", "Diabetic").build();
    Patient Bernice = new PatientBuilder().withName("Bernice Yu").withWard("3B").withIC("T1234567B").withTags("Allergic").build();
    Patient Charlotte = new PatientBuilder().withName("Charlotte Oliveiro").withWard("2A").withIC("S8654256A").withTags("PostOp", "Dementia").build();
    Patient David = new PatientBuilder().withName("David Li").withWard("4C").withIC("S3124567A").withTags("Isolation").build();
    Patient Irfan = new PatientBuilder().withName("Irfan Ibrahim").withWard("1B").withIC("S1235767A").withTags("Critical").build();
    Patient Roy = new PatientBuilder().withName("Roy Balakrishnan").withWard("2B").withIC("S3534657A").build();
    Patient[] expectedSamplePatient = new Patient[] { Alex, Bernice, Charlotte, David, Irfan, Roy};

    @Test
    public void testSamplePatients() {
        Patient[] arr = SampleDataUtil.getSamplePatients();
        for (int i = 0; i < 6; i++) {
            assertEquals(arr[i], expectedSamplePatient[i]);
        }
    }

    @Test
    public void testSampleAddressBook() {
        AddressBookBuilder abBuilder = new AddressBookBuilder();
        for (Patient p: expectedSamplePatient) {
            abBuilder = abBuilder.withPatient(p);
        }
        ReadOnlyAddressBook expectedAb = abBuilder.build();
        assertEquals(expectedAb, SampleDataUtil.getSampleAddressBook());
    }
}
