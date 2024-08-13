import Vehicle.vehicle;

import java.util.List;

public class VehicleInventoryManagement {
    List<vehicle> vehicleList;

    public VehicleInventoryManagement(List<vehicle> list){
        this.vehicleList = list;
    }

    public List<vehicle> getVehicles() {
        //filtering
        return vehicleList;
    }

    public void setVehicles(List<vehicle> vehicles) {
        this.vehicleList = vehicles;
    }
}
