import java.util.ArrayList;

/**
 * Utility class to manage the list of tasks and provides augmenting operations to
 * add to the list of tasks or to enumerate through the list of tasks
 */
public class TaskManager {
    // The list of tasks
    private final ArrayList<Task> taskList;

    public TaskManager() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Adds the received task into the task list
     *
     * @param taskDescription Task description received from the caller
     */
    public void add(String taskDescription) {
        this.taskList.add(new Task(taskDescription));
    }

    /**
     * Retrieve the task
     *
     * @param taskIndex An index (1-index) corresponding to a particular task
     * @return Task corresponding to the particular task index
     */
    public Task get(int taskIndex) {
        // FIXME: Error handling if the task index is invalid
        return this.taskList.get(taskIndex - 1);
    }

    /**
     * Return the formatted display of tasks
     *
     * @return List of formatted tasks
     */
    public String[] displayAll() {
        String[] commands = new String[this.taskList.size()];
        for (int i = 0; i < this.taskList.size(); i++) {
            // Implicitly invoke the display of the task defined in the Task class
            commands[i] = String.format("%d. %s", i + 1, this.taskList.get(i));
        }
        return commands;
    }
}
