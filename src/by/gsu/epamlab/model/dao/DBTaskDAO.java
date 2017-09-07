package by.gsu.epamlab.model.dao;

import by.gsu.epamlab.exceptions.DaoException;
import by.gsu.epamlab.interfaces.ITaskDAO;
import by.gsu.epamlab.model.beans.Task;
import by.gsu.epamlab.model.connector.DBConnector;
import by.gsu.epamlab.model.constants.QueryConstants;
import by.gsu.epamlab.model.enums.ActionEnum;
import by.gsu.epamlab.model.enums.TaskEnum;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBTaskDAO implements ITaskDAO{

    private static final String RESOURCES_PATH = "WEB-INF\\resources\\";
    private static final String SEPARATOR = "\\";
    private static final String FILE_NOT_FOUND = "file not found";
    private static final String ERROR_DURING_DELETING_FILE_NAME = "Error during deleting file name";
    private static final String ERROR_DURING_DELETING_FILE = "Error during deleting file";

    @Override
    public Map<TaskEnum, List<Task>> getTaskLists(String login) throws DaoException {
        Connection connection = null;
        DBConnector dbConnector = new DBConnector();
        try {
            connection = dbConnector.getConnection();
            Map<TaskEnum, List<Task>> taskLists = new HashMap<>();
            for (TaskEnum taskEnum:  TaskEnum.values()) {
                List<Task> tasks = getTaskList(dbConnector, login, taskEnum);
                taskLists.put(taskEnum, tasks);
            }
            return taskLists;
        } catch (SQLException e) {
            throw new DaoException(QueryConstants.ERROR_IN_QUERY_DURING_GETTING_TASKS, e);
        } finally {
            dbConnector.closeConnection(connection);
        }
    }

    @Override
    public void updateTasks(String login, Integer[] idTasks, ActionEnum action) throws DaoException {
        final int ACTION_INDEX = 1;
        final int ID_INDEX = 2;
        final int LOGIN_INDEX = 3;
        DBConnector dbConnector = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            dbConnector = new DBConnector();
            connection = dbConnector.getConnection();
            preparedStatement = connection.prepareStatement(QueryConstants.UPDATE_TASK);
            for (Integer idTask: idTasks) {
                preparedStatement.setString(ACTION_INDEX, action.toString());
                preparedStatement.setInt(ID_INDEX, idTask);
                preparedStatement.setString(LOGIN_INDEX, login);
                preparedStatement.executeUpdate();
            }
        }catch (SQLException e) {
            throw new DaoException(QueryConstants.ERROR_IN_QUERY_DURING_UPDATING_TASKS, e);
        }finally {
            if (dbConnector !=null){
                dbConnector.closeConnection(connection, preparedStatement);
            }
        }
    }

    @Override
    public void createTask(String login, Task task) throws DaoException {
        final int INSERT_LOGIN_INDEX = 1;
        final int SELECT_TASK_ID_INDEX = 1;
        final int INSERT_TASK_ID_INDEX = 2;
        final int TASK_DESCRIPTION_INDEX = 1;
        final int INSERT_DATE_INDEX = 3;
        DBConnector dbConnector = new DBConnector();
        Connection connection = null;
        PreparedStatement psSelectTask = null;
        PreparedStatement psInsertTask = null;
        PreparedStatement psInsertUserTask = null;
        ResultSet resultSet = null;
        try {
            connection = dbConnector.getConnection();
            psSelectTask = connection.prepareStatement(QueryConstants.GET_TASK_ID_BY_DESCRIPTION);
            psInsertTask = connection.prepareStatement(QueryConstants.CREATE_TASK_DESCRIPTION);
            psInsertUserTask = connection.prepareStatement(QueryConstants.CREATE_USER_TASK);
            psSelectTask.setString(TASK_DESCRIPTION_INDEX, task.getDescription());
            resultSet = psSelectTask.executeQuery();
            if (!resultSet.next()){
                psInsertTask.setString(TASK_DESCRIPTION_INDEX, task.getDescription());
                psInsertTask.executeUpdate();
                resultSet = psSelectTask.executeQuery();
                resultSet.next();
            }
            int taskId = resultSet.getInt(SELECT_TASK_ID_INDEX);
            psInsertUserTask.setString(INSERT_LOGIN_INDEX, login);
            psInsertUserTask.setInt(INSERT_TASK_ID_INDEX, taskId);
            psInsertUserTask.setDate(INSERT_DATE_INDEX, task.getDate());
            psInsertUserTask.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(QueryConstants.ERROR_IN_QUERY_DURING_CREATING_TASK, e);
        }finally {
            dbConnector.closeConnection(resultSet);
            dbConnector.closeConnection(psInsertTask, psInsertUserTask, psSelectTask);
            dbConnector.closeConnection(connection);
        }
    }

    @Override
    public void deleteTask(String login, Integer[] idTasks) throws DaoException {
        final int ID_INDEX = 1;
        final int LOGIN_INDEX = 2;
        DBConnector dbConnector = new DBConnector();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            connection = dbConnector.getConnection();
            preparedStatement = connection.prepareStatement(QueryConstants.DELETE_TASK);
            for (Integer idTask: idTasks) {
                preparedStatement.setInt(ID_INDEX, idTask);
                preparedStatement.setString(LOGIN_INDEX, login);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DaoException(QueryConstants.ERROR_IN_QUERY_DURING_DELETING_TASKS, e);
        }finally {
            dbConnector.closeConnection(connection, preparedStatement);
        }
    }

    @Override
    public void uploadFile(String login, Integer idTask, String realPath, String fileName, InputStream inputStream) throws DaoException {
        final int FILE_PATH_INDEX = 1;
        final int LOGIN_INDEX = 2;
        final int ID_TASK_INDEX = 3;

        String dir = realPath + RESOURCES_PATH + login ;
        String uniqueFileName = generateUniqueFileName(fileName, idTask);
        String filePath = dir + SEPARATOR + uniqueFileName;
        File userDirectory = new File(dir);
        userDirectory.mkdir();
        try(BufferedInputStream bis = new BufferedInputStream(inputStream);
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File(filePath)))) {
            int data;
            while ((data = bis.read()) !=-1){
                bos.write(data);
            }
        } catch (IOException e) {
            throw new DaoException(QueryConstants.ERROR_DURING_UPLOADING_FILE, e);
        }
        DBConnector dbConnector = new DBConnector();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dbConnector.getConnection();
            preparedStatement = connection.prepareStatement(QueryConstants.UPLOAD_FILE);
            preparedStatement.setString(FILE_PATH_INDEX, fileName);
            preparedStatement.setString(LOGIN_INDEX, login);
            preparedStatement.setInt(ID_TASK_INDEX, idTask);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(QueryConstants.ERROR_DURING_UPLOADING_FILE, e);
        }finally {
            dbConnector.closeConnection(connection, preparedStatement);
        }
    }

    @Override
    public InputStream getFileInputStream(String login, String fileName, int idTask, String realPath) throws DaoException {
        String uniqueFileName = generateUniqueFileName(fileName, idTask);
        String filePath = realPath + RESOURCES_PATH + login + SEPARATOR + uniqueFileName;
        try {
            return new FileInputStream(new File(filePath));
        } catch (FileNotFoundException e) {
            throw new DaoException(FILE_NOT_FOUND, e);
        }
    }

    @Override
    public void deleteFile(String login, String fileName, int idTask, String realPath) throws DaoException {
        final int LOGIN_INDEX = 1;
        final int ID_TASK_INDEX = 2;

        DBConnector dbConnector = new DBConnector();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dbConnector.getConnection();
            preparedStatement = connection.prepareStatement(QueryConstants.DELETE_FILE);
            preparedStatement.setString(LOGIN_INDEX, login);
            preparedStatement.setInt(ID_TASK_INDEX, idTask);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(ERROR_DURING_DELETING_FILE_NAME, e);
        }finally {
            dbConnector.closeConnection(connection, preparedStatement);
        }
        String uniqueFileName = generateUniqueFileName(fileName, idTask);
        String filePath = realPath + RESOURCES_PATH + login + SEPARATOR + uniqueFileName;
        File fileToDelete = new File(filePath);
        if (!fileToDelete.delete()){
            throw new DaoException(ERROR_DURING_DELETING_FILE);
        }
    }

    private String generateUniqueFileName(String fileName, int idTask){
        return idTask + fileName;
    }

    private static List<Task> getTaskList(DBConnector dbConnector, String login, TaskEnum taskEnum) throws SQLException, DaoException {
        final int LOGIN_INDEX = 1;
        final int STATUS_INDEX = 2;
        final int ID_INDEX = 1;
        final int TASK_DESCRIPTION_INDEX = 2;
        final int DATE_INDEX = 3;
        final int FILE_NAME_INDEX = 4;

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Task> tasks = new ArrayList<>();
        try{
            Connection connection = dbConnector.getConnection();
            preparedStatement = connection.prepareStatement(taskEnum.getQuery());
            preparedStatement.setString(LOGIN_INDEX, login);
            preparedStatement.setString(STATUS_INDEX, taskEnum.getStatus());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt(ID_INDEX);
                String taskDescription = resultSet.getString(TASK_DESCRIPTION_INDEX);
                Date date = resultSet.getDate(DATE_INDEX);
                String fileName = resultSet.getString(FILE_NAME_INDEX);
                tasks.add(new Task(id, taskDescription, date, fileName));
            }
        }finally {
            if (dbConnector != null) {
                dbConnector.closeConnection(preparedStatement, resultSet);
            }
        }
        return tasks;
    }
}
