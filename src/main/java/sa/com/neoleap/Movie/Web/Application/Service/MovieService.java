package sa.com.neoleap.Movie.Web.Application.Service;

import jakarta.annotation.PostConstruct;
import sa.com.neoleap.Movie.Web.Application.Repo.MovieRep;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import sa.com.neoleap.Movie.Web.Application.Controller.DTO.GetNowPlayingMoviesRs;
import sa.com.neoleap.Movie.Web.Application.Controller.DTO.GetUpcomingMoviesRs;
import sa.com.neoleap.Movie.Web.Application.Module.NowPlayingMovie;
import sa.com.neoleap.Movie.Web.Application.Module.UpcomingMovie;
import java.util.*;

@Service
public class MovieService {
//    private final MovieRepository movieRepository;
//
//    public MovieService(MovieRepository movieRepository) {
//        this.movieRepository = movieRepository;
//    }
//
//    @PostConstruct
//    public void initializeData() {
//        movieRepository.save(new Movie(1, "LE BLUE LE BLUE", "", "Released"));
//        movieRepository.save(new Movie(2, "Adolf Hitler You Said I Was A Dreamer", "\"My spirit will raise from the graves, and everyone will know I was right\" — A.H.", "Released"));
//    }
//
//    public List<Movie> getAllMovies() {
//        return (List<Movie>) movieRepository.findAll();
//    }
//
//    public Movie getMovieById(int id) {
//        return movieRepository.findById(id);
//    }

    public List<NowPlayingMovie> getNowPlayingMovies(String language, int page) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJhZGNhYWI0NjIxMzliYmM5NDFhOTkxODhkOWNhNzExNCIsInN1YiI6IjY2MDE1YzZkNDU5YWQ2MDE4N2ZhY2Q1YyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.nV2q8IhTA7mwnRTVgqV6nYUQkBGxwwR7m5lhMubg638");

        HttpEntity<String> httpEntity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();

        String url = "https://api.themoviedb.org/3/movie/now_playing?language=" + language + "&page=" + page;

        try {
            ResponseEntity<GetNowPlayingMoviesRs> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, GetNowPlayingMoviesRs.class);

            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                List<NowPlayingMovie> nowPlayingMovies = new ArrayList<>();
                for (GetNowPlayingMoviesRs.MovieResult movieResult : responseEntity.getBody().getResults()) {
                    NowPlayingMovie movie = new NowPlayingMovie();
                    movie.setAdult(movieResult.isAdult());
                    movie.setBackdropPath(movieResult.getBackdrop_path());
                    movie.setGenreIds(movieResult.getGenre_ids().stream().mapToInt(Integer::intValue).toArray());
                    movie.setId(movieResult.getId());
                    movie.setOriginalLanguage(movieResult.getOriginal_language());
                    movie.setOriginalTitle(movieResult.getOriginal_title());
                    movie.setOverview(movieResult.getOverview());
                    movie.setPopularity(movieResult.getPopularity());
                    movie.setPosterPath(movieResult.getPoster_path());
                    movie.setReleaseDate(movieResult.getRelease_date());
                    movie.setTitle(movieResult.getTitle());
                    movie.setVideo(movieResult.isVideo());
                    movie.setVoteAverage(movieResult.getVote_average());
                    movie.setVoteCount(movieResult.getVote_count());
                    nowPlayingMovies.add(movie);
                }
                return nowPlayingMovies;
            } else {
                throw new RuntimeException("Failed to fetch now playing movies. Status code: " + responseEntity.getStatusCodeValue());
            }
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            HttpStatus statusCode = (HttpStatus) e.getStatusCode();
            String responseBody = e.getResponseBodyAsString();
            throw new RuntimeException("HTTP error occurred: " + statusCode + ", Response: " + responseBody, e);
        } catch (RestClientException e) {
            throw new RuntimeException("Rest client exception occurred: " + e.getMessage(), e);
        }
    }

    public List<UpcomingMovie> getUpcomingMovies(String language, int page) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJhZGNhYWI0NjIxMzliYmM5NDFhOTkxODhkOWNhNzExNCIsInN1YiI6IjY2MDE1YzZkNDU5YWQ2MDE4N2ZhY2Q1YyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.nV2q8IhTA7mwnRTVgqV6nYUQkBGxwwR7m5lhMubg638");

        HttpEntity<String> httpEntity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();

        String url = "https://api.themoviedb.org/3/movie/upcoming?language=" + language + "&page=" + page;

        try {
            ResponseEntity<GetUpcomingMoviesRs> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, GetUpcomingMoviesRs.class);

            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                List<UpcomingMovie> upcomingMovies = new ArrayList<>();
                for (GetUpcomingMoviesRs.MovieResult movieResult : responseEntity.getBody().getResults()) {
                    UpcomingMovie movie = new UpcomingMovie();
                    movie.setAdult(movieResult.isAdult());
                    movie.setBackdropPath(movieResult.getBackdrop_path());
                    movie.setGenreIds(movieResult.getGenre_ids().stream().mapToInt(Integer::intValue).toArray());
                    movie.setId(movieResult.getId());
                    movie.setOriginalLanguage(movieResult.getOriginal_language());
                    movie.setOriginalTitle(movieResult.getOriginal_title());
                    movie.setOverview(movieResult.getOverview());
                    movie.setPopularity(movieResult.getPopularity());
                    movie.setPosterPath(movieResult.getPoster_path());
                    movie.setReleaseDate(movieResult.getRelease_date());
                    movie.setTitle(movieResult.getTitle());
                    movie.setVideo(movieResult.isVideo());
                    movie.setVoteAverage(movieResult.getVote_average());
                    movie.setVoteCount(movieResult.getVote_count());
                    upcomingMovies.add(movie);
                }
                return upcomingMovies;
            } else {
                throw new RuntimeException("Failed to fetch upcoming movies. Status code: " + responseEntity.getStatusCodeValue());
            }
        } catch (RestClientException e) {
            throw new RuntimeException("Error occurred while fetching upcoming movies: " + e.getMessage(), e);
        }
    }


}
