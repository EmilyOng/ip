package handlers;

import exceptions.DukeException;
import models.Event;
import models.TaskManager;
import utils.DukeValidator;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddEventTaskCommand extends AddTaskCommand implements DukeCommand {
    private static final String INVALID_EVENT_TASK_ERROR = "Use the 'event' command together with the " +
            "task description and date time\nFor example: 'event project meeting /at 2022-12-02'";

    // Matches a non-empty description and a non-empty datetime, separated by a '/at' command
    // For example: <description> /at <datetime>
    private static final Pattern MATCH_EVENT_TASK = Pattern.compile("(?<taskDescription>.+?)\\s/at\\s(?<taskDatetime>.+)");

    @Override
    public String execute(TaskManager taskManager, String arguments) throws DukeException {
        Matcher matcher = AddEventTaskCommand.MATCH_EVENT_TASK.matcher(arguments);
        if (!matcher.find()) {
            throw new DukeException(AddEventTaskCommand.INVALID_EVENT_TASK_ERROR);
        }
        String description = matcher.group("taskDescription").strip();
        String datetimeString = matcher.group("taskDatetime").strip();
        LocalDate datetime = DukeValidator.parseDate(datetimeString);
        return this.addTask(taskManager, () -> new Event(description, datetime));
    }
}
