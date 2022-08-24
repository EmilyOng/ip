package duke.commands;

import duke.exceptions.DukeException;
import duke.managers.TaskManager;
import duke.managers.UiManager;
import duke.models.task.Event;
import duke.utils.DukeValidator;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Encapsulates a command for adding an {@link Event} task. The command should be used as follows:
 * <ul>
 *     <li>
 *         {@code event {taskDescription} /by {taskDate}}: To add an event task with the corresponding description
 *         and date.
 *     </li>
 * </ul>
 *
 * @author Emily Ong Hui Qi
 */
public class AddEventTaskCommand extends AddTaskCommand implements Command {
    public static final String COMMAND_WORD = "event";
    private static final String INVALID_EVENT_TASK_ERROR = "Use the 'event' command together with the " +
            "task description and date\nFor example: 'event project meeting /at 2022-12-02'";

    /**
     * Matches a non-empty description and a non-empty date, separated by a {@code '/at'} indicator.
     * <p>For example: {@code {taskDescription} /at {taskDate}}</p>
     */
    private static final Pattern MATCH_EVENT_TASK = Pattern.compile("(?<taskDescription>.+?)\\s/at\\s(?<taskDate>.+)");

    private final String arguments;

    /**
     * Creates a new instance of the Command handler for adding an {@link Event} task.
     *
     * @param arguments The arguments following the {@code 'event'} prefix supplied by the user from keyboard input
     */
    public AddEventTaskCommand(String arguments) {
        this.arguments = arguments;
    }

    @Override
    public void execute(TaskManager taskManager, UiManager uiManager) throws DukeException {
        Matcher matcher = AddEventTaskCommand.MATCH_EVENT_TASK.matcher(this.arguments);
        if (!matcher.matches()) {
            throw new DukeException(AddEventTaskCommand.INVALID_EVENT_TASK_ERROR);
        }
        String description = matcher.group("taskDescription").strip();
        String dateString = matcher.group("taskDate").strip();
        LocalDate date = DukeValidator.parseDate(dateString);
        uiManager.print(this.addTask(taskManager, () -> new Event(description, date)));
    }
}
