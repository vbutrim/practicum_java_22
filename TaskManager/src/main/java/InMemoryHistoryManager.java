import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author butrim
 */
public class InMemoryHistoryManager implements HistoryManager {
    private final ArrayList<Integer> ids;

    public InMemoryHistoryManager() {
        this.ids = new ArrayList<>();
    }

    public void addTask(int taskId) {
        this.ids.add(taskId);
    }

    public List<Integer> getHistoryIds() {
        return Collections.unmodifiableList(ids);
    }
}
