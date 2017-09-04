package by.gsu.epamlab.controllers;

public class ControllerConstants {

    public static final String LOGIN_OR_PASSWORD_ABSENT_ERROR = "login or password absent error";
    public static final String LOGIN_ERROR_EMPTY = "Login is empty.";
    public static final String PASSWORD_AND_PASSWORD_REPEAT_ARE_NOT_EQUALS = "Password and password repeat are not equals";
    public static final String EMPTY_LINE = "";


    public static final String KEY_ERROR_MESSAGE = "errorMessage";
    public static final String KEY_LOGIN = "login";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_USER = "user";
    public static final String KEY_PASSWORD_REPEAT = "passwordRepeat";

    //controllers and filters urls
    public static final String TASK_CONTROLLER = "/operation/taskController";
    public static final String CHANGE_TASK_CONTROLLER = "/operation/changeTaskController";
    public static final String CREATE_TASK_CONTROLLER = "/operation/createTaskController";
    public static final String DELETE_TASK_CONTROLLER = "/operation/deleteTaskController";
    public static final String UPLOAD_FILE_CONTROLLER = "/operation/uploadFileController";
    public static final String LOGIN_CONTROLLER = "/userControllers/login";
    public static final String LOGOUT_CONTROLLER = "/userControllers/logout";
    public static final String REGISTRATION_CONTROLLER = "/userControllers/registration";

    public static final String LOGIN_FILTER_URL = "/operation/*";
    //page url
    public static final String INDEX_PAGE = "/index.jsp";
    public static final String MAIN_PAGE = "/pages/taskPages/main.jsp";
    public static final String LOGIN_PAGE = "/pages/userPages/login.jsp";
    public static final String REGISTRATION_PAGE = "/pages/userPages/registration.jsp";
    public static final String ERROR_PAGE = "/pages/error.jsp";

    public static final String TODAY_LIST = "todayList";
    public static final String TOMORROW_LIST = "tomorrowList";
    public static final String OTHER_DAY_LIST = "otherDayList";
    public static final String FIXED_LIST = "fixedList";
    public static final String DELETED_LIST = "deletedList";
    public static final String ACTUAL = "actual";


}
