package Task;

import User.User;

public class Task {
    int taskId;
    int sprintId;
    TaskType type;
    TaskStatus status;
    User assignedUser;

    public Task(int taskId, int sprintId, TaskType type, User assignedUser) {
        this.taskId = taskId;
        this.sprintId = sprintId;
        this.type = type;
        this.status = TaskStatus.TODO;
        this.assignedUser = assignedUser;
    }

    public int getSprintId() {
        return sprintId;
    }

    public void setSprintId(int sprintId) {
        this.sprintId = sprintId;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public TaskType getType() {
        return type;
    }

    public void setType(TaskType type) {
        this.type = type;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public User getAssignedUser() {
        return assignedUser;
    }

    public void setAssignedUser(User assignedUser) {
        this.assignedUser = assignedUser;
    }
}
