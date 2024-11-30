package ua.ucu.edu.apps.task2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Group<T> extends Task<T> {
    private String groupUuid;
    private List<Task<T>> tasks = new ArrayList<>();

    public Group<T> addTask(Task<T> task) {
        tasks.add(task);
        return this;
    }

    public List<Task<T>> getTasks() {
        return tasks;
    }

    @Override
    public void freeze() {
        super.freeze();
        if (groupUuid == null) {
            groupUuid = UUID.randomUUID().toString();
        }
        setHeader("groupId", groupUuid);
        for (Task<T> task : tasks) {
            task.freeze();
            task.setHeader("groupId", groupUuid);
        }
    }

    @Override
    public void apply(T arg) {
        this.freeze();
        tasks = Collections.unmodifiableList(tasks);
        for (Task<T> task : tasks) {
            task.apply(arg);
        }
    }
}
