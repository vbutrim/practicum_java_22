import java.util.ArrayList;
import java.util.List;

/**
 * @author butrim
 */
public interface TaskManager {

    List<Task> getAllTasks();

    void save(SingleTask.ToCreate singleTaskToCreate);

    Task getTaskByIdOrNull(int taskId);

    void update(Task task);

    void deleteTaskById(int taskId);

    /**
     * @return null if no task not found
     */
    Task getTaskById(int id);
}
