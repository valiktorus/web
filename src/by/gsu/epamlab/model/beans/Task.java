package by.gsu.epamlab.model.beans;

import java.sql.Date;

public class Task {
    private int id;
    private String description;
    private  String date;
    private String fileName;

    public Task() {
        id = 0;
        this.description = null;
        this.date = null;
        fileName = null;
    }

    public Task(int id, String description, String date, String fileName) {
        this.id = id;
        this.description = description;
        this.date = date;
        this.fileName = fileName;
    }

    public Task(String description, String date, String fileName) {
        this.description = description;
        this.date = date;
        this.fileName = fileName;
    }

    public Task(String description, Date date, String fileName) {
        this.description = description;
        this.date = date.toString();
        this.fileName = fileName;
    }

    public Task(String description, Date date) {
        this.description = description;
        this.date = date.toString();
        this.fileName = null;
    }

    public Task(String description, String date) {
        this.description = description;
        this.date = date;
        this.fileName = null;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public Date getDate() {
        return Date.valueOf(date);
    }

    @Override
    public String toString() {
        return description;
    }
}
