package commands;

import managers.TaskManager;
import ui.Ui;

public class ByeCommand implements Command {
    public static final String COMMAND_WORD = "bye";
    private static final String GOODBYE_MESSAGE = "Bye. Hope to see you again soon!";

    @Override
    public void execute(TaskManager taskManager, Ui ui) {
        ui.print(ByeCommand.GOODBYE_MESSAGE);
    }

    public static boolean is(Command command) {
        return command instanceof ByeCommand;
    }
}
