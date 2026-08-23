package sa.com.neoleap.Movie.Web.Application.Controller.DTO;

import lombok.Data;
import java.util.List;

@Data
public class GetUpcomingMoviesRs {
    private Dates dates;
    private int page;
    private List<MovieResult> results;
    private int totalPages;
    private int totalResults;

    @Data
    static class Dates {
        private String maximum;
        private String minimum;
    }

    @Data
    public static class MovieResult {
        private boolean adult;
        private String backdrop_path;
        private List<Integer> genre_ids;
        private int id;
        private String original_language;
        private String original_title;
        private String overview;
        private double popularity;
        private String poster_path;
        private String release_date;
        private String title;
        private boolean video;
        private double vote_average;
        private int vote_count;
    }
}
