package Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import Entities.Users;

public interface UsersRepository extends CrudRepository<Users, Integer> {
	List<Users> findByEmailAndPassword(String email,String password);
}
