import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class ToDoList {
private HashMap<String, Task> tasks = new HashMap<String, Task>();

	public void addTask (Task task) {
		//Add code here
	}
	public void completeTask(String description) {
		// Add code here
	}
	public boolean getStatus(String description) {
		//Add code here
		return false;
	}
	public Task getTask(String description) {
		// Add code here
		return null;
	}
	public Task removeTask(String description) {
		// Add code here
		return null;
	}
	public Collection<Task> getAllTasks() {
		return tasks.values();

	}
	public Collection<Task> getCompletedTasks() {
		// Add code here
		return null;
	}
}
