/**
 * @author butrim
 */
public abstract class Managers {

    private static HistoryManager historyManager;
    private static TaskManager taskManager;

    public static HistoryManager getHistoryManager() {
        if (historyManager == null) {
            historyManager = new HistoryManager.InMemoryArrayList();
        }
        return historyManager;
    }

    public static TaskManager getTaskManager() {
        if (taskManager == null) {
            taskManager = new InMemoryTaskManager(getHistoryManager());
        }
        return taskManager;
    }
}
