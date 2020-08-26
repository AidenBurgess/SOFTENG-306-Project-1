package main.java.dotio;

/**
 * This class contains the name and task time of a task.
 */
public class Task {
    private String _name;
    private int _taskTime;

    public Task(String name, int taskTime) {
        _name = name;
        _taskTime = taskTime;
    }

    public String getName() {
        return _name;
    }

    public int getTaskTime() {
        return _taskTime;
    }
}
