package Repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import Entities.Movie;

public interface MovieRepository extends CrudRepository<Movie, Integer> {

	@Query(value = "SELECT * FROM movie ORDER BY movie_date DESC LIMIT 8", nativeQuery = true)
	List<Movie> getNewMovie();
	
	@Query(value="SELECT * FROM movie m, type t WHERE m.type_id = t.type_id LIMIT ?1,10", nativeQuery = true)
	List<Movie> getMovielmit(Integer page);

	@Query(value = "SELECT * FROM movie WHERE movie_name LIKE '%?%' ", nativeQuery = true)
	List<Movie> searchMovie(String keyword);

}
