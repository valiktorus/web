package by.gsu.epamlab.model.dao;

import by.gsu.epamlab.exceptions.DaoException;
import by.gsu.epamlab.interfaces.IUserDAO;
import by.gsu.epamlab.model.beans.User;
import by.gsu.epamlab.model.connector.DBConnector;
import by.gsu.epamlab.model.constants.DBConstants;
import by.gsu.epamlab.model.constants.QueryConstants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUserDAO implements IUserDAO {

    public static final int LOGIN_INDEX = 1;
    public static final int PASSWORD_INDEX = 2;

    @Override
    public User getUser(String login, String password) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        DBConnector dbConnector = null;
        try {
            dbConnector = new DBConnector();
            connection = dbConnector.getConnection();
            preparedStatement = connection.prepareStatement(QueryConstants.GET_USER_QUERY);
            preparedStatement.setString(LOGIN_INDEX, login);
            preparedStatement.setString(PASSWORD_INDEX, password);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return new User(login, password);
            }else {
                throw new DaoException(QueryConstants.WRONG_LOGIN_OR_PASSWORD);
            }
        } catch (SQLException e) {
            throw new DaoException(QueryConstants.ERROR_IN_QUERY_DURING_LOGIN, e);
        }finally {
            if (dbConnector != null){
                dbConnector.closeConnection(connection, preparedStatement, resultSet);
            }
        }
    }

    @Override
    public User registerUser(String login, String password) throws DaoException {
        DBConnector dbConnector = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatementChecker = null;
        ResultSet resultSet = null;
        try {
            dbConnector = new DBConnector();
            connection = dbConnector.getConnection();
            preparedStatementChecker = connection.prepareStatement(QueryConstants.CHECK_USER_QUERY);
            preparedStatementChecker.setString(LOGIN_INDEX, login);
            preparedStatement = connection.prepareStatement(QueryConstants.USER_REGISTRATION_QUERY);
            preparedStatement.setString(LOGIN_INDEX, login);
            preparedStatement.setString(PASSWORD_INDEX, password);
            synchronized (DBUserDAO.class) {
                resultSet = preparedStatementChecker.executeQuery();
                if (resultSet.next()){
                    throw new DaoException(DBConstants.USER_ALREADY_EXISTS);
                }
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DaoException(QueryConstants.ERROR_IN_QUERY_DURING_REGISTRATION, e);
        }finally {
            if (dbConnector != null){
                dbConnector.closeConnection(connection, preparedStatement);
                dbConnector.closeConnection(preparedStatementChecker, resultSet);
            }
        }
        return new User(login, password);
    }
}