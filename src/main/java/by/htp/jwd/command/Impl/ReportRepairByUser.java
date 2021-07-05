package by.htp.jwd.command.Impl;

import java.io.IOException;
import java.util.ArrayList;
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

public class ReportRepairByUser implements Command {

	private final Logger LOGGER = LoggerFactory.getLogger(ReportRepairByUser.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ServiceProvider provider = ServiceProvider.getInstance();
		ValidatorService validatorService = provider.getValidatorService();
		RepairBillService repairBillService = provider.getRepairBillService();
		HttpSession session = request.getSession();
		String PATH = "";
		List<RepairBill> repairBill = new ArrayList<RepairBill>();

		if (!validatorService.loginationValidator(request, response)) {
			response.sendRedirect(CommandField.PATH_CHOOSE_CAR);
			return;
		}
		try {
			int userId = (int) session.getAttribute(CommandField.USER_ID_LOGIN);
			int currentPage = 1;
			int recordsPerPage = 15;
			if (request.getParameter(CommandField.T_PAGE) != null) {
				currentPage = Integer.parseInt(request.getParameter(CommandField.T_PAGE));
			}
			if (!CommandField.ARCHIVE.equals(request.getParameter(CommandField.TAKE))) {
				repairBill = repairBillService.allRepairBillNotPayByUser(currentPage, userId);
				PATH = CommandField.PATH_REPAIR_REPORT;
			}
			if (CommandField.ARCHIVE.equals(request.getParameter(CommandField.TAKE))) {
				repairBill = repairBillService.allRepairBillByUser(currentPage, userId);
				PATH = CommandField.PATH_REPAIR_REPORT_ARCHIVE;
			}
			int rows = repairBillService.getNumberRows();
			int nOfPages = rows / recordsPerPage;
			if (nOfPages % recordsPerPage > 0) {
				nOfPages++;
			}
			request.setAttribute(CommandField.NUMBER_PAGE, nOfPages);
			request.setAttribute(CommandField.CURRENT_PAGE, currentPage);
			request.setAttribute(CommandField.RECORD_PAGE, recordsPerPage);
			request.setAttribute(CommandField.REPAIR_BILL_ALL, repairBill);

			RequestDispatcher requestDispather = request.getRequestDispatcher(PATH);
			requestDispather.forward(request, response);

		} catch (ServiceException e) {
			LOGGER.error("ReportRepairByUser::ServiceException.");
			request.setAttribute(CommandField.MESSAGE_ERROR, "Cannot get repair bill list, please try again later");
			RequestDispatcher requestDispather = request.getRequestDispatcher(CommandField.PATH_PAGE_ERROR);
			requestDispather.forward(request, response);
		}

	}

}
