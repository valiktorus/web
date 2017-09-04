package by.gsu.epamlab.model.constants;

public class QueryConstants {
    public static final String GET_USER_QUERY = "select login, password from users where login = ? and password = ?;";
    public static final String CHECK_USER_QUERY = "select login from users where login = ?;";


    public static final String USER_REGISTRATION_QUERY = "insert into web.users (login, password) values (?, ?);";

    public static final String GET_TASK_QUERY_START = "SELECT tasks.task, date, fileName from usertasks" +
            " inner join tasks on tasks.id = idTask" +
            " inner join users on users.id = idUser" +
            " inner join taskstatuses on taskstatuses.id = idTaskStatus " +
            "where users.login = ? ";
    public static final String GET_TASK_QUERY_END = " and taskstatuses.status = ?;";
    public static final String GET_TASK_ID_BY_DESCRIPTION = "SELECT tasks.id FROM tasks WHERE tasks.task = ?;";
    public static final String CREATE_TASK_DESCRIPTION = "INSERT INTO tasks (tasks.task) VALUES(?);";
    public static final String CREATE_USER_TASK = "INSERT INTO usertasks (usertasks.idUser, usertasks.idTask, usertasks.date, usertasks.idTaskStatus) " +
            "VALUES (" +
            "(SELECT users.id FROM users WHERE users.login = ?),?,?, " +
            "(SELECT taskstatuses.id FROM taskstatuses WHERE taskstatuses.status = 'actual'));";


    public static final String UPDATE_TASK = "update usertasks " +
            "inner join users on users.id = idUser " +
            "inner join tasks on tasks.id = idTask " +
            "inner join taskstatuses on taskstatuses.id = idTaskStatus " +
            "set usertasks.idTaskStatus = (SELECT taskstatuses.id FROM taskstatuses WHERE taskstatuses.status = ?) " +
            "where users.login = ?  and tasks.task = ? and usertasks.date = ?;";

    public static final String DELETE_TASK = "DELETE FROM usertasks " +
            "WHERE idUser = (SELECT users.id FROM users WHERE login = ?) " +
            "AND idTask = (SELECT tasks.id FROM tasks WHERE tasks.task = ?) " +
            "AND usertasks.date = ?  " +
            "AND idTaskStatus = 3;";

    public static final String UPLOAD_FILE = "update usertasks " +
            "inner join users on users.id = idUser " +
            "inner join tasks on tasks.id = idTask " +
            "set usertasks.fileName = ? " +
            "where users.login = ? and tasks.task = ? and usertasks.date = ?;";

    public static final String DELETE_FILE = "UPDATE usertasks " +
            "INNER JOIN tasks ON tasks.id = idTask " +
            "INNER JOIN users ON users.id = idUser " +
            "SET fileName = NULL " +
            "WHERE users.login = ? " +
            "AND tasks.task = ? " +
            "AND date = ? " +
            "AND fileName = ?;";

    public static final String WRONG_LOGIN_OR_PASSWORD = "Wrong login or password";
    public static final String ERROR_IN_QUERY_DURING_LOGIN = "Error in query during login";
    public static final String ERROR_IN_QUERY_DURING_REGISTRATION = "Error in query during registration";
    public static final String ERROR_IN_QUERY_DURING_GETTING_TASKS = "Error in query during getting tasks";
    public static final String ERROR_IN_QUERY_DURING_UPDATING_TASKS = "Error in query during updating tasks";
    public static final String ERROR_IN_QUERY_DURING_DELETING_TASKS = "Error in query during deleting tasks";
    public static final String ERROR_IN_QUERY_DURING_CREATING_TASK = "Error in query during creating tasks";
    public static final String ERROR_DURING_UPLOADING_FILE = "Error during uploading file";
    public static final String ERROR_CONNECTING_TO_DB = "Error connecting to db";


}