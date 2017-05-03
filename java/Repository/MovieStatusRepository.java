package Repository;

import org.springframework.data.repository.CrudRepository;

import Entities.MovieStatus;

public interface MovieStatusRepository extends CrudRepository<MovieStatus, Integer> {

}
