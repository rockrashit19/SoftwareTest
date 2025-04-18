package task.task3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Creature {
    private final String name;
    private final String description;
    private final List<Reason> reasons = new ArrayList<>();

    public Creature(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void addReason(Reason reason) {
        reasons.add(reason);
    }

    public List<Reason> getReasons() {
        return Collections.unmodifiableList(reasons);
    }

    @Override
    public String toString() {
        return "task.task3.Creature{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", reasons=" + reasons +
                '}';
    }
}
