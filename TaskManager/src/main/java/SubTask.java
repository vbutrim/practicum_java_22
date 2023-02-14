/**
 * @author butrim
 */
public class SubTask extends Task {
    private final Status status;
    private final int epicTaskId;

    public SubTask(int id, String name, Status status, int epicTaskId) {
        super(id, name);
        this.status = status;
        this.epicTaskId = epicTaskId;
    }

    @Override
    public Status getStatus() {
        return status;
    }

    @Override
    public Type getType() {
        return Type.SUB;
    }

    public int getEpicTaskId() {
        return epicTaskId;
    }

    @Override
    public String toString() {
        return "SubTask{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                '}';
    }
}
