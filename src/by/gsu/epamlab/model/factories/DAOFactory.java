package by.gsu.epamlab.model.factories;

import by.gsu.epamlab.exceptions.DaoException;
import by.gsu.epamlab.interfaces.ITaskDAO;
import by.gsu.epamlab.interfaces.IUserDAO;
import by.gsu.epamlab.model.dao.DBTaskDAO;
import by.gsu.epamlab.model.dao.DBUserDAO;

import java.util.HashMap;
import java.util.Map;

public class DAOFactory {
    private static final String NO_DAO_INSTANCE_IN_FACTORY_FOR_CLASS = "No DAO instance in factory for class ";
    private static final Map<Class, Object> DB_DAO = new HashMap<>();

    static {
        DB_DAO.put(IUserDAO.class, new DBUserDAO());
        DB_DAO.put(ITaskDAO.class, new DBTaskDAO());
    }
    public static <T> T getDAO(Class<T> daoClass) throws DaoException {
        Object dao = DB_DAO.get(daoClass);
        if (dao == null){
            throw new DaoException(NO_DAO_INSTANCE_IN_FACTORY_FOR_CLASS + daoClass);
        }
        return daoClass.cast(dao);
    }
}