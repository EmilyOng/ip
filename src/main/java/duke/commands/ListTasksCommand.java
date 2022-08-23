package duke.commands;

import duke.exceptions.DukeException;
import duke.managers.TaskManager;
import duke.managers.UiManager;
import duke.models.task.Task;
import duke.utils.DukeValidator;

import java.time.LocalDate;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ListTasksCommand implements Command {
    public static final String COMMAND_WORD = "list";
    private static final String ERROR_UNKNOWN_OPTION = "Unknown option provided! Use either a '/on' or\n" +
            "'/before' command together with a date in order to\n" +
            "filter the tasks by their date!";
    // Matches a '/on' command followed by a (possibly invalid) date string. This is used
    // to show the list of tasks occurring on the specific date.
    // For example: /on <date>
    private static final Pattern MATCH_TASKS_ON = Pattern.compile("/on\\s(?<date>.+)");
    // Matches a '/before' command followed by a (possibly invalid) date string. This is
    // used to show the list of tasks occurring before the specific date.
    // For example: /before <date>
    private static final Pattern MATCH_TASKS_BEFORE = Pattern.compile("/before\\s(?<date>.+)");

    private final String arguments;

    public ListTasksCommand(String arguments) {
        this.arguments = arguments;
    }

    @Override
    public void execute(TaskManager taskManager, UiManager uiManager) throws DukeException {
        if (this.arguments.length() > 0) {
            Matcher matchTasksOn = ListTasksCommand.MATCH_TASKS_ON.matcher(this.arguments);
            if (matchTasksOn.matches()) {
                LocalDate date = DukeValidator.parseDate(matchTasksOn.group("date"));
                List<Task> filteredList = taskManager.list(
                        task -> {
                            LocalDate taskDate = task.getDate();
                            return taskDate != null && taskDate.isEqual(date);
                        }
                );
                uiManager.print(TaskManager.display(filteredList));
                return;
            }
            Matcher matchTasksBefore = ListTasksCommand.MATCH_TASKS_BEFORE.matcher(this.arguments);
            if (matchTasksBefore.matches()) {
                LocalDate date = DukeValidator.parseDate(matchTasksBefore.group("date"));
                List<Task> filteredList = taskManager.list(
                        task -> {
                            LocalDate taskDate = task.getDate();
                            return taskDate != null && taskDate.isBefore(date);
                        }
                );
                uiManager.print(TaskManager.display(filteredList));
                return;
            }
            throw new DukeException(ListTasksCommand.ERROR_UNKNOWN_OPTION);
        }

        uiManager.print(TaskManager.display(taskManager.list()));
    }
}
