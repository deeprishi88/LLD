package model.parking.panel;

import exception.InvalidParkingFloorException;
import exception.ParkingFullException;
import model.vehicle.Vehicle;
import model.Ticket.ParkingTicket;
import model.parking.spot.ParkingSpot;
import model.parking.ParkingLot;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@Getter
public class EntryPanel {
    private final String id;

    public ParkingTicket getTicket(final Vehicle vehicle) throws ParkingFullException, InvalidParkingFloorException {
        final ParkingSpot parkingSpot = ParkingLot.INSTANCE.getParkingSpot(vehicle);
        return buildParkingTicket(vehicle.getLicenseNumber(), parkingSpot.getId());
    }

    private ParkingTicket buildParkingTicket(String licenseNumber, String allocatedSpotId) {
        return ParkingTicket.builder()
                .id(UUID.randomUUID().toString())
                .issuedAt(LocalDateTime.now())
                .assignedVehicleId(licenseNumber)
                .allocatedSpotId(allocatedSpotId)
                .build();
    }

}
