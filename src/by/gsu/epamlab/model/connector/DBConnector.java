package by.gsu.epamlab.model.connector;

import by.gsu.epamlab.exceptions.DaoException;
import by.gsu.epamlab.model.constants.DBConstants;

import java.sql.*;

public class DBConnector {
    private Connection connection;

    public DBConnector() throws DaoException {
        try {
            Class.forName(DBConstants.MYSQL_JDBC_DRIVER);
            String connectionURL = DBConstants.PROTOCOL + DBConstants.HOST_NAME + DBConstants.PORT + DBConstants.DB_NAME;
            connection = DriverManager.getConnection(connectionURL, DBConstants.USER_NAME, DBConstants.PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            throw new DaoException(DBConstants.ERROR_DURING_GETTING_CONNECTION, e);
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection(Connection connection, Statement statement, ResultSet resultSet) throws DaoException {
        closeConnection(connection, statement);
        closeConnection(resultSet);
    }
    public void closeConnection(Connection connection, Statement statement) throws DaoException {
        closeConnection(connection);
        closeConnection(statement);
    }
    public void closeConnection(Connection connection) throws DaoException {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DaoException(DBConstants.ERROR_DURING_CLOSING_RESOURCES, e);
            }
        }
    }
    public void closeConnection(Statement statement) throws DaoException {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new DaoException(DBConstants.ERROR_DURING_CLOSING_RESOURCES, e);
            }
        }
    }
    public void closeConnection(Statement ... statements) throws DaoException {
        for (Statement statement: statements) {
            closeConnection(statement);
        }
    }
    public void closeConnection(ResultSet resultSet) throws DaoException {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new DaoException(DBConstants.ERROR_DURING_CLOSING_RESOURCES, e);
            }
        }
    }
    public void closeConnection(Statement statement, ResultSet resultSet) throws DaoException {
       closeConnection(resultSet);
       closeConnection(statement);
    }

}
