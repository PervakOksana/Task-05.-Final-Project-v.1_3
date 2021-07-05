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
import by.htp.jwd.bean.OderInfo;
import by.htp.jwd.bean.PriceList;
import by.htp.jwd.bean.User;
import by.htp.jwd.bean.UserDetail;
import by.htp.jwd.command.Command;
import by.htp.jwd.command.CommandField;
import by.htp.jwd.service.CarService;
import by.htp.jwd.service.OderService;
import by.htp.jwd.service.SenderService;
import by.htp.jwd.service.ServiceException;
import by.htp.jwd.service.ServiceProvider;
import by.htp.jwd.service.UserService;
import by.htp.jwd.service.ValidatorService;

public class ToUpdateCarPage implements Command {

	private final Logger LOGGER = LoggerFactory.getLogger(ToUpdateCarPage.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ServiceProvider provider = ServiceProvider.getInstance();
		ValidatorService validatorService = provider.getValidatorService();
		CarService carService = provider.getCarServise();
		OderService oderService = provider.getOderService();
		HttpSession session = request.getSession();
		PriceList priceList = null;
		List<Adress> adresses = new ArrayList<Adress>();

		if (!validatorService.loginationAdminValidator(request, response)) {

			response.sendRedirect(CommandField.PATH_LOGINATION_PAGE);
			return;
		}

		if (!"is".equals(request.getParameter("error"))) {
			session.removeAttribute(CommandField.NOTICE_NUMBER);
		}
		try {

			String carId = (String) request.getParameter(CommandField.CAR_ID);
			priceList = carService.infoAboutCar(carId);
			adresses = oderService.adressCar();
			request.setAttribute(CommandField.ADRESS, adresses);

			request.setAttribute(CommandField.CAR_LIST, priceList);
			RequestDispatcher requestDispather = request.getRequestDispatcher(CommandField.PATH_CAR_UPDATE_ADMIN);
			requestDispather.forward(request, response);

		} catch (ServiceException e) {
			LOGGER.error("ToUpdateCarPage::ServiceException.");
			request.setAttribute(CommandField.MESSAGE_ERROR, "Cannot find page, please try again later");
			RequestDispatcher requestDispather = request.getRequestDispatcher(CommandField.PATH_PAGE_ERROR);
			requestDispather.forward(request, response);
		}

	}

}
