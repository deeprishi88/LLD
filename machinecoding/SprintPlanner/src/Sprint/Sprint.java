package Sprint;

import Task.Task;
import User.User;

import java.util.ArrayList;
import java.util.List;

public class Sprint {
    int sprintId;
    User createdByUser;
    List<Task> tasks;
    List<User> userList;

    public Sprint(int sprintId, User createdByUser, List<User> userList) {
        this.sprintId = sprintId;
        this.createdByUser = createdByUser;
        this.userList = userList;
        this.tasks = new ArrayList<Task>();
    }

    public int getSprintId() {
        return sprintId;
    }

    public void setSprintId(int sprintId) {
        this.sprintId = sprintId;
    }

    public User getCreatedByUser() {
        return createdByUser;
    }

    public void setCreatedByUser(User createdByUser) {
        this.createdByUser = createdByUser;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}
