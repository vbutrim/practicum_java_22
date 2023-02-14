import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author butrim
 */
public class InMemoryTaskManager implements TaskManager {
    private final HashMap<Integer, Task> taskById;
    private final TaskIdGenerator taskIdGenerator;

    private final HistoryManager historyManager;

    public InMemoryTaskManager(HistoryManager historyManager) {
        this.taskById = new HashMap<>();
        this.taskIdGenerator = new TaskIdGenerator();
        this.historyManager = historyManager;
    }

    @Override
    public List<Task> getAllTasks() {
        return new ArrayList<>(this.taskById.values());
    }

    @Override
    public void save(SingleTask.ToCreate singleTaskToCreate) {
        int nextFreeId = taskIdGenerator.getNextFreeId();

        SingleTask singleTask = new SingleTask(
                nextFreeId,
                singleTaskToCreate.getName(),
                Status.NEW
        );

        taskById.put(singleTask.getId(), singleTask);
    }

    @Override
    public Task getTaskByIdOrNull(int taskId) {
        Task task = taskById.get(taskId);
        addHistory(taskId);
        return task;
    }

    @Override
    public void update(Task task) {
        taskById.put(task.getId(), task);
    }

    @Override
    public void deleteTaskById(int taskId) {
        taskById.remove(taskId);
    }

    private void addHistory(int taskId) {
        this.historyManager.addTask(taskId);
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
