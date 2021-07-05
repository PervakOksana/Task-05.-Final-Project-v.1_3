package by.htp.jwd.command.Impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.htp.jwd.bean.Adress;
import by.htp.jwd.bean.User;
import by.htp.jwd.bean.UserDetail;
import by.htp.jwd.command.Command;
import by.htp.jwd.command.CommandField;
import by.htp.jwd.service.ServiceException;
import by.htp.jwd.service.ServiceProvider;
import by.htp.jwd.service.UserService;
import by.htp.jwd.service.ValidatorService;

public class SaveUser implements Command {

	private final Logger LOGGER = LoggerFactory.getLogger(SaveUser.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String login = request.getParameter(CommandField.LOGIN);
		String password = request.getParameter(CommandField.PASSWORD);
		String name = request.getParameter(CommandField.NAME);
		String surname = request.getParameter(CommandField.SURNAME);
		String phone = request.getParameter(CommandField.PHONE);
		String email = request.getParameter(CommandField.EMAIL_IS);

		String passwordNumber = request.getParameter(CommandField.PASSWORD_NUMBER);
		String drivingLicanseNumber = request.getParameter(CommandField.DRIVING_LICENSE_NUMBER);
		String drivingLicanseDate = request.getParameter(CommandField.DRIVING_LICENSE_DATE);
		String passwordDate = request.getParameter(CommandField.PASSWORD_DATE);

		String country = request.getParameter(CommandField.COUNTRY);
		String city = request.getParameter(CommandField.CITY);
		String street = request.getParameter(CommandField.STREET);
		String house = request.getParameter(CommandField.HOUSE);
		String role=CommandField.USER_IS;
		HttpSession session = request.getSession();
		ServiceProvider provider = ServiceProvider.getInstance();
		UserService userService = provider.getUserServise();
		ValidatorService validatorService = provider.getValidatorService();

		try {
			if (!validatorService.dateUserValidator(request) | !validatorService.registrationValidator(request)
					| !validatorService.emailValidator(request) | userService.findLogin(login)!=null | 
					userService.findPassword(email)!=null) {		
				if (!validatorService.dateUserValidator(request)) {
					session.setAttribute(CommandField.NOTICE_DATE, CommandField.ERROR_DATE);					
				}
				if (!validatorService.registrationValidator(request)) {
					session.setAttribute(CommandField.NOTICE_PASSWORD, CommandField.ERROR_PASSWORD);				
				}
				if (!validatorService.emailValidator(request)) {
					session.setAttribute(CommandField.NOTICE_EMAIL, CommandField.ERROR_EMAIL);
				}	
				if (userService.findLogin(login)!=null) {	
					session.setAttribute(CommandField.NOTICE_LOGIN, CommandField.ERROR_LOGIN);		
				}	
				if (userService.findPassword(email)!=null) {	
					session.setAttribute(CommandField.NOTICE_LOGIN, CommandField.ERROR_LOGIN);		
				}			
				if (CommandField.TAKE_IS.equals(request.getParameter(CommandField.TAKE))) {
					response.sendRedirect(CommandField.PATH_REGISTRATION_ADMIN_IS);
				}
				if (!CommandField.TAKE_IS.equals(request.getParameter(CommandField.TAKE))) {
					response.sendRedirect(CommandField.PATH_REGISTRATION_IS);
				}	
				return;
			}
	
			if (CommandField.TAKE_IS.equals(request.getParameter(CommandField.TAKE))) {
				role = CommandField.ADMIN;
			}
			
			Adress adress = new Adress(city, country, street, house);
			User user = new User(login, password, surname, role, name, phone, email,adress);
			UserDetail userDetail = new UserDetail(passwordNumber, drivingLicanseNumber, drivingLicanseDate,
					passwordDate,user);
			userService.registrationAll(userDetail);			
			response.sendRedirect(CommandField.PATH_LOGINATION_PAGE);

		} catch (ServiceException e) {
			LOGGER.error("SaveUser::ServiceException.");
			request.setAttribute(CommandField.MESSAGE_ERROR, "Cannot save user, please try again later");
			RequestDispatcher requestDispather = request.getRequestDispatcher(CommandField.PATH_PAGE_ERROR);
			requestDispather.forward(request, response);
		}

	}
}