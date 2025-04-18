package task.task3;

public class Reason {
    private final ReasonType type;
    private final String description;

    public Reason(ReasonType type, String description) {
        this.type = type;
        this.description = description;
    }

    public ReasonType getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "task.task3.Reason{" +
                "type=" + type +
                ", description='" + description + '\'' +
                '}';
    }
}
