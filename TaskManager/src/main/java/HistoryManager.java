import java.util.List;

/**
 * @author butrim
 */
public interface HistoryManager {

    void addTask(int taskId);

    List<Integer> getHistoryIds();
}
