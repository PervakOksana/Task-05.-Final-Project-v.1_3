package by.htp.jwd.command.Impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.htp.jwd.bean.Oder;
import by.htp.jwd.bean.PriceList;
import by.htp.jwd.command.Command;
import by.htp.jwd.command.CommandField;
import by.htp.jwd.service.CarService;
import by.htp.jwd.service.OderService;
import by.htp.jwd.service.ServiceException;
import by.htp.jwd.service.ServiceProvider;
import by.htp.jwd.service.ValidatorService;

public class ToCancelOderPage implements Command {

	private final Logger LOGGER = LoggerFactory.getLogger(ToCancelOderPage.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ServiceProvider provider = ServiceProvider.getInstance();
		ValidatorService validatorService = provider.getValidatorService();
		OderService oderService = provider.getOderService();

		if (!validatorService.loginationAdminValidator(request, response)) {
			response.sendRedirect(CommandField.PATH_LOGINATION_PAGE);
			return;
		}
		try {
			int oderId = Integer.parseInt(request.getParameter(CommandField.ID_ODER));
			Oder oder = oderService.findOderById(oderId);
			request.setAttribute(CommandField.ODER, oder);
			RequestDispatcher requestDispather = request.getRequestDispatcher(CommandField.PATH_CAR_UPDATE);
			requestDispather.forward(request, response);

		} catch (ServiceException e) {
			LOGGER.error("ToCancelOderPage::ServiceException.");
			request.setAttribute(CommandField.MESSAGE_ERROR, "Cannot cancel order, please try again later");
			RequestDispatcher requestDispather = request.getRequestDispatcher(CommandField.PATH_PAGE_ERROR);
			requestDispather.forward(request, response);
		}

	}

}
