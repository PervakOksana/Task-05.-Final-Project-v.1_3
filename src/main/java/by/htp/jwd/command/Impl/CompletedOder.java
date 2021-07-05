package by.htp.jwd.command.Impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.htp.jwd.command.Command;
import by.htp.jwd.command.CommandField;
import by.htp.jwd.service.CarService;
import by.htp.jwd.service.OderService;
import by.htp.jwd.service.ServiceException;
import by.htp.jwd.service.ServiceProvider;
import by.htp.jwd.service.ValidatorService;

public class CompletedOder implements Command{
	
	private final Logger LOGGER = LoggerFactory.getLogger(CompletedOder.class);
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ServiceProvider provider = ServiceProvider.getInstance();
		OderService oderService = provider.getOderService();
		ValidatorService validatorService = provider.getValidatorService();
		
		if (!validatorService.loginationAdminValidator(request, response)) {

			response.sendRedirect(CommandField.PATH_LOGINATION_PAGE);
			return;
		}
		
		int oderId = Integer.parseInt(request.getParameter(CommandField.ODER_ID_IN));
		try {
			oderService.oderСompleted(oderId);
			response.sendRedirect(CommandField.PATH_REPORT_PAGE);
		} catch (ServiceException e) {
			LOGGER.error("CompletedOder::ServiceException.");
			request.setAttribute(CommandField.MESSAGE_ERROR, "Cannot completed oder, please try again later");
			RequestDispatcher requestDispather = request.getRequestDispatcher(CommandField.PATH_PAGE_ERROR);
			requestDispather.forward(request, response);
		}
		
		
	}

}
