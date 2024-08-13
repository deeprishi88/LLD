package floor;

import elevatorCar.Direction;
import externalButtons.ExternalButtons;

public class Floor {
    int floorNumber;
    ExternalButtons externalButtons;

    public Floor(int floorNumber){
        this.floorNumber = floorNumber;
        externalButtons = new ExternalButtons();
    }

    public void pressButton(Direction direction){
        externalButtons.pressButton(floorNumber, direction);
    }
}
