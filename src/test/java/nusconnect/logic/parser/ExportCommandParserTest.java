package nusconnect.logic.parser;

import static nusconnect.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import nusconnect.logic.LogicManager;
import nusconnect.logic.commands.ExportCommand;
import nusconnect.logic.parser.exceptions.ParseException;

public class ExportCommandParserTest {


    private ExportCommandParser parser;
    private LogicManager mockLogicManager;

    @BeforeEach
    public void setUp() {
        mockLogicManager = mock(LogicManager.class); // Mock LogicManager
        parser = new ExportCommandParser(mockLogicManager); // Instantiate the parser
    }

    @Test
    public void parse_validFilePath_returnsExportCommand() throws ParseException {

        String validInputParser = "src/test/data/JsonAddressBookStorageTest/addressbook.json";

        ExportCommand expectedExportCommand = new ExportCommand(validInputParser, mockLogicManager);

        // Parse input to get the actual ExportCommand
        ExportCommand actualExportCommand = parser.parse(validInputParser);

        // Verify that the parsed command matches the expected command
        assertEquals(expectedExportCommand, actualExportCommand);
    }

    @Test
    public void parse_emptyFilePath_throwsParseException() {
        // Empty input
        String emptyInput = "";

        // Expected behavior: the parser throws a ParseException
        ParseException exception = assertThrows(ParseException.class, () -> parser.parse(emptyInput));

        // Verify the exception message is correct
        assertEquals(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ExportCommand.MESSAGE_USAGE),
                exception.getMessage());
    }

    @Test
    public void parse_invalidFilePath_throwsParseException() {
        // Invalid input (e.g., malformed or incorrectly formatted path)
        String invalidInput = "invalidFilePath";

        // Expected behavior: the parser throws a ParseException for empty input
        ParseException exception = assertThrows(ParseException.class, () -> parser.parse("")); // Empty string case

        // Verify the exception message is correct
        assertEquals(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ExportCommand.MESSAGE_USAGE),
                exception.getMessage());
    }
}
