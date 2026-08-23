package sa.com.neoleap.Movie.Web.Application.Repo;

import org.springframework.data.repository.CrudRepository;
import sa.com.neoleap.Movie.Web.Application.Module.NowPlayingMovie;


public interface MovieRep extends CrudRepository<NowPlayingMovie, Integer> {
    NowPlayingMovie findById(int id);
}