package sa.com.neoleap.Movie.Web.Application.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sa.com.neoleap.Movie.Web.Application.Module.NowPlayingMovie;
import sa.com.neoleap.Movie.Web.Application.Module.UpcomingMovie;
import sa.com.neoleap.Movie.Web.Application.Service.MovieService;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:8080")
public class MovieController {

    private final MovieService movieService;

    @GetMapping("")
    public String start() {
        return "Haifa Movie App Backend";
    }

    @GetMapping("/movie/now_playing")
    public ResponseEntity<?> getNowPlayingMovies(@RequestParam String language, @RequestParam int page) {
        try {
            List<NowPlayingMovie> nowPlayingMovies = movieService.getNowPlayingMovies(language, page);
            return ResponseEntity.ok(nowPlayingMovies);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred: " + e.getMessage());
        }
    }

    @GetMapping("/movie/upcoming")
    public ResponseEntity<?> getUpcomingMovies(@RequestParam String language, @RequestParam int page) {
        try {
            List<UpcomingMovie> upcomingMovies = movieService.getUpcomingMovies(language, page);
            return ResponseEntity.ok(upcomingMovies);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred: " + e.getMessage());
        }
    }


//
//    //GET ALL MOVIES
//    @GetMapping("/movies")
//    public List<Movie> getAllMovies() {
//        return movieService.getAllMovies();
//    }
//
//    //GET MOVIE BY ID
//    @GetMapping(value = "/movies/{id}", produces = "application/json")
//    public ResponseEntity<?> getMovieById(@PathVariable int id) {
//        Movie movie = movieService.getMovieById(id);
//        if (movie != null) {
//            return ResponseEntity.ok(movie);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
}

