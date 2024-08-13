import Enums.City;
import Enums.SeatCategory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BookMyShow {
    MovieController movieController;
    TheatreController theatreController;

    BookMyShow() {
        this.movieController = new MovieController();
        this.theatreController = new TheatreController();
    }

    void initialize() {
        createMovies();

        createTheatres();
    }

    void createTheatres() {

        Movie avengerMovie = movieController.getMovieByName("AVENGERS");
        Movie baahubali = movieController.getMovieByName("BAAHUBALI");

        // create theatres
        Theatre inoxTheatre = new Theatre();
        inoxTheatre.setTheatreId(1);
        inoxTheatre.setAddress("Address");
        inoxTheatre.setCity(City.Bangalore);
        inoxTheatre.setScreen(createScreen());

        List<Show> inoxShows = new ArrayList<>();
        Show inoxMorningShow = createShows(1, inoxTheatre.getScreen().get(0), avengerMovie, 8);
        Show inoxEveningShow = createShows(2, inoxTheatre.getScreen().get(0), baahubali, 16);
        inoxShows.add(inoxMorningShow);
        inoxShows.add(inoxEveningShow);
        inoxTheatre.setShows(inoxShows);

        Theatre pvrTheatre = new Theatre();
        pvrTheatre.setTheatreId(2);
        pvrTheatre.setScreen(createScreen());
        pvrTheatre.setCity(City.Delhi);
        List<Show> pvrShows = new ArrayList<>();
        Show pvrMorningShow = createShows(3, pvrTheatre.getScreen().get(0), avengerMovie, 13);
        Show pvrEveningShow = createShows(4, pvrTheatre.getScreen().get(0), baahubali, 20);
        pvrShows.add(pvrMorningShow);
        pvrShows.add(pvrEveningShow);
        pvrTheatre.setShows(pvrShows);

        theatreController.addTheatre(City.Bangalore, inoxTheatre);
        theatreController.addTheatre(City.Delhi, pvrTheatre);

    }

    private List<Screen> createScreen() {
        List<Screen> screens = new ArrayList<>();
        Screen screen1 = new Screen();
        screen1.setScreenId(1);
        screen1.setSeats(createSeats());
        screens.add(screen1);

        return screens;

    }

    private Show createShows(int showId, Screen screen, Movie movie, int showStartTime){
        Show show = new Show();
        show.setShowId(showId);
        show.setScreen(screen);
        show.setMovie(movie);
        show.setShowStartTime(showStartTime); //24 hrs time ex: 14 means 2pm and 8 means 8AM
        return show;

    }

    private List<Seat> createSeats() {
        //creating 100 seats for testing purpose, this can be generalised
        List<Seat> seats = new ArrayList<>();

        //1 to 40 : SILVER
        for (int i = 0; i < 40; i++) {
            Seat seat = new Seat();
            seat.setSeatId(i);
            seat.setSeatCategory(SeatCategory.SILVER);
            seats.add(seat);
        }

        //41 to 70 : GOLD
        for (int i = 40; i < 70; i++) {
            Seat seat = new Seat();
            seat.setSeatId(i);
            seat.setSeatCategory(SeatCategory.GOLD);
            seats.add(seat);
        }

        //1 to 40 : PLATINUM
        for (int i = 70; i < 100; i++) {
            Seat seat = new Seat();
            seat.setSeatId(i);
            seat.setSeatCategory(SeatCategory.PLATINUM);
            seats.add(seat);
        }

        return seats;

    }

    void createMovies() {
        //create Movies1
        Movie avengers = new Movie();
        avengers.setMovieId(1);
        avengers.setMovieName("AVENGERS");
        avengers.setMovieDurationInMinutes(128);

        //create Movies2
        Movie baahubali = new Movie();
        baahubali.setMovieId(2);
        baahubali.setMovieName("BAAHUBALI");
        baahubali.setMovieDurationInMinutes(180);


        //add movies against the cities
        movieController.addMovie(avengers, City.Bangalore);
        movieController.addMovie(avengers, City.Delhi);
        movieController.addMovie(baahubali, City.Bangalore);
        movieController.addMovie(baahubali, City.Delhi);
    }

    public void createBooking(City userCity, String movieName){

        //1. search movie by my location
        List<Movie> movies = movieController.getMovieByCity(userCity);

        //2. select the movie which you want to see. i want to see Baahubali
        Movie interestedMovie = null;
        for (Movie movie : movies) {

            if ((movie.getMovieName()).equals(movieName)) {
                interestedMovie = movie;
            }
        }

        //3. get all show of this movie in Bangalore location
        Map<Theatre, List<Show>> showsTheatreWise = theatreController.getAllShow(interestedMovie, userCity);

        //4. select the particular show user is interested in
        Map.Entry<Theatre,List<Show>> entry = showsTheatreWise.entrySet().iterator().next();
        List<Show> runningShows = entry.getValue();
        Show interestedShow = runningShows.get(0);

        confirmBooking(30, interestedShow);
//        //5. select the seat
//        int seatNumber = 30;
//        List<Integer> bookedSeatIds = interestedShow.getBookedSeatIds();
//        if(!bookedSeatIds.contains(seatNumber)){
//            bookedSeatIds.add(30);
//            //start payment
//            Booking booking = new Booking();
//            booking.setShow(interestedShow);
//            List<Seat> myBookedSeats = new ArrayList<>();
//            for(Seat screenSeat : interestedShow.getScreen().getSeats()){
//                if(screenSeat.getSeatId() == seatNumber){
//                    myBookedSeats.add(screenSeat);
//                }
//            }
//            booking.setBookedSeats(myBookedSeats);
//        }
//        else {
//            //throw exception
//            System.out.println("seat already booked, try again");
//            return;
//        }
//
//        System.out.println("BOOKING SUCCESSFUL");
    }

    private synchronized void confirmBooking(int seatNumber, Show interestedShow){
        //5. select the seat
        List<Integer> bookedSeatIds = interestedShow.getBookedSeatIds();
        if(!bookedSeatIds.contains(seatNumber)){
            bookedSeatIds.add(30);
            //start payment
            Booking booking = new Booking();
            booking.setShow(interestedShow);
            List<Seat> myBookedSeats = new ArrayList<>();
            for(Seat screenSeat : interestedShow.getScreen().getSeats()){
                if(screenSeat.getSeatId() == seatNumber){
                    myBookedSeats.add(screenSeat);
                }
            }
            booking.setBookedSeats(myBookedSeats);
        }
        else {
            //throw exception
            System.out.println("seat already booked, try again");
            return;
        }

        System.out.println("BOOKING SUCCESSFUL");
    }
}
