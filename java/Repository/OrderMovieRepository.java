package Repository;

import org.springframework.data.repository.CrudRepository;

import Entities.OrderMovie;

public interface OrderMovieRepository extends CrudRepository<OrderMovie, Integer> {

}
