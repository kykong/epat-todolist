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
	public void testGetAllTask() {
		assertNotNull(todoList);
		todoList.addTask(task1);
		todoList.addTask(task2);
		todoList.addTask(task3);
		for (Task i : todoList.getAllTasks()) {
			assertEquals(i, todoList.getTask(i.getDescription()));
		}
		
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
	public void testRemoveTaskOnEmptyListDoesNothing() {
		todoList.removeTask(task1.getDescription());
		assertEquals(0, todoList.getAllTasks().size());
	}
	
	@Test
	public void testRemoveNonExistentTaskReturnsNullTask() {
		todoList.addTask(task1);
		Task result = todoList.removeTask(task2.getDescription());
		assertNull(result);
	}
	
	@Test
	public void editNonExistentTaskDescriptionReturnsFalse() throws TaskDescriptionAlreadyExistsException {
		boolean result = todoList.editDescription("oldTaskDesc", "newTaskDesc");
		assertFalse(result);
	}
	
	@Test
	public void editExistingTaskDescriptionToDuplicateDescriptionReturnsException() {
		todoList.addTask(task1);
		todoList.addTask(task2);
		
		try {
			todoList.editDescription(task2.getDescription(), task1.getDescription());
			fail("Expected exception to be thrown");
			
		} catch (TaskDescriptionAlreadyExistsException ex) {
			//ex.printStackTrace();
		}
	}
	
	@Test
	public void correctEditExistingTaskDescription() throws TaskDescriptionAlreadyExistsException {
		todoList.addTask(task1);
		todoList.addTask(task2);
		
		boolean result = todoList.editDescription(task1.getDescription(), "New desc1");
		Task task = todoList.getTask("New desc1");
		
		assertEquals(2, todoList.getAllTasks().size());
		assertTrue(result);
		assertNotNull(task);
		assertEquals("New desc1", task.getDescription());
	}
}
