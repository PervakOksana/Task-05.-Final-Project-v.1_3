package by.htp.jwd.command.Impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.htp.jwd.bean.ApplicationInfo;
import by.htp.jwd.command.Command;
import by.htp.jwd.command.CommandField;
import by.htp.jwd.service.ApplicationInfoService;
import by.htp.jwd.service.ServiceException;
import by.htp.jwd.service.ServiceProvider;
import by.htp.jwd.service.ValidatorService;

public class UpdateInfoPage implements Command {

	private final Logger LOGGER = LoggerFactory.getLogger(UpdateInfoPage.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ServiceProvider provider = ServiceProvider.getInstance();
		ValidatorService validatorService = provider.getValidatorService();
		ApplicationInfoService applicationInfoService = provider.getApplicationInfoService();
		HttpSession session = request.getSession();

		if (!validatorService.emailValidator(request)) {
			session.setAttribute(CommandField.NOTICE_NUMBER, CommandField.ERROR_NUMBER);
			response.sendRedirect(CommandField.PATH_INFO_PAGE_IS);
			return;
		}
		String contact = request.getParameter(CommandField.CONTACT);
		String payTerm = request.getParameter(CommandField.PAY_TERM);
		String email = request.getParameter(CommandField.EMAIL_IS);
		String password = request.getParameter(CommandField.PASSWORD);
		String letterReminder = request.getParameter(CommandField.LETTER_REMINDER);
		String letterBill = request.getParameter(CommandField.LETTER_BILL);

		ApplicationInfo applicationInfo = new ApplicationInfo(payTerm, contact, email, password, letterReminder,
				letterBill);
		try {
			applicationInfoService.updateApplicationInfo(applicationInfo);
			response.sendRedirect(CommandField.PATH_CABINET_PAGE);
		} catch (ServiceException e) {
			LOGGER.error("UpdateInfoPage::ServiceException.");
			request.setAttribute(CommandField.MESSAGE_ERROR, "Cannot update information, please try again later");
			RequestDispatcher requestDispather = request.getRequestDispatcher(CommandField.PATH_PAGE_ERROR);
			requestDispather.forward(request, response);
		}
	}

}
