package by.htp.jwd.command.Impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.htp.jwd.bean.PriceList;
import by.htp.jwd.command.Command;
import by.htp.jwd.command.CommandField;
import by.htp.jwd.service.CarService;
import by.htp.jwd.service.ServiceException;
import by.htp.jwd.service.ServiceProvider;

public class AllCarsView implements Command {

	private final Logger LOGGER = LoggerFactory.getLogger(AllCarsView.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ServiceProvider provider = ServiceProvider.getInstance();
		CarService carService = provider.getCarServise();

		int currentPage = 1;
		int recordsPerPage = 15;
		if (request.getParameter(CommandField.T_PAGE) != null) {
			currentPage = Integer.parseInt(request.getParameter(CommandField.T_PAGE));
		}
		try {
			List<PriceList> cars = carService.allCar(currentPage);
			int rows = carService.getNumberOfRows();
			int nOfPages = rows / recordsPerPage;
			if (nOfPages % recordsPerPage > 0) {
				nOfPages++;
			}

			request.setAttribute(CommandField.NUMBER_PAGE, nOfPages);
			request.setAttribute(CommandField.CURRENT_PAGE, currentPage);
			request.setAttribute(CommandField.RECORD_PAGE, recordsPerPage);
			request.setAttribute(CommandField.CAR, cars);
			RequestDispatcher requestDispather = request.getRequestDispatcher(CommandField.PATH_ALL_CARS);
			requestDispather.forward(request, response);

		} catch (ServiceException e) {
			LOGGER.error("AddAdress::ServiceException.");
			request.setAttribute(CommandField.MESSAGE_ERROR, "Cannot get car list, please try again later");
			RequestDispatcher requestDispather = request.getRequestDispatcher(CommandField.PATH_PAGE_ERROR);
			requestDispather.forward(request, response);
		}
	}

}
