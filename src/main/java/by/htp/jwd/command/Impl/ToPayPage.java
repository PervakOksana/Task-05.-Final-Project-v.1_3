package by.htp.jwd.command.Impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.htp.jwd.bean.OderInfo;
import by.htp.jwd.command.Command;
import by.htp.jwd.command.CommandField;
import by.htp.jwd.service.CarService;
import by.htp.jwd.service.OderService;
import by.htp.jwd.service.ServiceException;
import by.htp.jwd.service.ServiceProvider;
import by.htp.jwd.service.ValidatorService;

public class ToPayPage implements Command {

	private final Logger LOGGER = LoggerFactory.getLogger(ToPayPage.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ServiceProvider provider = ServiceProvider.getInstance();
		CarService carService = provider.getCarServise();
		OderService oderService = provider.getOderService();
		ValidatorService validatorService = provider.getValidatorService();
		HttpSession session = request.getSession();

		String id = request.getParameter(CommandField.LINK_ID);
		if (!"is".equals(request.getParameter("take"))) {
			session.removeAttribute(CommandField.CAR_ID);
		}
		if (!validatorService.loginationValidator(request, response)) {
			response.sendRedirect(CommandField.PATH_LOGINATION_PAGE);
			return;
		}
		try {
			if (id != null) {
				session.setAttribute(CommandField.CAR_ID, id);
			}
			if (id == null) {
				id = (String) session.getAttribute(CommandField.CAR_ID);
			}
			OderInfo oderInfo = oderService.costCar(carService.infoAboutCar(id),
					(OderInfo) session.getAttribute(CommandField.ODER_INFO));

			session.setAttribute(CommandField.ODER_INFO_FULL, oderInfo);
			RequestDispatcher requestDispather = request.getRequestDispatcher(CommandField.PATH_PAY);
			requestDispather.forward(request, response);
		} catch (ServiceException e) {
			LOGGER.error("ToPayPage::ServiceException.");
			request.setAttribute(CommandField.MESSAGE_ERROR, "Cannot find page, please try again later");
			RequestDispatcher requestDispather = request.getRequestDispatcher(CommandField.PATH_PAGE_ERROR);
			requestDispather.forward(request, response);
		}
	}
}
