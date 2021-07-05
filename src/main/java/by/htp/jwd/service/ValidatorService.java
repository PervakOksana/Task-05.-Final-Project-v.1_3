package by.htp.jwd.service;

import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ValidatorService {

	final String AUTH = "auth";
	final String PASSWORD = "password";
	final String DATE_START = "dateStart";
	final String DATE_END = "dateEnd";
	final String TIME_START = "timeStart";
	final String TIME_END = "timeEnd";
	final String DRIVING_LICANSE_DATE = "drivingLicanseDate";
	final String EMAIL = "email";
	final String LOGIN = "login";
	final String NAME = "name";
	final String SURNAME = "surname";
	final String PHONE = "phone";	
	final String USER = "USER";	
	final String ADMIN = "ADMIN";	
	final String ROLE_LOGIN = "roleByLogin";
	final String MESSAGE = "message";		
	final String PASSWORD_NUMBER = "passwordNumber";
	final String DRIVING_LICENSE_NUMBER = "drivingLicanseNumber";
	final String DRIVING_LICENSE_DATE = "drivingLicanseDate";
	final String PASSWORD_DATE = "passwordDate";	
	final String COUNTRY = "country";
	final String CITY = "city";
	final String STREET = "street";
	final String HOUSE = "house";
	final String COST_HOUR = "cost_hour";
	final String COST_DAY = "cost_dayr";
	final String SALE = "sale";
	final String DATE_REPAIR = "dateRepair";
	final String COST_REPAIR = "costRepair";
	final String PASSWORD_PATTERN = "(?=.*[0-9])(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z!@#$%^&*]{6,}";
	final String DATE_PATTERN = "((19|20)\\d\\d)-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])";
	final String TIME_PATTERN =  "([01]?[0-9]|2[0-3]):[0-5][0-9]";
	final String EMAIL_PATTERN =  "\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*\\.\\w{2,4}";
	final String PATTERN_DATE = "yyyy-MM-dd";
	final String PATTERN_NUMBER = "((-|\\\\+)?[0-9]+(\\\\.[0-9]+)?)";

	boolean loginationValidator(HttpServletRequest request, HttpServletResponse response);
	boolean loginationAdminValidator(HttpServletRequest request, HttpServletResponse response);
	boolean registrationValidator(HttpServletRequest request);
	boolean dateValidator(HttpServletRequest request);
	boolean dateUserValidator(HttpServletRequest request);
	boolean repairValidator(HttpServletRequest request);
	boolean timeValidator(HttpServletRequest request);
	boolean compareDateValidator(HttpServletRequest request) throws ParseException;
	boolean emailValidator(HttpServletRequest request);
	boolean dateForOderValidator(HttpServletRequest request);
	boolean numberValidator(HttpServletRequest request);

}
