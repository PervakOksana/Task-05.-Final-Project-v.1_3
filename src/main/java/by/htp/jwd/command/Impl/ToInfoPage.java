package by.htp.jwd.command.Impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.htp.jwd.bean.ApplicationInfo;
import by.htp.jwd.command.Command;
import by.htp.jwd.command.CommandField;
import by.htp.jwd.service.ApplicationInfoService;
import by.htp.jwd.service.ServiceException;
import by.htp.jwd.service.ServiceProvider;
import by.htp.jwd.service.ValidatorService;

public class ToInfoPage implements Command {

	private final Logger LOGGER = LoggerFactory.getLogger(ToInfoPage.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ServiceProvider provider = ServiceProvider.getInstance();
		ValidatorService validatorService = provider.getValidatorService();
		ApplicationInfoService applicationInfoService = provider.getApplicationInfoService();

		if (!validatorService.loginationAdminValidator(request, response)) {

			response.sendRedirect(CommandField.PATH_LOGINATION_PAGE);
			return;
		}
		try {
			ApplicationInfo applicationInfo = applicationInfoService.getApplicationInfo();
			request.setAttribute(CommandField.APP_INFO, applicationInfo);
			RequestDispatcher requestDispather = request.getRequestDispatcher(CommandField.PATH_INFO_PAGE);
			requestDispather.forward(request, response);
		} catch (ServiceException e) {
			LOGGER.error("ToInfoPage::ServiceException.");
			request.setAttribute(CommandField.MESSAGE_ERROR, "Cannot find info page, please try again later");
			RequestDispatcher requestDispather = request.getRequestDispatcher(CommandField.PATH_PAGE_ERROR);
			requestDispather.forward(request, response);
		}

	}

}
