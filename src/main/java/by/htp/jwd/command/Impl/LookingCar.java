package by.htp.jwd.command.Impl;

import java.io.IOException;
import java.text.ParseException;
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
import by.htp.jwd.command.Command;
import by.htp.jwd.command.CommandField;
import by.htp.jwd.service.CarService;
import by.htp.jwd.service.OderService;
import by.htp.jwd.service.ServiceException;
import by.htp.jwd.service.ServiceProvider;
import by.htp.jwd.service.ValidatorService;

public class LookingCar implements Command {

	private final Logger LOGGER = LoggerFactory.getLogger(LookingCar.class);
	private List<PriceList> carsLook;
	OderInfo oderInfo;

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String adressStartId = request.getParameter(CommandField.ADRESS_START);
		String adressEndId = request.getParameter(CommandField.ADRESS_END);
		String dateStart = request.getParameter(CommandField.DATE_START);
		String dateEnd = request.getParameter(CommandField.DATE_END);
		String timeStart = request.getParameter(CommandField.TIME_START);
		String timeEnd = request.getParameter(CommandField.TIME_END);

		dateStart = dateStart + " " + timeStart + ":00";
		dateEnd = dateEnd + " " + timeEnd + ":00";

		if (adressStartId == null) {
			request.setAttribute(CommandField.CARS, carsLook);
			RequestDispatcher requestDispather = request.getRequestDispatcher(CommandField.PATH_LOOKING_CARS);
			requestDispather.forward(request, response);
			return;
		}
		ServiceProvider provider = ServiceProvider.getInstance();
		CarService carService = provider.getCarServise();
		OderService oderService = provider.getOderService();
		ValidatorService validatorService = provider.getValidatorService();
		HttpSession session = request.getSession();

		try {
			if (!validatorService.dateValidator(request) | !validatorService.timeValidator(request)
					| !validatorService.compareDateValidator(request)
					| !validatorService.dateForOderValidator(request)) {
				if (!validatorService.dateValidator(request)) {
					session.setAttribute(CommandField.NOTICE_DATE, CommandField.ERROR_DATE);
				}
				if (!validatorService.timeValidator(request)) {
					session.setAttribute(CommandField.NOTICE_TIME, CommandField.ERROR_TIME);
				}
				if (!validatorService.compareDateValidator(request)) {
					session.setAttribute(CommandField.NOTICE_DATE, CommandField.ERROR_DATE);
				}
				if (!validatorService.dateForOderValidator(request)) {
					session.setAttribute(CommandField.NOTICE_DATE_WAIT, CommandField.ERROR_DATE_WAIT);
				}
				response.sendRedirect(CommandField.PATH_CHOOSE_CAR_ERROR);
				return;
			}
			if (validatorService.loginationValidator(request, response)) {
				session.setAttribute(CommandField.IS_USER, CommandField.USER);
			}
			carsLook = carService.lookingCar(adressStartId, dateStart, dateEnd);
			String adressStart = oderService.adressCarId(adressStartId).getAdressAll();
			String adressEnd = oderService.adressCarId(adressEndId).getAdressAll();

			OderInfo oderInfo = new OderInfo(adressStart, adressEnd, adressStartId, adressEndId, dateStart, dateEnd);
			session.setAttribute(CommandField.ODER_INFO, oderInfo);
			if (carsLook.size() == 0) {
				session.setAttribute(CommandField.CAR_IS, CommandField.CAR_NOT);
			} else {
				session.removeAttribute(CommandField.CAR_IS);
			}
			request.setAttribute(CommandField.CARS, carsLook);
			RequestDispatcher requestDispather = request.getRequestDispatcher(CommandField.PATH_LOOKING_CARS);
			requestDispather.forward(request, response);
		} catch (ServiceException e) {
			LOGGER.error("LookingCar::ServiceException.");
			request.setAttribute(CommandField.MESSAGE_ERROR, "Cannot get car list, please try again later");
			RequestDispatcher requestDispather = request.getRequestDispatcher(CommandField.PATH_PAGE_ERROR);
			requestDispather.forward(request, response);
		} catch (ParseException e) {
			LOGGER.error("LookingCar::ServiceException.");
			request.setAttribute(CommandField.MESSAGE_ERROR, "Cannot get date, please try again later");
			session.setAttribute(CommandField.NOTICE_DATE, CommandField.ERROR_DATE);
			response.sendRedirect(CommandField.PATH_CHOOSE_CAR_ERROR);
		}

	}

}
