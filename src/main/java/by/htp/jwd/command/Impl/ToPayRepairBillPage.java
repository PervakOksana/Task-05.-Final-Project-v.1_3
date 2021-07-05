package by.htp.jwd.command.Impl;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import by.htp.jwd.command.Command;
import by.htp.jwd.command.CommandField;
import by.htp.jwd.service.CarService;
import by.htp.jwd.service.OderService;
import by.htp.jwd.service.ServiceProvider;
import by.htp.jwd.service.ValidatorService;

public class ToPayRepairBillPage implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ServiceProvider provider = ServiceProvider.getInstance();
		ValidatorService validatorService = provider.getValidatorService();

		String id = request.getParameter(CommandField.LINK_ID_REPAIR);
		request.setAttribute(CommandField.LINK_ID_REPAIR_N, id);
		if (!validatorService.loginationValidator(request, response)) {

			response.sendRedirect(CommandField.PATH_LOGINATION_PAGE);
			return;
		}

		RequestDispatcher requestDispather = request.getRequestDispatcher(CommandField.PATH_PAY_BILL);
		requestDispather.forward(request, response);

	}

}
