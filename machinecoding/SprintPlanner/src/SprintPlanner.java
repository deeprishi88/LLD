import Sprint.SprintController;
import Task.TaskStatus;
import Task.TaskType;
import User.User;
import User.UserController;

import java.util.ArrayList;
import java.util.List;

public class SprintPlanner {
    UserController userController;
    SprintController sprintController;

    List<User> userList;

    public SprintPlanner(UserController userController) {
        this.userController = userController;
        this.sprintController = new SprintController();
        this.userList = userController.getUserList();
    }

    public void demo() {
        List<User> userList1 = new ArrayList<>();
        userList1.add(userList.get(0));
        userList1.add(userList.get(1));
        userList1.add(userList.get(2));

        List<User> userList2 = new ArrayList<>();
        userList2.add(userList.get(3));
        userList2.add(userList.get(4));
        userList2.add(userList.get(5));

        List<User> userList3 = new ArrayList<>();
        userList3.add(userList.get(6));
        userList3.add(userList.get(2));
        userList3.add(userList.get(7));

        sprintController.createSprint(11, userList.get(0), userList1);
        sprintController.createSprint(12, userList.get(3), userList2);
        sprintController.createSprint(13, userList.get(7), userList3);

        sprintController.addTask(111,11, TaskType.FEATURE, userList1.get(0));
        sprintController.addTask(112,11, TaskType.BUG, userList1.get(1));
        sprintController.addTask(113,11, TaskType.STORY, userList1.get(2));

        sprintController.addTask(121,12, TaskType.FEATURE, userList2.get(0));
        sprintController.addTask(122,12, TaskType.BUG, userList2.get(1));
        sprintController.addTask(123,12, TaskType.STORY, userList2.get(2));

        sprintController.addTask(131,13, TaskType.FEATURE, userList3.get(0));
        sprintController.addTask(132,13, TaskType.BUG, userList3.get(1));
        sprintController.addTask(133,13, TaskType.STORY, userList3.get(2));

        sprintController.showUserTasks(userList1.get(1));
        sprintController.updateTaskStatus(112, 11, userList1.get(1), TaskStatus.INPROGRESS);
        sprintController.showUserTasks(userList1.get(1));

    }
}
