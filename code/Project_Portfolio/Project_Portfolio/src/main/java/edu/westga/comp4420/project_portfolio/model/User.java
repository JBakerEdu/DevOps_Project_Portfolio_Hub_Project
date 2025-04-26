package edu.westga.comp4420.project_portfolio.model;

public class User {
    private String username;
    private String password;
    private String email;
    private String description;
    private ProjectManager projectManager;

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.description = "";
        this.projectManager = new ProjectManager();
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getEmail() {
        return this.email;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ProjectManager getProjectManager() {
        return this.projectManager;
    }
}
