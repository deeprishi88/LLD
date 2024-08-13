import Vehicle.VehicleType;
import Vehicle.vehicle;

import java.util.ArrayList;
import java.util.List;

public class Store {
    int storeId;
    VehicleInventoryManagement vehicleInventoryManagement;
    Location location;
    List<Reservation> reservations = new ArrayList<>();

    public List<vehicle> getVehicles(VehicleType vehicleType){
        return vehicleInventoryManagement.getVehicles();
    }

    //addVehicles, update vehicles, use inventory management to update those

    public void setVehicles(List<vehicle> vehicles){
        vehicleInventoryManagement = new VehicleInventoryManagement(vehicles);
    }

    public Reservation createReservation(vehicle v, User user){
        Reservation reservation = new Reservation();
        reservation.createReserve(user,v);
        reservations.add(reservation);
        return reservation;
    }

    public boolean completeReservation(int reservationID) {

        //take out the reservation from the list and call complete the reservation method.
        return true;
    }

    //update reservation

}
