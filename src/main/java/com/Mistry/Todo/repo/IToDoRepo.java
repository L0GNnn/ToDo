package com.Mistry.Todo.repo;

import com.Mistry.Todo.Model.ToDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IToDoRepo extends JpaRepository<ToDO, Long> {
}
