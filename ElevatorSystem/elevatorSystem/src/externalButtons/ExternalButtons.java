package externalButtons;

import elevatorCar.Direction;

public class ExternalButtons {
    ExternalButtonsDispatcher externalButtonsDispatcher;

    public void pressButton(int floor, Direction direction){

        //submit the request to the jobDispatcher
        externalButtonsDispatcher.submitExternalRequest(floor, direction);
    }
}
