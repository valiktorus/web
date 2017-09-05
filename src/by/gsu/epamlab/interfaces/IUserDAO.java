package by.gsu.epamlab.interfaces;

import by.gsu.epamlab.exceptions.DaoException;
import by.gsu.epamlab.exceptions.UserAuthenticationException;
import by.gsu.epamlab.model.beans.User;

public interface IUserDAO {
    User getUser(String login, String password) throws DaoException, UserAuthenticationException;
    User registerUser(String login, String password) throws DaoException;
}

