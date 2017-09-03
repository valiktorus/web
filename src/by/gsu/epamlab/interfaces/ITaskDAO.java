package by.gsu.epamlab.interfaces;

import by.gsu.epamlab.exceptions.DaoException;
import by.gsu.epamlab.model.beans.Task;
import by.gsu.epamlab.model.enums.TaskEnum;

import java.util.List;
import java.util.Map;

public interface ITaskDAO {
    Map<TaskEnum, List<Task>> getTaskLists(String login) throws DaoException;
    void updateTasks(String login, Task[] tasks, String action) throws DaoException;
    void createTask(String login, Task task) throws DaoException;
    void deleteTask(String login, Task[] tasks) throws DaoException;
}
