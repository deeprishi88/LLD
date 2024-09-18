package Sprint;

import Task.Task;
import Task.TaskType;
import Task.TaskStatus;
import User.User;

import java.util.ArrayList;
import java.util.List;

public class SprintController {
    List<Sprint> sprintList;

    public SprintController(){
        this.sprintList = new ArrayList<Sprint>();
    }

    public List<Sprint> getSprintList() {
        return sprintList;
    }

    public void setSprintList(List<Sprint> sprintList) {
        this.sprintList = sprintList;
    }

    public void createSprint(int sprintId, User createdByUser, List<User> userList){
        Sprint sprint = new Sprint(sprintId, createdByUser, userList);
        sprintList.add(sprint);
        System.out.println("Sprint Created");
    }

    public void removeSprint(int sprintId, User createdByUser, List<User> userList){
        for(Sprint sprint : sprintList){
            if(sprint.getSprintId() == sprintId){
                sprintList.remove(sprint);
                System.out.println("Sprint removed");
            }
        }
    }

    public void addTask(int taskId, int sprintId, TaskType type, User assignedUser){
        Sprint curSprint = null;
        for(Sprint sprint : sprintList){
            if(sprint.getSprintId() == sprintId){
                curSprint = sprint;
                break;
            }
        }

        if(curSprint!=null && curSprint.getTasks().size()<20){
            Task task = new Task(taskId, sprintId, type, assignedUser);

            List<Task> curSprintTasks = curSprint.getTasks();
            curSprintTasks.add(task);
            curSprint.setTasks(curSprintTasks);

            List<Task> assigned = assignedUser.getAssignedTasks();
            assigned.add(task);
            assignedUser.setAssignedTasks(assigned);

            System.out.println("Task = " + task.getTaskId() + " created for user = " + assignedUser.getUserId() + " in sprint = " + sprintId);
        }
        else{
            System.out.println("Sprint is null or full");
        }
    }

    public void removeTask(int taskId, int sprintId, TaskType type, User assignedUser){
        Sprint curSprint = null;
        for(Sprint sprint : sprintList){
            if(sprint.getSprintId() == sprintId){
                curSprint = sprint;
                break;
            }
        }

        if(curSprint!=null){
            List<Task> curSprintTasks = curSprint.getTasks();
            Task taskToRemove = null;
            int taskIdx = -1;
            for(Task task : curSprintTasks){
                taskIdx++;
                if(task.getTaskId() == taskId){
                    taskToRemove = task;
                    break;
                }
            }
            if(taskToRemove!=null){
                curSprintTasks.remove(taskIdx);
                curSprint.setTasks(curSprintTasks);

                List<Task> assigned = assignedUser.getAssignedTasks();
                int userTaskIdx = -1;
                for(Task task : assigned){
                    userTaskIdx++;
                    if(task == taskToRemove){
                        break;
                    }
                }

                assigned.remove(userTaskIdx);
                assignedUser.setAssignedTasks(assigned);
                System.out.println("Task removed success = " + taskToRemove.getTaskId());
            }
            else{
                System.out.println("No given Task present");
            }
        }
        else{
            System.out.println("No given Sprint present");
        }
    }

    public void updateTaskStatus(int taskId, int sprintId, User assignedUser, TaskStatus newStatus){
        Sprint curSprint = null;
        for(Sprint sprint : sprintList){
            if(sprint.getSprintId() == sprintId){
                curSprint = sprint;
                break;
            }
        }

        if(curSprint!=null){
            List<Task> curSprintTasks = curSprint.getTasks();
            Task taskToUpdate = null;
            int taskIdx = -1;
            for(Task task : curSprintTasks){
                taskIdx++;
                if(task.getTaskId() == taskId){
                    taskToUpdate = task;
                    break;
                }
            }
            if(taskToUpdate!=null){

                List<Task> assigned = assignedUser.getAssignedTasks();
                int userTaskIdx = -1;
                for(Task task : assigned){
                    userTaskIdx++;
                    if(task == taskToUpdate){
                        break;
                    }
                }

                TaskStatus taskToUpdateCurStatus = taskToUpdate.getStatus();
                if(((taskToUpdateCurStatus==TaskStatus.INPROGRESS)&&(newStatus==TaskStatus.DONE)) ||
                        ((taskToUpdateCurStatus==TaskStatus.INPROGRESS)&&(newStatus==TaskStatus.TODO)) ||
                        ((taskToUpdateCurStatus==TaskStatus.TODO)&&(newStatus==TaskStatus.INPROGRESS)&&checkUserTaskStatus(sprintId, assignedUser))){

                    taskToUpdate.setStatus(newStatus);

                    curSprintTasks.remove(taskIdx);
                    curSprintTasks.add(taskToUpdate);
                    curSprint.setTasks(curSprintTasks);

                    assigned.remove(userTaskIdx);
                    assigned.add(taskToUpdate);
                    assignedUser.setAssignedTasks(assigned);

                    System.out.println("Successfully updated the task status from " + taskToUpdateCurStatus + " to " + newStatus);
                }
                else{
                    System.out.println("Task update Failed");
                }
            }
            else{
                System.out.println("No given Task present");
            }
        }
        else{
            System.out.println("No given Sprint present");
        }
    }

    public void showUserTasks(User assignedUser){
        List<Task> assignedUserTasks = assignedUser.getAssignedTasks();
        System.out.println("Tasks against User = " + assignedUser.getUserId());
        for(Task task : assignedUserTasks){
            System.out.println("- " + task.getTaskId() + " | " + task.getSprintId() + " | " + task.getStatus());
        }
    }

    public boolean checkUserTaskStatus(int sprintId, User assignedUser){
        List<Task> assignedTasks = assignedUser.getAssignedTasks();
        int currentSprintTasks = 0;
        for(Task task : assignedTasks){
            if(task.getSprintId()==sprintId && task.getStatus()==TaskStatus.INPROGRESS){
                currentSprintTasks++;
            }
        }
        return currentSprintTasks<2;
    }
}
