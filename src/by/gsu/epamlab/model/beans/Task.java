package by.gsu.epamlab.model.beans;

import java.sql.Date;

public class Task {
    private String description;
    private  String date;

    public Task() {
        this.description = "";
        this.date = "";
    }

    public Task(String description, Date date) {
        this.description = description;
        this.date = date.toString();
    }

    public Task(String description, String date) {
        this.description = description;
        this.date = date;

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
