package by.htp.jwd.command.Impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.htp.jwd.bean.PriceList;
import by.htp.jwd.command.Command;
import by.htp.jwd.command.CommandField;
import by.htp.jwd.service.CarService;
import by.htp.jwd.service.ServiceException;
import by.htp.jwd.service.ServiceProvider;

public class InfoCar implements Command {

	private final Logger LOGGER = LoggerFactory.getLogger(InfoCar.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String id = request.getParameter(CommandField.LINK_ID);

		ServiceProvider provider = ServiceProvider.getInstance();
		CarService carService = provider.getCarServise();
		PriceList car = new PriceList();

		try {
			car = carService.infoAboutCar(id);
			request.setAttribute(CommandField.INFO_CAR, car);
			RequestDispatcher requestDispather = request.getRequestDispatcher(CommandField.PATH_PAGE_INFO_CAR);
			requestDispather.forward(request, response);

		} catch (ServiceException e) {
			LOGGER.error("InfoCar::ServiceException.");
			request.setAttribute(CommandField.MESSAGE_ERROR, "Cannot get car, please try again later");
			RequestDispatcher requestDispather = request.getRequestDispatcher(CommandField.PATH_PAGE_ERROR);
			requestDispather.forward(request, response);
		}

	}

}
