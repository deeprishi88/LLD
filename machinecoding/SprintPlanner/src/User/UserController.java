package User;

import Task.Task;

import java.util.ArrayList;
import java.util.List;

public class UserController {
    List<User> userList;

    public UserController(){
        this.userList = new ArrayList<User>();
    }

    public List<User> getUserList() {
        return userList;
    }

    public void addUser(int userId, String userName){
        User user = new User(userId, userName);
        userList.add(user);
    }

    public void removeUser(int userId, String userName){
        for(User user : userList){
            if(user.getUserId() == userId){
                userList.remove(user);
                break;
            }
        }
    }

    public List<Task> assignedTasksUser(int userId){
        for(User user : userList){
            if(user.getUserId() == userId){
                return user.getAssignedTasks();
            }
        }
        return new ArrayList<>();
    }
}
