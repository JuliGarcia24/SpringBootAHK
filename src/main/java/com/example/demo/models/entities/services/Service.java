package com.example.demo.models.entities.services;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "service")

@Setter
@Getter
@AllArgsConstructor
@Builder
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "icon", nullable = true)
    private String icon;

    @OneToMany(mappedBy = "service")
    private List<Task> tasks;

    public Service(){
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task){
        this.tasks.add(task);
        task.setService(this);
    }
}
