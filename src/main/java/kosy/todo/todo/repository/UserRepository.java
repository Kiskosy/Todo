package kosy.todo.todo.repository;

import org.springframework.data.repository.CrudRepository;

import kosy.todo.todo.domain.User;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findByName(String name);

}