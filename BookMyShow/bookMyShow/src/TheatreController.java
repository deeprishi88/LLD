import Enums.City;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TheatreController {
    Map<City, List<Theatre>> cityVsTheatre;
    List<Theatre> allTheatre;

    TheatreController() {
        cityVsTheatre = new HashMap<>();
        allTheatre = new ArrayList<>();
    }

    void addTheatre(City city, Theatre theatre){
        allTheatre.add(theatre);

        List<Theatre> theatres = cityVsTheatre.getOrDefault(city, new ArrayList<>());
        theatres.add(theatre);
        cityVsTheatre.put(city, theatres);
    }

    Map<Theatre, List<Show>> getAllShow(Movie movie, City city) {
        // get all theatres of city
        List<Theatre> theatres = cityVsTheatre.get(city);
        
        Map<Theatre, List<Show>> showsVsTheatre = new HashMap<>();
        
        for(Theatre theatre : theatres){
            List<Show> shows = theatre.getShows();
            List<Show> requiredShows = new ArrayList<>();

            for(Show showObj : shows){
                if(showObj.getMovie().equals(movie)){
                    requiredShows.add(showObj);
                }
            }

            if(!requiredShows.isEmpty()) {
                showsVsTheatre.put(theatre, requiredShows);
            }
        }
        
        return showsVsTheatre;
    }

}
