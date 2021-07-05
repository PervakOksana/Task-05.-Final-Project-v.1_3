package by.htp.jwd.command.Impl;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.htp.jwd.bean.Adress;
import by.htp.jwd.bean.Car;
import by.htp.jwd.bean.PriceList;
import by.htp.jwd.command.Command;
import by.htp.jwd.command.CommandField;
import by.htp.jwd.service.CarService;
import by.htp.jwd.service.ServiceException;
import by.htp.jwd.service.ServiceProvider;
import by.htp.jwd.service.ValidatorService;

@MultipartConfig(maxFileSize = 16177215)
public class AddNewCar implements Command {

	private final Logger LOGGER = LoggerFactory.getLogger(AddNewCar.class);

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
		
		if (!validatorService.numberValidator(request)) {
			session.setAttribute(CommandField.NOTICE_NUMBER, CommandField.ERROR_NUMBER);
			response.sendRedirect(CommandField.PATH_ADD_CAR_VIEW_ERROR);
			return;
		}

		String brand = request.getParameter(CommandField.BRAND);
		String describeBrand = request.getParameter(CommandField.DESCRIBE_BRAND);
		String bodywork = request.getParameter(CommandField.BODYWORK);
		String power = request.getParameter(CommandField.POWER);
		String transmission = request.getParameter(CommandField.TRANSMISSION);
		String numberDoors = request.getParameter(CommandField.NUMBER_DOORS);
		String year = request.getParameter(CommandField.YEAR);
		int adressId = Integer.parseInt(request.getParameter(CommandField.ADRESS_CAR));

		String photoPath = request.getParameter(CommandField.PHOTO_PATH);

		Car car = new Car(brand, describeBrand, bodywork, power, transmission, numberDoors, year, photoPath, "1",
				new Adress(adressId));

		double costHour = Double.parseDouble(request.getParameter(CommandField.COST_HOUR));
		double costDayr = Double.parseDouble(request.getParameter(CommandField.COST_DAY));
		double sale = Double.parseDouble(request.getParameter(CommandField.SALE));

		PriceList priceList = new PriceList(costHour, costDayr, sale);
		try {
			carService.addCarService(car);
			carService.addPriceToCarService(priceList);
			response.sendRedirect(CommandField.PATH_REPORT_ALL_CAR_PAGE);
		} catch (ServiceException e) {
			LOGGER.error("AddAdress::ServiceException.");
			request.setAttribute(CommandField.MESSAGE_ERROR, "Cannot save new car, please try again later");
			RequestDispatcher requestDispather = request.getRequestDispatcher(CommandField.PATH_PAGE_ERROR);
			requestDispather.forward(request, response);
		}
	}

}
