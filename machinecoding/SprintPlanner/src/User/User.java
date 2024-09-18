package User;

import Task.Task;

import java.util.ArrayList;
import java.util.List;

public class User {
    int userId;
    String userName;
    List<Task> assignedTasks;

    public User(int userId, String userName) {
        this.userId = userId;
        this.userName = userName;
        this.assignedTasks = new ArrayList<Task>();
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<Task> getAssignedTasks() {
        return assignedTasks;
    }

    public void setAssignedTasks(List<Task> assignedTasks) {
        this.assignedTasks = assignedTasks;
    }
}
