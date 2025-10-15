package com.Mistry.Todo.service;

import com.Mistry.Todo.repo.IToDoRepo;
import com.Mistry.Todo.Model.ToDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ToDOService {

    @Autowired
    IToDoRepo repo;

    public List<ToDO> getAllToDoItems(){
        ArrayList<ToDO> todoList = new ArrayList<>();
        repo.findAll().forEach(toDO -> todoList.add(toDO));
        return todoList;
    }

    public ToDO getToDoItemById(Long id){
        return repo.findById(id).get();
    }

    public boolean updateStatus(Long id){
        ToDO toDO = getToDoItemById(id);
        toDO.setStatus("Completed");
        return saveOrUpdateToDoItem(toDO);
    }

    public boolean saveOrUpdateToDoItem(ToDO todo){
        ToDO updateObj = repo.save(todo);

        if(getToDoItemById(updateObj.getId()) != null){
            return true;
        }
        return false;
    }

    public boolean deleteToDoItem(Long id){
        repo.deleteById(id);

        if(getToDoItemById(id) == null){
            return true;
        }
        return false;
    }


}
