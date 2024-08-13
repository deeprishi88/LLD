package model.parking;

import exception.InvalidParkingFloorException;
import exception.ParkingFullException;
import model.account.common.Address;
import model.parking.panel.EntryPanel;
import model.parking.panel.ExitPanel;
import model.parking.spot.ParkingSpot;
import model.vehicle.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ParkingLot {
    private final String id;
    private Address address;
    private final List<ParkingFloor> parkingFloors;
    private final List<EntryPanel> entryPanels;
    private final List<ExitPanel> exitPanels;

    public static final ParkingLot INSTANCE = new ParkingLot();

    private ParkingLot() {
        this.id = UUID.randomUUID().toString();
        this.parkingFloors = new ArrayList<>();
        this.entryPanels = new ArrayList<>();
        this.exitPanels = new ArrayList<>();
    }

    public void updateAddress(final Address address) {
        this.address = address;
    }

    public void addFloor(final ParkingFloor parkingFloor) {
        final Optional<ParkingFloor> floor = parkingFloors.stream()
                .filter(pF -> pF.getId().equals(parkingFloor.getId()))
                .findFirst();
        if (floor.isPresent())
            return;
        parkingFloors.add(parkingFloor);
    }

    public void addEntryPanel(final EntryPanel entryPanel) {
        final Optional<EntryPanel> panel = entryPanels.stream()
                .filter(eP -> eP.getId().equals(entryPanel.getId()))
                .findFirst();
        if (panel.isPresent())
            return;
        entryPanels.add(entryPanel);
    }

    public void addExitPanel(final ExitPanel exitPanel) {
        final Optional<ExitPanel> panel = exitPanels.stream()
                .filter(eP -> eP.getId().equals(exitPanel.getId()))
                .findFirst();
        if (panel.isPresent())
            return;
        exitPanels.add(exitPanel);
    }

    public void addParkingSpot(final String floorId, final ParkingSpot parkingSpot) throws InvalidParkingFloorException {
        final Optional<ParkingFloor> parkingFloor = parkingFloors.stream()
                .filter(pF -> pF.getId().equals(floorId))
                .findFirst();
        if (!parkingFloor.isPresent())
            throw new InvalidParkingFloorException("Parking Floor with id " + floorId + " doesn't exists");
        parkingFloor.get().addParkingSpot(parkingSpot);
    }

    public ParkingSpot getParkingSpot(final Vehicle vehicle) throws ParkingFullException, InvalidParkingFloorException {
        final Optional<ParkingFloor> parkingFloor = parkingFloors.stream()
                .filter(pF -> pF.canPark(vehicle.getVehicleType()))
                .findFirst();
        if (!parkingFloor.isPresent())
            throw new ParkingFullException("Sorry! Parking is full");
        return parkingFloor.get().getParkingSpot(vehicle);
    }

    public void vacateParkingSpot(final String parkingSpotId) {
        parkingFloors.stream()
                .filter(parkingFloor -> parkingFloor.vacateParkingSpot(parkingSpotId).isPresent())
                .findFirst();
    }
}
