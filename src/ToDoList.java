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
	public Task getTask(String description) {
		return tasks.get(description);
	}
	public Task removeTask(String description) {
		return tasks.remove(description);
	}
	public Collection<Task> getAllTasks() {
		return tasks.values();
	}
	public Collection<Task> getIncompletedTask(){
		Collection<Task> incompletedTasks = new ArrayList<Task> ();
		Collection<Task> allTasks = new ArrayList<Task> ();
		allTasks = getAllTasks();
		for (Task task: allTasks) 
			if (task.isComplete() == false) incompletedTasks.add(task);
		return incompletedTasks;
	}
	public Collection<Task> getCompletedTasks() {
		Collection<Task> completedTasks = new ArrayList<Task> ();
		Collection<Task> allTasks = new ArrayList<Task> ();
		allTasks = getAllTasks();
		for (Task task: allTasks) 
			if (task.isComplete() == true) completedTasks.add(task);
		return completedTasks;
	}

  public boolean editDescription(String oldDesc, String newDesc) throws TaskDescriptionAlreadyExistsException {
		Task existingTask = getTask(newDesc);
		if (existingTask != null) {
			throw new TaskDescriptionAlreadyExistsException();
		}
				
		if (removeTask(oldDesc) !=null) {
			addTask(new Task(newDesc));
			return true;
			
		} else {
			return false;
		}
	}
	
	public int getNumOfIncompleteTask() {
		int noOfCompletedTask = this.getCompletedTasks().size();
		int totalNumberOfTask = this.getAllTasks().size();
		return totalNumberOfTask - noOfCompletedTask;
	}
}
