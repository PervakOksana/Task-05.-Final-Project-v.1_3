package by.htp.jwd.command.Impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.htp.jwd.bean.User;
import by.htp.jwd.command.Command;
import by.htp.jwd.command.CommandField;
import by.htp.jwd.service.ServiceException;
import by.htp.jwd.service.ServiceProvider;
import by.htp.jwd.service.UserService;

public class Logination implements Command {

	private final Logger LOGGER = LoggerFactory.getLogger(Logination.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String login = request.getParameter(CommandField.LOGIN);
		String password = request.getParameter(CommandField.PASSWORD);

		ServiceProvider provider = ServiceProvider.getInstance();
		UserService userService = provider.getUserServise();
		HttpSession session = request.getSession();
		User user = null;

		try {
			user = userService.authorization(login, password);

			if (user == null) {

				session.setAttribute(CommandField.MESSAGE_LOG, CommandField.ERROR_LOG);
				RequestDispatcher requestDispather = request.getRequestDispatcher(CommandField.PATH_PAGE_ERROR);
				requestDispather.forward(request, response);

			} else {

				session.setAttribute(CommandField.AUTH, true);
				session.setAttribute(CommandField.ROLE_LOGIN, user.getRole());
				session.setAttribute(CommandField.USER_ID_LOGIN, user.getId());
				session.setAttribute(CommandField.MESSAGE, user.getLogin());
				response.sendRedirect(CommandField.PATH_CHOOSE_CAR);
			}

		} catch (ServiceException e) {
			e.printStackTrace();
			LOGGER.error("Logination::ServiceException." + e);
			request.setAttribute(CommandField.MESSAGE_ERROR, "Cannot get logination, please try again later");
			RequestDispatcher requestDispather = request.getRequestDispatcher(CommandField.PATH_PAGE_ERROR);
			requestDispather.forward(request, response);
		}

	}

}
