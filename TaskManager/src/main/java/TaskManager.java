import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author butrim
 */
public class TaskManager {
    private final TaskIdGenerator taskIdGenerator;
    // option 1: 1 hashmap
    private final HashMap<Integer, Task> taskById;

    public TaskManager() {
        this.taskIdGenerator = new TaskIdGenerator();
        this.taskById = new HashMap<>();
    }

    // option 2: 3 hashmap
/*    private HashMap<Integer, SingleTask> singleTasks;
    private HashMap<Integer, SubTask> subTasks;
    private HashMap<Integer, EpicTask> epicTasks;*/

    /**
     * option 1: how to save object with generated id in hashmap
     */
    public void saveNewTask(SingleTask singleTask) {
        // 1: generate id and save it to the task
        singleTask.setId(taskIdGenerator.getNextFreeId());
        // 2: save task
        taskById.put(singleTask.getId(), singleTask);
    }

    /**
     * option 2: more safe, not to create SingleTask object without id
     */
    public void saveNewTask(SingleTask.ToCreate singleTaskToCreate) {
        // 1: create SingleTask
        int nextFreeId = taskIdGenerator.getNextFreeId();

        SingleTask singleTask = new SingleTask(
                nextFreeId,
                singleTaskToCreate.getName(),
                Status.NEW
        );

        // 2: save task
        taskById.put(singleTask.getId(), singleTask);
    }

    public void update(Task task) {
        taskById.put(task.getId(), task);
    }

    public ArrayList<Task> getAllTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        for (Task task : this.taskById.values()) {
            // add filter if you would like to have only subtasks
            tasks.add(task);
        }
        return tasks;
    }

    public ArrayList<Task> getTaskByIds(List<Integer> taskIds) {
        ArrayList<Task> tasks = new ArrayList<>();
        for (int id : taskIds) {
            tasks.add(this.taskById.get(id));
        }
        return tasks;
    }

    /**
     * @return null if no task not found
     */
    public Task getTaskById(int id) {
        return taskById.get(id);
    }

    public static final class TaskIdGenerator {
        private int nextFreeId = 0;

        public int getNextFreeId() {
            return nextFreeId++;
        }
    }
}
