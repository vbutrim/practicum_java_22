import java.util.ArrayList;
import java.util.List;

/**
 * @author butrim
 */
public class EpicTask extends Task {
    private final List<Integer> subtaskIds;

    public EpicTask(int id, String name) {
        super(id, name);
        this.subtaskIds = new ArrayList<>();
    }

    @Override
    public Status getStatus() {
        // todo
        return Status.NEW;
    }

    @Override
    public Type getType() {
        return Type.EPIC;
    }

    public List<Integer> getSubtaskIds() {
        return subtaskIds;
    }

    @Override
    public String toString() {
        return "EpicTask{" +
                "id=" + getId() + "," +
                "status=" + getStatus() + "," +
                "subtasks=" + subtaskIds +
                '}';
    }
}
