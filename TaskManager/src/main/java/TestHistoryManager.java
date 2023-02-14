import java.util.List;

/**
 * @author butrim
 */
public class TestHistoryManager {
    public static void main(String[] args) {
        InMemoryHistoryManager historyManager = new InMemoryHistoryManager();

        historyManager.addTask(1);

        System.out.println(historyManager.getHistoryIds());

        List<Integer> historyIds = historyManager.getHistoryIds();
        historyIds.add(10); // ?

        System.out.println(historyManager.getHistoryIds()); // ?
    }
}
