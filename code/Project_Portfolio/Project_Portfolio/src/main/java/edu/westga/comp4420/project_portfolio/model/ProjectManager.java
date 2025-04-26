package edu.westga.comp4420.project_portfolio.model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ProjectManager {
    private List<Project> projects;

    public ProjectManager() {
        this.projects = new ArrayList<>();
    }

    public void addProject(String name, String description, String hyperlink, File rootDirectory) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Project name cannot be empty");
        }

        Project project = new Project(name, description, hyperlink);
        project.setRootDirectory(rootDirectory);

        this.projects.add(project);
    }

    public void removeProject(Project project) {
        this.projects.remove(project);
    }

    public List<Project> getProjects() {
        return this.projects;
    }
	
	public Project getProjectByName(String name) {
		if (name == null || name.isBlank()) {
			return null;
		}

		for (Project project : this.projects) {
			if (project.getName().equalsIgnoreCase(name)) {
				return project;
			}
		}
		return null;
	}
	
}
