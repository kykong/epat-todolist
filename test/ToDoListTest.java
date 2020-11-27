import org.junit.*;
import static org.junit.Assert.*;


import java.util.Collection;

public class ToDoListTest {
	private Task task1;
	private Task task2;
	private Task task3;
	private ToDoList todoList;
	
	public ToDoListTest() {
		super();
	}
	@Before
	 public void setUp() throws Exception{
		task1 = new Task ("desc 1");
		task2 = new Task ("desc 2");
		task3 = new Task ("desc 3");
		todoList = new ToDoList();
	}
	@After
	 public void tearDown() throws Exception{
		task1 = null;
		task2 = null;
		task3 = null;
		todoList = null;
	}

	@Test
	public void testAddTask() {
		assertNotNull(todoList);
		todoList.addTask(task1);
		assertEquals(1, todoList.getAllTasks().size());
		assertEquals(task1, todoList.getTask(task1.getDescription()));
	}
	@Test
	public void testgetStatus() {
		assertNotNull(todoList);
		todoList.addTask(task1);
		assertEquals(false, todoList.getStatus(task1.getDescription()));
		todoList.completeTask(task1.getDescription());
		assertEquals(true, todoList.getStatus(task1.getDescription()));
	}
	@Test
	public void testRemoveTask() {
		assertNotNull(todoList);
		todoList.addTask(task1);
		todoList.addTask(task2);;
		todoList.removeTask(task1.getDescription());
		assertNull(todoList.getTask(task1.getDescription()));	
	}
	@Test
	public void testGetCompletedTasks() {
		task1.setComplete(true);
		task3.setComplete(true);
		todoList.addTask(task1);
		todoList.addTask(task2);
		todoList.addTask(task3);
		Collection<Task> tasks = todoList.getCompletedTasks();
		assertEquals(2, tasks.size());
	}
	@Test
	public void testGetTasks() {
		task1.setComplete(true);
		task2.setComplete(true);
		todoList.addTask(task1);
		todoList.addTask(task2);
		todoList.addTask(task3);
		Collection<Task> tasks = todoList.getTasks(true);
		assertEquals(2, tasks.size());
		
		todoList.getTask(task1.getDescription()).setComplete(false);
		tasks = todoList.getTasks(false);
		assertEquals(2, tasks.size());
	}
	@Test
	public void testSearchTask() {
		todoList.addTask(task1);
		todoList.addTask(task2);
		todoList.addTask(task3);
		Collection<Task> search = todoList.searchTask("desc");
		assertEquals(3, search.size());

		search = todoList.searchTask("wrong");
		assertEquals(0, search.size());

		search = todoList.searchTask("1");
		assertEquals(1, search.size());
	}
	@Test
	public void testRemoveAllTasks() {
		todoList.addTask(task1);
		todoList.addTask(task2);
		todoList.addTask(task3);
		assertEquals(3, todoList.taskCount());
		todoList.removeAllTasks();
		assertEquals(0, todoList.taskCount());
		
		todoList.addTask(task1);
		assertEquals(1, todoList.taskCount());
		assertFalse(todoList.isEmpty());
		todoList.removeAllTasks();
		assertEquals(0, todoList.taskCount());
		assertTrue(todoList.isEmpty());
	}
	@Test
	public void testEditTaskDescription() {
		task1.setComplete(true);
		task2.setComplete(false);
		todoList.addTask(task1);
		todoList.addTask(task2);
		todoList.editTaskDescription(task1.getDescription(), "new 1");
		assertEquals(task1, todoList.getTask("new 1"));
		assertNull(todoList.getTask("desc 1"));
		assertTrue(todoList.getStatus("new 1"));
		assertEquals(2, todoList.taskCount());
	}
}
