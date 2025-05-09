package nusconnect.logic.commands;

import static nusconnect.logic.parser.CliSyntax.PREFIX_ALIAS;
import static nusconnect.logic.parser.CliSyntax.PREFIX_EMAIL;
import static nusconnect.logic.parser.CliSyntax.PREFIX_MAJOR;
import static nusconnect.logic.parser.CliSyntax.PREFIX_MODULE;
import static nusconnect.logic.parser.CliSyntax.PREFIX_NAME;
import static nusconnect.logic.parser.CliSyntax.PREFIX_NOTE;
import static nusconnect.logic.parser.CliSyntax.PREFIX_PHONE;
import static nusconnect.logic.parser.CliSyntax.PREFIX_TELEGRAM;
import static nusconnect.logic.parser.CliSyntax.PREFIX_WEBSITE;
import static nusconnect.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import nusconnect.commons.core.index.Index;
import nusconnect.logic.commands.exceptions.CommandException;
import nusconnect.model.AddressBook;
import nusconnect.model.Model;
import nusconnect.model.person.NameContainsKeywordsPredicate;
import nusconnect.model.person.Person;
import nusconnect.testutil.EditPersonDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "88888888";
    public static final String VALID_PHONE_BOB = "99999999";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_MODULE_CS2103T = "CS2103T";
    public static final String VALID_MODULE_CS2106 = "CS2106";
    public static final String VALID_ALIAS_AMY = "AmyBeeAlias";
    public static final String VALID_ALIAS_BOB = "BobChooAlias";
    public static final String VALID_MAJOR_ANY = "Computer Science";
    public static final String VALID_MAJOR_BOB = "Information Security";
    public static final String VALID_NOTE_AMY = "Likes ice cream.";
    public static final String VALID_NOTE_BOB = "Enjoys long walks.";
    public static final String VALID_TELEGRAM_AMY = "@amybee";
    public static final String VALID_TELEGRAM_BOB = "@bobchoo";
    public static final String VALID_WEBSITE_AMY = "https://amy.example.com";
    public static final String VALID_WEBSITE_BOB = "https://bob.example.com";
    public static final String VALID_GROUP_NAME_CS2100 = "CS2100 Team";
    public static final String VALID_GROUP_NAME_CCA = "Grey hats";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String MODULE_DESC_FRIEND = " " + PREFIX_MODULE + VALID_MODULE_CS2106;
    public static final String MODULE_DESC_HUSBAND = " " + PREFIX_MODULE + VALID_MODULE_CS2103T;
    public static final String ALIAS_DESC_AMY = " " + PREFIX_ALIAS + VALID_ALIAS_AMY;
    public static final String ALIAS_DESC_BOB = " " + PREFIX_ALIAS + VALID_ALIAS_BOB;
    public static final String MAJOR_DESC_ANY = " " + PREFIX_MAJOR + VALID_MAJOR_ANY;
    public static final String MAJOR_DESC_BOB = " " + PREFIX_MAJOR + VALID_MAJOR_BOB;
    public static final String NOTE_DESC_AMY = " " + PREFIX_NOTE + VALID_NOTE_AMY;
    public static final String NOTE_DESC_BOB = " " + PREFIX_NOTE + VALID_NOTE_BOB;
    public static final String TELEGRAM_DESC_AMY = " " + PREFIX_TELEGRAM + VALID_TELEGRAM_AMY;
    public static final String TELEGRAM_DESC_BOB = " " + PREFIX_TELEGRAM + VALID_TELEGRAM_BOB;
    public static final String WEBSITE_DESC_AMY = " " + PREFIX_WEBSITE + VALID_WEBSITE_AMY;
    public static final String WEBSITE_DESC_BOB = " " + PREFIX_WEBSITE + VALID_WEBSITE_BOB;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_TELEGRAM_DESC = " " + PREFIX_TELEGRAM + "telehandle"; // Must start with @
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_MODULE_DESC = " " + PREFIX_MODULE + "hubby*"; // '*' not allowed in Modules
    public static final String INVALID_ALIAS_DESC = " " + PREFIX_ALIAS + "&"; // Only alphanumeric
    public static final String INVALID_MAJOR_DESC = " " + PREFIX_MAJOR + "1"; //Only alphabet and spaces
    public static final String INVALID_WEBSITE_DESC = " " + PREFIX_WEBSITE + "website"; // Not a url
    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditPersonDescriptor DESC_AMY;
    public static final EditCommand.EditPersonDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY)
                .withEmail(VALID_EMAIL_AMY)
                .withAlias(VALID_ALIAS_AMY)
                .withMajor(VALID_MAJOR_ANY)
                .withNote(VALID_NOTE_AMY)
                .withTelegram(VALID_TELEGRAM_AMY)
                .withWebsite(VALID_WEBSITE_AMY)
                .withModules(VALID_MODULE_CS2106)
                .build();
        DESC_BOB = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_BOB)
                .withAlias(VALID_ALIAS_BOB)
                .withMajor(VALID_MAJOR_BOB)
                .withNote(VALID_NOTE_BOB)
                .withTelegram(VALID_TELEGRAM_BOB)
                .withWebsite(VALID_WEBSITE_BOB)
                .withModules(VALID_MODULE_CS2106)
                .build();

    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
                                            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
                                            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered person list and selected person in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Person> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPersonList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredPersonList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the person at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showPersonAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPersonList().size());

        Person person = model.getFilteredPersonList().get(targetIndex.getZeroBased());
        final String[] splitName = person.getName().fullName.split("\\s+");
        model.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredPersonList().size());
    }

}
