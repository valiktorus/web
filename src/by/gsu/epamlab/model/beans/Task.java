package by.gsu.epamlab.model.beans;

import java.sql.Date;

public class Task {
    private final String description;
    private final String date;

    public Task(String description, Date date) {
        this.description = description;
        this.date = date.toString();
    }

    public Task(String description, String date) {
        this.description = description;
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
