package com.nick.pm.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "projects")
public class Project {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User userId;

//    @ManyToMany
//    @JoinTable(name = "project_developers", joinColumns = {@JoinColumn(name = "project_id")}
//            , inverseJoinColumns = {@JoinColumn(name = "user_id")})
//    private List<User> developers;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "post_date")
    private String postDate;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "projectId")
    private List<Task> projectTasks;

    public Project() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

//    public List<User> getDevelopers() {
//        return developers;
//    }
//
//    public void setDevelopers(List<User> developers) {
//        this.developers = developers;
//    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Task> getProjectTasks() {
        return projectTasks;
    }

    public void setProjectTasks(List<Task> projectTasks) {
        this.projectTasks = projectTasks;
    }
}
