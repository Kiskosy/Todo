package kosy.todo.todo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import kosy.todo.todo.domain.Issue;

public interface IssueRepository extends CrudRepository<Issue, Integer> {
    
    Issue findByName(String name);

    List<Issue> findAll();

}