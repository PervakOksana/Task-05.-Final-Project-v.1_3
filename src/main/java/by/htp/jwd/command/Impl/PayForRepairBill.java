package by.htp.jwd.command.Impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.htp.jwd.command.Command;
import by.htp.jwd.command.CommandField;
import by.htp.jwd.service.CarService;
import by.htp.jwd.service.OderService;
import by.htp.jwd.service.RepairBillService;
import by.htp.jwd.service.ServiceException;
import by.htp.jwd.service.ServiceProvider;
import by.htp.jwd.service.ValidatorService;

public class PayForRepairBill implements Command {

	private final Logger LOGGER = LoggerFactory.getLogger(PayForRepairBill.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ServiceProvider provider = ServiceProvider.getInstance();
		RepairBillService repairBillService = provider.getRepairBillService();
		ValidatorService validatorService = provider.getValidatorService();

		if (!validatorService.loginationValidator(request, response)) {
			response.sendRedirect(CommandField.PATH_LOGINATION_PAGE);
			return;
		}
		int id = Integer.parseInt(request.getParameter(CommandField.LINK_ID_REPAIR_N));
		try {
			repairBillService.payRepairBill(id);
			response.sendRedirect(CommandField.TO_REPAIR_BILLREPORT_PAGE_USER);
		} catch (ServiceException e) {
			LOGGER.error("PayForRepairBill::ServiceException.");
			request.setAttribute(CommandField.MESSAGE_ERROR, "Cannot get payment, please try again later");
			RequestDispatcher requestDispather = request.getRequestDispatcher(CommandField.PATH_PAGE_ERROR);
			requestDispather.forward(request, response);
			
		}

	}

}
