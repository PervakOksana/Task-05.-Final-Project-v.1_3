package by.htp.jwd.command.Impl;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.htp.jwd.bean.Oder;
import by.htp.jwd.bean.RepairBill;
import by.htp.jwd.command.Command;
import by.htp.jwd.command.CommandField;
import by.htp.jwd.service.OderService;
import by.htp.jwd.service.RepairBillService;
import by.htp.jwd.service.ServiceException;
import by.htp.jwd.service.ServiceProvider;
import by.htp.jwd.service.ValidatorService;

public class CancelOderByUser implements Command {

	private final Logger LOGGER = LoggerFactory.getLogger(CancelOderByUser.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ServiceProvider provider = ServiceProvider.getInstance();
		ValidatorService validatorService = provider.getValidatorService();
		OderService oderService = provider.getOderService();

		Oder oder = new Oder();

		if (!validatorService.loginationValidator(request, response)) {
			response.sendRedirect(CommandField.PATH_CHOOSE_CAR);
			return;
		}
		int oderId = Integer.parseInt(request.getParameter(CommandField.LINK_ID_ODERS));

		try {
			oder = oderService.findOderById(oderId);
			Date dateOder = new SimpleDateFormat("yyyy-MM-dd").parse(oder.getDateStart());

			if (new Date().compareTo(dateOder) > 0) {

				RequestDispatcher requestDispather = request.getRequestDispatcher(CommandField.PATH_ODER_CANCEL);
				requestDispather.forward(request, response);
				return;
			}
			oderService.cancelOder(oderId, CommandField.CANCEL_ODER);
			RequestDispatcher requestDispather = request.getRequestDispatcher(CommandField.PATH_CABINET);
			requestDispather.forward(request, response);

		} catch (ServiceException e) {
			LOGGER.error("CancelOderByUser::ServiceException.");
			request.setAttribute(CommandField.MESSAGE_ERROR, "Cannot cancel oder, please try again later");
			RequestDispatcher requestDispather = request.getRequestDispatcher(CommandField.PATH_PAGE_ERROR);
			requestDispather.forward(request, response);
		} catch (ParseException e) {
			LOGGER.error("CancelOderByUser::ParseException.");
			request.setAttribute(CommandField.MESSAGE_ERROR, "Date is wrong, please check it");
			RequestDispatcher requestDispather = request.getRequestDispatcher(CommandField.PATH_PAGE_ERROR);
			requestDispather.forward(request, response);
		}

	}

}
