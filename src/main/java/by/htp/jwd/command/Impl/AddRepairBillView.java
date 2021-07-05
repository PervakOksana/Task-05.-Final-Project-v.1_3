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
import by.htp.jwd.command.Command;
import by.htp.jwd.command.CommandField;
import by.htp.jwd.dao.impl.OderDAOimpl;
import by.htp.jwd.service.OderService;
import by.htp.jwd.service.ServiceException;
import by.htp.jwd.service.ServiceProvider;
import by.htp.jwd.service.ValidatorService;

public class AddRepairBillView implements Command {

	private final Logger LOGGER = LoggerFactory.getLogger(AddRepairBillView.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ServiceProvider provider = ServiceProvider.getInstance();
		ValidatorService validatorService = provider.getValidatorService();
		OderService oderService = provider.getOderService();
		HttpSession session = request.getSession();
		List<Oder> oders = new ArrayList<Oder>();

		if (!validatorService.loginationValidator(request, response)) {
			response.sendRedirect(CommandField.PATH_CHOOSE_CAR);
			return;
		}

		if (!"is".equals(request.getParameter("error"))) {
			session.removeAttribute(CommandField.NOTICE_NUMBER);
		}

		try {
			int userId = (int) session.getAttribute(CommandField.USER_ID_LOGIN);

			if (!CommandField.ARCHIVE.equals(request.getParameter(CommandField.TAKE))) {
				oders = oderService.allOderNew();
				request.setAttribute(CommandField.ODERS, oders);
				RequestDispatcher requestDispather = request.getRequestDispatcher(CommandField.PATH_ODER_REPORT);
				requestDispather.forward(request, response);
				return;
			}

			int currentPage = 1;
			int recordsPerPage = 15;
			if (request.getParameter(CommandField.T_PAGE) != null) {

				currentPage = Integer.parseInt(request.getParameter(CommandField.T_PAGE));
			}

			oders = oderService.allOder(currentPage);

			int rows = oderService.getNumberRows();

			int nOfPages = rows / recordsPerPage;

			if (nOfPages % recordsPerPage > 0) {

				nOfPages++;
			}

			request.setAttribute(CommandField.NUMBER_PAGE, nOfPages);
			request.setAttribute(CommandField.CURRENT_PAGE, currentPage);
			request.setAttribute(CommandField.RECORD_PAGE, recordsPerPage);

			request.setAttribute(CommandField.ODERS, oders);

			RequestDispatcher requestDispather = request.getRequestDispatcher(CommandField.PATH_ODER_REPORT_ARCHIVE);
			requestDispather.forward(request, response);

		} catch (ServiceException e) {
			LOGGER.error("AddRepairBillView::ServiceException.");
			request.setAttribute(CommandField.MESSAGE_ERROR, "Cannot get response, please try again later");
			RequestDispatcher requestDispather = request.getRequestDispatcher(CommandField.PATH_PAGE_ERROR);
			requestDispather.forward(request, response);
		}
	}

}
