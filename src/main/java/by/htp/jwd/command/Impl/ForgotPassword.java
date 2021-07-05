package by.htp.jwd.command.Impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.htp.jwd.bean.ApplicationInfo;
import by.htp.jwd.bean.User;
import by.htp.jwd.command.Command;
import by.htp.jwd.command.CommandField;
import by.htp.jwd.service.ApplicationInfoService;
import by.htp.jwd.service.SenderService;
import by.htp.jwd.service.ServiceException;
import by.htp.jwd.service.ServiceProvider;
import by.htp.jwd.service.UserService;
import by.htp.jwd.service.ValidatorService;

public class ForgotPassword implements Command {
	
	private final Logger LOGGER = LoggerFactory.getLogger(ForgotPassword.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		ServiceProvider provider = ServiceProvider.getInstance();
		UserService userService = provider.getUserServise();
		SenderService senderService = provider.getSenderService();
		ApplicationInfoService applicationInfoService = provider.getApplicationInfoService();
		String email = request.getParameter(CommandField.EMAIL_ALL);

		User user = null;

		try {
			user = userService.findPassword(email);

			if (user.getEmail() != null) {
				ApplicationInfo applicationInfo = applicationInfoService.getApplicationInfo();
				senderService.send(CommandField.LETTER_PASSWORD, user.getPassword(), applicationInfo.getAdressEmail(),
						applicationInfo.getPasswordEmail(), user.getEmail());
				session.removeAttribute(CommandField.NOTICE_USER_EMPTY);
				session.setAttribute(CommandField.NOTICE_USER, CommandField.ERROR_USER);
			}

			if (user.getEmail() == null) {

				session.removeAttribute(CommandField.NOTICE_USER);
				session.setAttribute(CommandField.NOTICE_USER_EMPTY, CommandField.ERROR_USER_EMPTY);
			}

			RequestDispatcher requestDispather = request.getRequestDispatcher(CommandField.PATH_FIND_PASSWORD);
			requestDispather.forward(request, response);

		} catch (ServiceException e) {
			LOGGER.error("ForgotPassword::ServiceException.");
			request.setAttribute(CommandField.MESSAGE_ERROR, "Cannot get your password, please try again later");
			RequestDispatcher requestDispather = request.getRequestDispatcher(CommandField.PATH_PAGE_ERROR);
			requestDispather.forward(request, response);
		}

	}

}
