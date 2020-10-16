package seedu.medibook.logic.parser;

import static seedu.medibook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.medibook.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.HashSet;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.medibook.logic.commands.AccessCommand;
import seedu.medibook.logic.commands.AddCommand;
import seedu.medibook.logic.commands.ClearCommand;
import seedu.medibook.logic.commands.Command;
import seedu.medibook.logic.commands.DeleteCommand;
import seedu.medibook.logic.commands.EditCommand;
import seedu.medibook.logic.commands.ExitCommand;
import seedu.medibook.logic.commands.FindCommand;
import seedu.medibook.logic.commands.HelpCommand;
import seedu.medibook.logic.commands.ListCommand;
import seedu.medibook.logic.commands.NoteCommand;
import seedu.medibook.logic.parser.exceptions.ParseException;
import seedu.medibook.model.patient.DateOfBirth;
import seedu.medibook.model.patient.Ic;
import seedu.medibook.model.patient.Name;
import seedu.medibook.model.patient.Patient;
import seedu.medibook.model.patient.Phone;
import seedu.medibook.model.tag.Tag;

/**
 * Parses user input.
 */
public class MediBookParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {

        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case NoteCommand.COMMAND_WORD:
            return new NoteCommandParser().parse(arguments);

        case AccessCommand.COMMAND_WORD:
            return new AccessCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
