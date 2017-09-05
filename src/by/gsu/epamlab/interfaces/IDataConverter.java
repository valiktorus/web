package by.gsu.epamlab.interfaces;

import by.gsu.epamlab.model.beans.Task;

public interface IDataConverter {
    Task[] getTasksFromJson(String json);
}
