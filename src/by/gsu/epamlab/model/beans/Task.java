package by.gsu.epamlab.model.beans;

import java.sql.Date;

public class Task {
    private final int id;
    private final String description;
    private final Date date;
    private final String fileName;

    public Task() {
        id = 0;
        this.description = null;
        this.date = null;
        fileName = null;
    }

    public Task(int id, String description, Date date, String fileName) {
        this.id = id;
        this.description = description;
        this.date = date;
        this.fileName = fileName;
    }

    public Task(int id, String description, String date, String fileName) {
        this(id, description, Date.valueOf(date), fileName);
    }

    public Task(String description, String date) {
        this(description, Date.valueOf(date));
    }

    public Task(String description, Date date) {
        this(0, description, date, null);
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Date getDate() {
        return date;
    }

    public String getFileName() {
        return fileName;
    }

    @Override
    public String toString() {
        return description;
    }
}
