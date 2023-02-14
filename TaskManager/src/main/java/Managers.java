/**
 * @author butrim
 */
public abstract class Managers {

    public static HistoryManager getHistoryManager() {
        return new InMemoryHistoryManager();
    }

    public static TaskManager getTaskManager() {
        return new InMemoryTaskManager(getHistoryManager());
    }
}
