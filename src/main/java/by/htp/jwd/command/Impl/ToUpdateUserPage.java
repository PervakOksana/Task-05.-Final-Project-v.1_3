package by.htp.jwd.command.Impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.htp.jwd.bean.OderInfo;
import by.htp.jwd.bean.User;
import by.htp.jwd.bean.UserDetail;
import by.htp.jwd.command.Command;
import by.htp.jwd.command.CommandField;
import by.htp.jwd.service.OderService;
import by.htp.jwd.service.SenderService;
import by.htp.jwd.service.ServiceException;
import by.htp.jwd.service.ServiceProvider;
import by.htp.jwd.service.UserService;
import by.htp.jwd.service.ValidatorService;

public class ToUpdateUserPage implements Command {

	private final Logger LOGGER = LoggerFactory.getLogger(ToUpdateUserPage.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ServiceProvider provider = ServiceProvider.getInstance();
		ValidatorService validatorService = provider.getValidatorService();
		UserService userService = provider.getUserServise();
		HttpSession session = request.getSession();
		UserDetail userDetail = null;

		if (!validatorService.loginationValidator(request, response)) {
			response.sendRedirect(CommandField.PATH_LOGINATION_PAGE);
			return;
		}
		try {
			if (!"is".equals(request.getParameter("error"))) {
				session.removeAttribute(CommandField.NOTICE_DATE);
				session.removeAttribute(CommandField.NOTICE_PASSWORD);
				session.removeAttribute(CommandField.NOTICE_EMAIL);
				session.removeAttribute(CommandField.NOTICE_EMPTY);
			}
			String userId = Integer.toString((int) session.getAttribute(CommandField.USER_ID_LOGIN));
			userDetail = userService.findUserViaId(userId);
			request.setAttribute(CommandField.USER_DETAIL, userDetail);
			RequestDispatcher requestDispather = request.getRequestDispatcher(CommandField.PATH_USER_UPDATE);
			requestDispather.forward(request, response);

		} catch (ServiceException e) {
			LOGGER.error("ToUpdateUserPage::ServiceException.");
			request.setAttribute(CommandField.MESSAGE_ERROR, "Cannot find page, please try again later");
			RequestDispatcher requestDispather = request.getRequestDispatcher(CommandField.PATH_PAGE_ERROR);
			requestDispather.forward(request, response);
		}

	}

}
