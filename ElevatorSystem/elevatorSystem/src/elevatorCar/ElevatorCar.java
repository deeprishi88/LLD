package elevatorCar;

public class ElevatorCar {
    public int id;
    ElevatorDisplay elevatorDisplay;
    Status status;
    Direction direction;
    int currentFloor;
    InternalButtons internalButtons;
    ElevatorDoor elevatorDoor;

    public ElevatorCar(int id){
        this.id = id;
        elevatorDisplay = new ElevatorDisplay();
        internalButtons = new InternalButtons();
        status = Status.IDLE;
        currentFloor = 0;
        direction = Direction.UP;
        elevatorDoor = new ElevatorDoor();
    }

    public void showDisplay() {
        elevatorDisplay.showDisplay();
    }

    public void pressButton(int destination) {
        internalButtons.pressButton(destination, this);
    }

    public void setDisplay() {
        this.elevatorDisplay.setElevatorDisplay(currentFloor, direction);
    }

    boolean moveElevator(Direction dir, int destinationFloor){
        int startFloor = currentFloor;
        if(dir == Direction.UP){
            for(int floor=startFloor;floor<=destinationFloor;floor++){
                this.currentFloor = floor;
                setDisplay();
                showDisplay();
                if(floor == destinationFloor){
                    return true;
                }
            }
        }

        if(dir == Direction.DOWN){
            for(int floor=startFloor;floor>=destinationFloor;floor--){
                this.currentFloor = floor;
                setDisplay();
                showDisplay();
                if(floor==destinationFloor){
                    return true;
                }
            }
        }

        return false;
    }

}
