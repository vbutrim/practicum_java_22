import java.util.List;

/**
 * @author butrim
 */
public class TestMain {
    public static void main(String[] args) {
        test1();
        flakyTest2();

        System.out.println("All tests are passed");
    }

    private static void test1() {
        // Given: что?
        HistoryManager historyManager = Managers.getHistoryManager();

        // When: когда?
        historyManager.addTask(1);

        // Then: проверка результата
        List<Integer> result = historyManager.getHistoryIds();

        /*
            if (!result.contains(1)) {
                throw new IllegalArgumentException();
            }
         */
        assert result.contains(1);
    }

    private static void flakyTest2() {
        // Given: что?
        HistoryManager historyManager = Managers.getHistoryManager();

        // When: когда?
        historyManager.addTask(1);

        // Then: проверка результата
        List<Integer> result = historyManager.getHistoryIds();

        assert result.contains(2);
    }
}
