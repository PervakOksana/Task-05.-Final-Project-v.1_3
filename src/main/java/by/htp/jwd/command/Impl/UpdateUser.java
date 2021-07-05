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

public class UpdateUser implements Command {

	private final Logger LOGGER = LoggerFactory.getLogger(UpdateUser.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String login = request.getParameter(CommandField.LOGIN);
		String password = request.getParameter(CommandField.PASSWORD);
		String name = request.getParameter(CommandField.NAME);
		String surname = request.getParameter(CommandField.SURNAME);
		String phone = request.getParameter(CommandField.PHONE);
		String email = request.getParameter(CommandField.EMAIL_IS);
		String role = request.getParameter(CommandField.ROLE_USER);
		String passwordNumber = request.getParameter(CommandField.PASSWORD_NUMBER);
		String drivingLicanseNumber = request.getParameter(CommandField.DRIVING_LICENSE_NUMBER);
		String drivingLicanseDate = request.getParameter(CommandField.DRIVING_LICENSE_DATE);
		String passwordDate = request.getParameter(CommandField.PASSWORD_DATE);
		String country = request.getParameter(CommandField.COUNTRY);
		String city = request.getParameter(CommandField.CITY);
		String street = request.getParameter(CommandField.STREET);
		String house = request.getParameter(CommandField.HOUSE);
		int adressId = Integer.parseInt(request.getParameter(CommandField.ADRESS_ID));
		int userId = Integer.parseInt(request.getParameter(CommandField.USER_ID));
		int userDetailId = Integer.parseInt(request.getParameter(CommandField.USER_DETAIL_ID));

		HttpSession session = request.getSession();
		ServiceProvider provider = ServiceProvider.getInstance();
		UserService userService = provider.getUserServise();
		ValidatorService validatorService = provider.getValidatorService();

		try {
			if (!validatorService.dateUserValidator(request) | !validatorService.registrationValidator(request)
					| !validatorService.emailValidator(request)) {

				if (!validatorService.dateUserValidator(request)) {
					session.setAttribute(CommandField.NOTICE_DATE, CommandField.ERROR_DATE);
				}
				if (!validatorService.registrationValidator(request)) {
					session.setAttribute(CommandField.NOTICE_PASSWORD, CommandField.ERROR_PASSWORD);
				}
				if (!validatorService.emailValidator(request)) {
					session.setAttribute(CommandField.NOTICE_EMAIL, CommandField.ERROR_EMAIL);
				}
				response.sendRedirect(CommandField.PATH_UPDATE_USER);
				return;
			}

			Adress adress = new Adress(adressId, city, country, street, house);
			User user = new User(userId, login, password, surname, role, name, phone, email, adress);
			UserDetail userDetail = new UserDetail(userDetailId, passwordNumber, drivingLicanseNumber,
					drivingLicanseDate, passwordDate, user);
			userService.updateUser(userDetail);
			response.sendRedirect(CommandField.PATH_CHOOSE_CAR);
		} catch (ServiceException e) {
			LOGGER.error("UpdateUser::ServiceException.");
			request.setAttribute(CommandField.MESSAGE_ERROR, "Cannot update user, please try again later");
			RequestDispatcher requestDispather = request.getRequestDispatcher(CommandField.PATH_PAGE_ERROR);
			requestDispather.forward(request, response);
		}

	}
}