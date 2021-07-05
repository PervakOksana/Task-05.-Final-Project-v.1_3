package by.htp.jwd.command.Impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.htp.jwd.bean.Adress;
import by.htp.jwd.bean.Car;
import by.htp.jwd.bean.PriceList;
import by.htp.jwd.bean.User;
import by.htp.jwd.bean.UserDetail;
import by.htp.jwd.command.Command;
import by.htp.jwd.command.CommandField;
import by.htp.jwd.service.CarService;
import by.htp.jwd.service.ServiceException;
import by.htp.jwd.service.ServiceProvider;
import by.htp.jwd.service.UserService;
import by.htp.jwd.service.ValidatorService;

public class UpdateCar implements Command {

	private final Logger LOGGER = LoggerFactory.getLogger(UpdateCar.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ServiceProvider provider = ServiceProvider.getInstance();
		CarService carService = provider.getCarServise();
		ValidatorService validatorService = provider.getValidatorService();
		HttpSession session = request.getSession();

		if (!validatorService.loginationAdminValidator(request, response)) {
			response.sendRedirect(CommandField.PATH_LOGINATION_PAGE);
			return;
		}
		int carId = Integer.parseInt(request.getParameter(CommandField.CARID));
		if (!validatorService.numberValidator(request)) {
			session.setAttribute(CommandField.NOTICE_NUMBER, CommandField.ERROR_NUMBER);
			response.sendRedirect(CommandField.TO_UPDATE_CAR_PAGE+carId );
			return;
		}
		
		String brand = request.getParameter(CommandField.BRAND);
		String describeBrand = request.getParameter(CommandField.DESCRIBE_BRAND);
		String bodywork = request.getParameter(CommandField.BODYWORK);
		String power = request.getParameter(CommandField.POWER);
		String transmission = request.getParameter(CommandField.TRANSMISSION);
		String numberDoors = request.getParameter(CommandField.NUMBER_DOORS);
		String year = request.getParameter(CommandField.YEAR);

		String photoPath = request.getParameter(CommandField.PHOTO_PATH);

		int priceId = Integer.parseInt(request.getParameter(CommandField.PRICE_ID));
		double costHour = Double.parseDouble(request.getParameter(CommandField.COST_HOUR));
		double costDayr = Double.parseDouble(request.getParameter(CommandField.COST_DAY));
		double sale = Double.parseDouble(request.getParameter(CommandField.SALE));

		Car car = new Car(carId, brand, describeBrand, bodywork, power, transmission, numberDoors, year, photoPath,
				"1");

		PriceList priceList = new PriceList(priceId, costHour, costDayr, sale, car);

		try {
			carService.updateCar(priceList);
			RequestDispatcher requestDispather = request.getRequestDispatcher(CommandField.PATH_REPORT_ALL_CAR_PAGE);
			requestDispather.forward(request, response);
		} catch (ServiceException e) {
			LOGGER.error("UpdateCar::ServiceException.");
			request.setAttribute(CommandField.MESSAGE_ERROR, "Cannot update car, please try again later");
			RequestDispatcher requestDispather = request.getRequestDispatcher(CommandField.PATH_PAGE_ERROR);
			requestDispather.forward(request, response);
		}

	}
}