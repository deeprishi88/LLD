import Enums.City;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        BookMyShow bookMyShow = new BookMyShow();
        bookMyShow.initialize();

        //user1
        bookMyShow.createBooking(City.Bangalore, "BAAHUBALI");

        //user2
        bookMyShow.createBooking(City.Bangalore, "BAAHUBALI");
    }
}