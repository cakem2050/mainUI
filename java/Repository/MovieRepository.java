package Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import Entities.Movie;

public interface MovieRepository extends CrudRepository<Movie, Integer> {
	@Query(value = "SELECT * FROM movie m, type t WHERE m.type_id = t.type_id AND t.type_id = 'action'", nativeQuery = true)
	List<Movie> getMovie();

}
