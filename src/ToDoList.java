import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collection;

public class ToDoList {

	private HashMap<String, Task> tasks = new HashMap<String, Task>();
	
	public void addTask (Task task) {
		tasks.put(task.getDescription(), task);
	}
	public void completeTask(String description) {
		Task task = null;
		if ((task = tasks.get(description)) != null){
			task.setComplete(true);
		};
	}
	public boolean getStatus(String description) {
		Task task = null;
		if ((task = tasks.get(description)) != null){
			return task.isComplete();
		};
		return false;
	}
	public boolean editTaskDescription(String oldDesc, String newDesc) {
		Task task = tasks.get(oldDesc);
		if (task == null) {
			return false;
		} else {
			tasks.remove(oldDesc);
			tasks.put(newDesc, task);
			return true;
		}
	}
	public Task getTask(String description) {
		return tasks.get(description);
	}
	public Task removeTask(String description) {
		return tasks.remove(description);
	}
	public Collection<Task> getAllTasks() {
		return tasks.values();
	}
	public Collection<Task> getCompletedTasks() {
		Collection<Task> completedTasks = new ArrayList<Task> ();
		Collection<Task> allTasks = new ArrayList<Task> ();
		allTasks = getAllTasks();
		for (Task task: allTasks) 
			if (task.isComplete() == true) completedTasks.add(task);
		return completedTasks;
	}
	public Collection<Task> getTasks(Boolean completed) {
		Collection<Task> filteredTasks = new ArrayList<Task> ();
		Collection<Task> allTasks = new ArrayList<Task> ();
		allTasks = getAllTasks();
		for (Task task: allTasks) 
			if (task.isComplete() == completed) filteredTasks.add(task);
		return filteredTasks;
	}
	public Collection<Task> searchTask(String query) {
		Collection<Task> searchResults = new ArrayList<Task> ();
		Collection<Task> allTasks = new ArrayList<Task> ();
		allTasks = getAllTasks();
		for (Task task: allTasks) 
			if (task.getDescription().contains(query)) searchResults.add(task);
		return searchResults;
	}
	public int taskCount() {
		return tasks.size();
	}
	public void removeAllTasks() {
		tasks.clear();
	}
	public boolean isEmpty() {
		return tasks.isEmpty();
	}
}
