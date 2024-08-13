package model.vehicle;

import lombok.Builder;
import lombok.Getter;
import model.Ticket.ParkingTicket;

@Getter
@Builder
public abstract class Vehicle {
    private final String licenseNumber;
    private final VehicleType vehicleType;
    private ParkingTicket parkingTicket;

    public Vehicle(String licenseNumber, VehicleType vehicleType) {
        this.licenseNumber = licenseNumber;
        this.vehicleType = vehicleType;
    }

    public void assignTicket(ParkingTicket parkingTicket){
        this.parkingTicket=parkingTicket;
    }
}
