import Vehicle.vehicle;

import java.util.Date;

public class Reservation {
    int reservationId;
    User user;
    vehicle v;
    Date bookingDate;
    Date dateBookedFrom;
    Date dateBookedTo;
    Long fromTimeStamp;
    Long toTimeStamp;
    Location pickUpLocation;
    Location dropLocation;
    ReservationType reservationType;
    ReservationStatus reservationStatus;
    Location location;

    public int createReserve(User user, vehicle v){
        //generate new id
        reservationId = 12232;
        this.user = user;
        this.v = v;
        reservationType = ReservationType.DAILY;
        reservationStatus = ReservationStatus.SCHEDULED;
        return reservationId;
    }

    //CRUD operations
}
