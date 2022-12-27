/**
 * @author butrim
 */
public class SubTask extends Task {
    private final Status status;
    private final EpicTask epicTask;

    public SubTask(int id, String name, Status status, EpicTask epicTask) {
        super(id, name);
        this.status = status;
        this.epicTask = epicTask;
    }

    @Override
    public Status getStatus() {
        return status;
    }

    @Override
    public Type getType() {
        return Type.SUB;
    }

    @Override
    public String toString() {
        return "SubTask{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                '}';
    }
}
