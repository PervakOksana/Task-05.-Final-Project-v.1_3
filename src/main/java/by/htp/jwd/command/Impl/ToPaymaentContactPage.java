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

public class ToPaymaentContactPage implements Command{
	
	private final Logger LOGGER = LoggerFactory.getLogger(ToPaymaentContactPage.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ServiceProvider provider = ServiceProvider.getInstance();
		ApplicationInfoService applicationInfoService = provider.getApplicationInfoService();
		
		String page = request.getParameter(CommandField.TAKE);
		
		try {
			ApplicationInfo applicationInfo =applicationInfoService.getApplicationInfo();
			
			request.setAttribute(CommandField.APP_INFO, applicationInfo);
			if (CommandField.IS.equals(page)) {
				RequestDispatcher requestDispather = request.getRequestDispatcher(CommandField.PATH_PAGE_PAYMENT);
				requestDispather.forward(request, response);
				return;
			}
			RequestDispatcher requestDispather = request.getRequestDispatcher(CommandField.PATH_PAGE_CONTACT);
			requestDispather.forward(request, response);
		} catch (ServiceException e) {
			LOGGER.error("ToPaymaentContactPage::ServiceException.");
			request.setAttribute(CommandField.MESSAGE_ERROR, "Cannot find pade, please try again later");
			RequestDispatcher requestDispather = request.getRequestDispatcher(CommandField.PATH_PAGE_ERROR);
			requestDispather.forward(request, response);
		}
	}

}
