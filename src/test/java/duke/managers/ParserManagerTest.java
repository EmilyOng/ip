package duke.managers;

import static org.junit.jupiter.api.Assertions.assertTrue;

import duke.commands.*;
import org.junit.jupiter.api.Test;

public class ParserManagerTest {
    @Test
    public void parseCommand_invalidCommandWord_returnsIncorrectCommand() {
        assertTrue(new ParserManager().parseCommand("invalid") instanceof IncorrectCommand);
    }

    @Test
    public void parseCommand_listCommand_returnsListCommand() {
        assertTrue(new ParserManager().parseCommand("list") instanceof ListTasksCommand);
    }

    @Test
    public void parseCommand_eventCommand_returnsListCommand() {
        assertTrue(new ParserManager().parseCommand("event") instanceof AddEventTaskCommand);
    }

    @Test
    public void parseCommand_todoCommand_returnsListCommand() {
        assertTrue(new ParserManager().parseCommand("todo") instanceof AddToDoTaskCommand);
    }

    @Test
    public void parseCommand_byeCommand_returnsListCommand() {
        assertTrue(new ParserManager().parseCommand("bye") instanceof ByeCommand);
    }

    @Test
    public void parseCommand_deleteCommand_returnsListCommand() {
        assertTrue(new ParserManager().parseCommand("delete") instanceof DeleteTaskCommand);
    }

    @Test
    public void parseCommand_markCommand_returnsListCommand() {
        assertTrue(new ParserManager().parseCommand("mark") instanceof MarkTaskCommand);
    }

    @Test
    public void parseCommand_unmarkCommand_returnsListCommand() {
        assertTrue(new ParserManager().parseCommand("unmark") instanceof UnmarkTaskCommand);
    }
}
