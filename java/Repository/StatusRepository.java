package Repository;

import org.springframework.data.repository.CrudRepository;

import Entities.Status;

public interface StatusRepository extends CrudRepository<Status, Integer> {

}
