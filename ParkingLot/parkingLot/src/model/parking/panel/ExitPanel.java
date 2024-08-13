package model.parking.panel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import model.Ticket.ParkingTicket;
import model.pricing.MinutesPricingStrategy;
import model.pricing.PricingStrategy;
import model.parking.ParkingLot;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class ExitPanel {
    private final String id;
    private final PricingStrategy pricingStrategy;

    public void scanAndVacate(final ParkingTicket parkingTicket) {
        ParkingLot.INSTANCE.vacateParkingSpot(parkingTicket.getAllocatedSpotId());
        parkingTicket.setVacatedAt(LocalDateTime.now());
        parkingTicket.updateAmountCharged(pricingStrategy.calculateAmountCharged(parkingTicket));
    }
}
