package Repository;

import org.springframework.data.repository.CrudRepository;

import Entities.Movie;

public interface MovieRepository extends CrudRepository<Movie, Integer> {

}
