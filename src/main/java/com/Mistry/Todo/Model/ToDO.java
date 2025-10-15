package com.Mistry.Todo.Model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table (name = "todo")
public class ToDO {
    public ToDO() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Column
    private Long id;

    @Column
    private String title;

    @Column
    private Date date;

    @Column
    private String status;
}
