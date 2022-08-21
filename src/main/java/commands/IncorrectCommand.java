package commands;

import managers.TaskManager;
import ui.Ui;

public class IncorrectCommand implements Command {
    private static final String UNKNOWN_COMMAND_ERROR = "I do not understand your command!";

    @Override
    public void execute(TaskManager taskManager, Ui ui) {
        ui.print(IncorrectCommand.UNKNOWN_COMMAND_ERROR);
    }
}
