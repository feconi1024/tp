package nusconnect.model;

import static nusconnect.logic.commands.CommandTestUtil.VALID_MAJOR_BOB;
import static nusconnect.logic.commands.CommandTestUtil.VALID_MODULE_CS2103T;
import static nusconnect.testutil.Assert.assertThrows;
import static nusconnect.testutil.TypicalPersons.ALICE;
import static nusconnect.testutil.TypicalPersons.getTypicalAddressBook;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import nusconnect.model.group.Group;
import nusconnect.model.person.Person;
import nusconnect.model.person.exceptions.DuplicatePersonException;
import nusconnect.testutil.PersonBuilder;
import nusconnect.testutil.TypicalGroups;

public class AddressBookTest {

    private final AddressBook addressBook = new AddressBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        AddressBook newData = getTypicalAddressBook();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Person editedAlice = new PersonBuilder(ALICE).withMajor(VALID_MAJOR_BOB).withModules(VALID_MODULE_CS2103T)
                .build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        AddressBookStub newData = new AddressBookStub(newPersons);

        assertThrows(DuplicatePersonException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        addressBook.addPerson(ALICE);
        assertTrue(addressBook.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withMajor(VALID_MAJOR_BOB).withModules(VALID_MODULE_CS2103T)
                .build();
        assertTrue(addressBook.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getPersonList().remove(0));
    }

    @Test
    public void toStringMethod() {
        String expected = AddressBook.class.getCanonicalName() + "{persons=" + addressBook.getPersonList() + "}";
        assertEquals(expected, addressBook.toString());
    }

    @Test
    public void hasGroup_nullGroup_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasGroup(null));
    }

    @Test
    public void hasGroup_groupNotInAddressBook_returnsFalse() {
        Group group = TypicalGroups.GROUP_CS2100;
        assertFalse(addressBook.hasGroup(group));
    }

    @Test
    public void hasGroup_groupInAddressBook_returnsTrue() {
        Group group = TypicalGroups.GROUP_CS2100;
        addressBook.addGroup(group);
        assertTrue(addressBook.hasGroup(group));
    }

    @Test
    public void removeGroup_existingGroup_success() {
        Group group = TypicalGroups.GROUP_CS2100;
        addressBook.addGroup(group);
        assertTrue(addressBook.hasGroup(group));

        addressBook.removeGroup(group);
        assertFalse(addressBook.hasGroup(group));
        assertEquals(0, addressBook.getGroupList().size());
    }

    @Test
    public void addPersonToGroup_validPersonAndGroup_success() {
        Person person = ALICE;
        Group group = TypicalGroups.GROUP_CS2100;

        addressBook.addPerson(person);
        addressBook.addGroup(group);

        addressBook.addPersonToGroup(person, group);

        Group actualGroup = addressBook.getGroupList().get(0);
        assertTrue(actualGroup.hasMember(person));
    }

    /**
     * A stub ReadOnlyAddressBook whose persons list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();
        private final ObservableList<Group> groups = FXCollections.observableArrayList();

        AddressBookStub(Collection<Person> persons) {
            this.persons.setAll(persons);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }

        @Override
        public ObservableList<Group> getGroupList() {
            return groups;
        }
    }

}
