package model.pricing;

import model.Ticket.ParkingTicket;

import java.time.Duration;

public class HourlyPricingStrategy implements PricingStrategy{
    private static final double PRICE_PER_HOUR = 1.0;

    @Override
    public double calculateAmountCharged(ParkingTicket parkingTicket) {
        final long hoursParked = Duration.between(parkingTicket.getIssuedAt(), parkingTicket.getVacatedAt())
                .toHours();
        return PRICE_PER_HOUR + hoursParked > 0 ? (hoursParked - 1) * PRICE_PER_HOUR : 0;
    }
}
