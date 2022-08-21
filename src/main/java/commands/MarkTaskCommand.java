package commands;

import exceptions.DukeException;
import managers.TaskManager;
import models.task.Task;

public class MarkTaskCommand implements Command {
    public static final String COMMAND_WORD = "mark";
    private static final String MARK_TASK_AS_DONE_MESSAGE = "Nice! I've marked this task as done:";
    private static final String MISSING_TASK_INDEX_ERROR = "You are missing a task number!\n" +
            "Use the 'list' command to view the tasks and their number.";
    private static final String NAN_TASK_NUMBER_ERROR = "The task number you provided is not a number!";
    private static final String TASK_NUMBER_IS_INVALID_ERROR = "The task number you provided is not valid!\n" +
            "Use the 'list' command to view the tasks and their number.";

    private final String arguments;

    public MarkTaskCommand(String arguments) {
        this.arguments = arguments;
    }

    @Override
    public String execute(TaskManager taskManager) throws DukeException {
        // Retrieve the task index (1-indexed) to mark the task as done
        if (this.arguments.length() == 0) {
            throw new DukeException(MarkTaskCommand.MISSING_TASK_INDEX_ERROR);
        }
        int taskNumber;
        try {
            taskNumber = Integer.parseInt(this.arguments);
        } catch (NumberFormatException e) {
            throw new DukeException(MarkTaskCommand.NAN_TASK_NUMBER_ERROR);
        }

        try {
            Task task = taskManager.get(taskNumber);
            task.markAsDone();
            Task updatedTask = taskManager.update(taskNumber, task);
            return String.format("%s\n\t%s", MarkTaskCommand.MARK_TASK_AS_DONE_MESSAGE, updatedTask);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
