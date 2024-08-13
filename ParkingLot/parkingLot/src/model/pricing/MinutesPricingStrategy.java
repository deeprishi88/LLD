package model.pricing;

import model.Ticket.ParkingTicket;

import java.time.Duration;

public class MinutesPricingStrategy implements PricingStrategy{
    private static final double PRICE_PER_MINUTE = 1.0;

    @Override
    public double calculateAmountCharged(ParkingTicket parkingTicket) {
        final long minutesParked = Duration.between(parkingTicket.getIssuedAt(), parkingTicket.getVacatedAt())
                .toMinutes();
        return PRICE_PER_MINUTE + minutesParked > 0 ? (minutesParked - 1) * PRICE_PER_MINUTE : 0;
    }
}
