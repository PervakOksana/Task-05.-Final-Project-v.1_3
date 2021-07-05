package by.htp.jwd.command.Impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.htp.jwd.bean.Adress;
import by.htp.jwd.command.Command;
import by.htp.jwd.command.CommandField;
import by.htp.jwd.service.CarService;
import by.htp.jwd.service.OderService;
import by.htp.jwd.service.ServiceException;
import by.htp.jwd.service.ServiceProvider;
import by.htp.jwd.service.ValidatorService;

public class AddNewCarView implements Command {

	private final Logger LOGGER = LoggerFactory.getLogger(AddNewCarView.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ServiceProvider provider = ServiceProvider.getInstance();
		ValidatorService validatorService = provider.getValidatorService();
		OderService oderService = provider.getOderService();
		HttpSession session = request.getSession();
		List<Adress> adresses = new ArrayList<Adress>();

		if (!validatorService.loginationAdminValidator(request, response)) {
			response.sendRedirect(CommandField.PATH_CHOOSE_CAR);
			return;
		}

		if (!"is".equals(request.getParameter("error"))) {
			session.removeAttribute(CommandField.NOTICE_NUMBER);
		}

		try {
			adresses = oderService.adressCar();
			request.setAttribute(CommandField.ADRESS, adresses);
			RequestDispatcher requestDispather = request.getRequestDispatcher(CommandField.PATH_ADD_CAR);
			requestDispather.forward(request, response);

		} catch (ServiceException e) {
			LOGGER.error("AddNewCarView::ServiceException.");
			request.setAttribute(CommandField.MESSAGE_ERROR, "Cannot get response, please try again later");
			RequestDispatcher requestDispather = request.getRequestDispatcher(CommandField.PATH_PAGE_ERROR);
			requestDispather.forward(request, response);

		}

	}

}
