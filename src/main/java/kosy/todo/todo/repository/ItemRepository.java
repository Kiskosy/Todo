package kosy.todo.todo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import kosy.todo.todo.domain.Item;

public interface ItemRepository extends CrudRepository<Item, Integer> {
    
    List<Item> findAll();

}