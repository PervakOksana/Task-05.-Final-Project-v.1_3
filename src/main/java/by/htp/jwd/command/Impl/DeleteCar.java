package by.htp.jwd.command.Impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import by.htp.jwd.service.ValidatorService;

public class DeleteCar implements Command {

	private final Logger LOGGER = LoggerFactory.getLogger(DeleteCar.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ServiceProvider provider = ServiceProvider.getInstance();
		CarService carService = provider.getCarServise();
		ValidatorService validatorService = provider.getValidatorService();

		if (!validatorService.loginationAdminValidator(request, response)) {
			response.sendRedirect(CommandField.PATH_LOGINATION_PAGE);
			return;
		}

		int idCar = Integer.parseInt(request.getParameter(CommandField.CAR_ID));

		try {
			carService.deleteCar(idCar);
			request.setAttribute(CommandField.CAR_DELETE, CommandField.CAR_DELETE_DONE);
			RequestDispatcher requestDispather = request.getRequestDispatcher(CommandField.PATH_CAR_ODER);
			requestDispather.forward(request, response);
		} catch (ServiceException e) {
			LOGGER.error("DeleteCar::ServiceException.");
			request.setAttribute(CommandField.MESSAGE_ERROR, "Cannot delete car, please try again later");
			RequestDispatcher requestDispather = request.getRequestDispatcher(CommandField.PATH_PAGE_ERROR);
			requestDispather.forward(request, response);
		}

	}
}