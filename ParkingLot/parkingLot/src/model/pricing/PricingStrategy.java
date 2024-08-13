package model.pricing;

import model.Ticket.ParkingTicket;

public interface PricingStrategy {
    double calculateAmountCharged(ParkingTicket parkingTicket);
}
