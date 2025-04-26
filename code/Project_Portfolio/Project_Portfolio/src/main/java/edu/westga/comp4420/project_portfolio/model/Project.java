package edu.westga.comp4420.project_portfolio.model;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Project {
    private String name;
    private String description;
    private String hyperlink;
    private LocalDateTime lastEdited;
    private File rootDirectory;

    public Project(String name, String description, String hyperlink) {
        this.name = name;
        this.description = description;
        this.hyperlink = hyperlink;
        this.lastEdited = LocalDateTime.now();
        this.rootDirectory = null;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
        this.updateLastEdited();
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
        this.updateLastEdited();
    }

    public String getHyperlink() {
        return this.hyperlink;
    }

    public void setHyperlink(String hyperlink) {
        this.hyperlink = hyperlink;
        this.updateLastEdited();
    }

    public LocalDateTime getLastEdited() {
        return this.lastEdited;
    }

    public String getFormattedLastEdited() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return this.lastEdited.format(formatter);
    }

    public File getRootDirectory() {
        return this.rootDirectory;
    }

    public void setRootDirectory(File rootDirectory) {
        this.rootDirectory = rootDirectory;
        this.updateLastEdited();
    }

    private void updateLastEdited() {
        this.lastEdited = LocalDateTime.now();
    }
}
