package elevatorCar;

import java.util.PriorityQueue;
import java.util.Queue;

public class ElevatorController {
    public ElevatorCar elevatorCar;
    PriorityQueue<Integer> upMinPQ;
    PriorityQueue<Integer> downMaxPQ;
    Queue<Integer> pending;

    public ElevatorController(ElevatorCar elevatorCar){
        this.elevatorCar = elevatorCar;
        upMinPQ = new PriorityQueue<>();
        downMaxPQ = new PriorityQueue<>((a,b) -> b-a);
    }

    public void submitExternalRequest(int floor, Direction direction){

        if(direction == Direction.DOWN) {
            downMaxPQ.offer(floor);
        } else {
            upMinPQ.offer(floor);
        }
    }

    public void submitInternalRequest(int floor){

    }

    public void controlElevator(){
        while(true) {

            if(elevatorCar.direction == Direction.UP){


            }
        }
    }


}
