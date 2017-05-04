package Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import Entities.Movie;

public interface MovieRepository extends CrudRepository<Movie, Integer> {
	@Query(value = "SELECT * FROM movie m, type t WHERE m.type_id = t.type_id AND t.type_name = 'action' LIMIT 8", nativeQuery = true)
	List<Movie> getMovie();
	
	@Query(value ="SELECT * FORM 'movie'", nativeQuery = true)
	List<Movie> getAllMovie();

}
