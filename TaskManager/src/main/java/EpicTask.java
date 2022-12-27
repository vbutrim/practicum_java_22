import java.util.ArrayList;
import java.util.List;

/**
 * @author butrim
 */
public class EpicTask extends Task {
    private final List<SubTask> subtasks;

    public EpicTask(int id, String name) {
        super(id, name);
        this.subtasks = new ArrayList<>();
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

    @Override
    public String toString() {
        return "EpicTask{" +
                "id=" + getId() + "," +
                "status=" + getStatus() + "," +
                "subtasks=" + subtasks +
                '}';
    }
}
