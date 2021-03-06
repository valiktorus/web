package by.gsu.epamlab.interfaces;

import by.gsu.epamlab.exceptions.DaoException;
import by.gsu.epamlab.model.beans.Task;
import by.gsu.epamlab.model.enums.ActionEnum;
import by.gsu.epamlab.model.enums.TaskEnum;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public interface ITaskDAO {
    Map<TaskEnum, List<Task>> getTaskLists(String login) throws DaoException;
    void updateTasks(String login, Integer[] idTasks, ActionEnum action) throws DaoException;
    void createTask(String login, Task task) throws DaoException;
    void deleteTask(String login, Integer[] idTasks) throws DaoException;
    void uploadFile(String login, Integer idTask, String realPath, String fileName, InputStream inputStream)throws DaoException;
    InputStream getFileInputStream(String login, String fileName, int idTask, String realPath) throws DaoException;
    void deleteFile(String login, String fileName, int idTask, String realPath) throws DaoException;
}
