package Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import Entities.Movie;

public interface MovieRepository extends CrudRepository<Movie, Integer> {
	
	
	@Query(value = "SELECT * FROM movie ORDER BY movie_date DESC LIMIT 8", nativeQuery = true)
	List<Movie> getNewMovie();
	
	@Query(value = "SELECT * FROM movie m, type t WHERE m.type_id = t.type_id AND t.type_name = 'action' LIMIT 8", nativeQuery = true)
	List<Movie> getMovie();

}
