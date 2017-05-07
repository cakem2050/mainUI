package Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import Entities.Type;

public interface TypeRepository extends CrudRepository<Type, Integer> {
	@Query(value = "SELECT * FROM type WHERE type_id = ? ", nativeQuery = true)
	List<Type> gettypeName(Integer type_id);
}
