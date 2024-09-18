import User.UserController;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        UserController userController = new UserController();
        userController.addUser(1, "A");
        userController.addUser(2, "B");
        userController.addUser(3, "C");
        userController.addUser(4, "D");
        userController.addUser(5, "E");
        userController.addUser(6, "F");
        userController.addUser(7, "G");
        userController.addUser(8, "H");

        SprintPlanner sprintPlanner = new SprintPlanner(userController);
        sprintPlanner.demo();
    }
}