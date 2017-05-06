package Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import Entities.Users;

public interface UserRepository extends CrudRepository<Users, Integer> {
	List<Users> findByUsernameAndPasswordContains(String username,String password);

}
