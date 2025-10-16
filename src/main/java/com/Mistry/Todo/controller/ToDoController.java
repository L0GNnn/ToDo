package com.Mistry.Todo.controller;

import com.Mistry.Todo.Model.ToDO;
import com.Mistry.Todo.service.ToDOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ToDoController {

    @Autowired
    private ToDOService service;

    @GetMapping("/test")
    public String testJsp() {
        return "ViewToDoList";
    }

    @GetMapping({"/", "viewToDoList"})
    public String viewAllToDOItems(Model model, @ModelAttribute("message") String message){
        model.addAttribute("list", service.getAllToDoItems());
        model.addAttribute("msg", message);

        return "ViewToDoList";
    }

    @PostMapping("/updateToDoStatus/{id}")
    public String updateToDoStatus(@PathVariable Long id, RedirectAttributes redirectAttributes){
        if(service.updateStatus(id)){
            redirectAttributes.addFlashAttribute("message", "Update Success");
            return "redirect:/viewToDoList";
        }
        return "redirect:/viewToDoList";
    }

    @GetMapping("/addToDoItem")
    public String addToDoItem(Model model){
        model.addAttribute("todo", new ToDO());
        return "AddToDOItem";
    }

    @PostMapping("/saveToDoItem")
    public String saveToDoItem(ToDO todo, RedirectAttributes redirectAttributes){
        if(service.saveOrUpdateToDoItem(todo)){
            redirectAttributes.addFlashAttribute("message", "Save Success");
            return "redirect:/addToDOItem";
        }

        redirectAttributes.addFlashAttribute("message", "Save Failure");
        return "redirect:/addToDoItem";
    }

    @GetMapping("/editToDoItem/{id}")
    public String editToDoItem(@PathVariable Long id, Model model){
        model.addAttribute("todo", service.getToDoItemById(id));

        return "EditToDoItem";
    }

    @PostMapping("/editSaveToDoItem")
    public String editSaveToDoItem(ToDO todo,  RedirectAttributes redirectAttributes){
        if(service.saveOrUpdateToDoItem(todo)){
            redirectAttributes.addFlashAttribute("message", "Save Success");
            return "redirect:/addToDOItem";
        }

        redirectAttributes.addFlashAttribute("message", "Save Failure");
        return "redirect:/editToDoItem" + todo.getId();
    }

    @GetMapping("/deleteTodoItem/{id}")
    public String deleteToDOItem(@PathVariable Long id, RedirectAttributes redirectAttributes){
        if(service.deleteToDoItem(id)){
            redirectAttributes.addFlashAttribute("message", "Delete Success");
        }

        redirectAttributes.addFlashAttribute("message", "Delete Failure");
        return "redirect:/viewToDOList";
    }
}
