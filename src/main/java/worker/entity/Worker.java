package worker.entity;

import worker.constants.WorkerType;

public class Worker {

    private int id;
    private String name;
    private WorkerType type;

    public Worker(int id, String name, WorkerType type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public WorkerType getType() {
        return type;
    }
}
