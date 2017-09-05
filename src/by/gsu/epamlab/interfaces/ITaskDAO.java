package by.gsu.epamlab.interfaces;

import by.gsu.epamlab.exceptions.DaoException;
import by.gsu.epamlab.model.beans.Task;
import by.gsu.epamlab.model.enums.ActionEnum;
import by.gsu.epamlab.model.enums.TaskEnum;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public interface ITaskDAO {
    Map<TaskEnum, List<Task>> getTaskLists(String login) throws DaoException;
    void updateTasks(String login, Task[] tasks, ActionEnum action) throws DaoException;
    void createTask(String login, Task task) throws DaoException;
    void deleteTask(String login, Task[] tasks) throws DaoException;
    void uploadFile(String login, Task task, String realPath, String fileName, InputStream inputStream)throws DaoException;
    InputStream getFileInputStream(String login, Task task, String realPath) throws DaoException;
    void deleteFile(String login, Task task, String realPath) throws DaoException;
}
