package by.htp.jwd.command.Impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.htp.jwd.bean.ApplicationInfo;
import by.htp.jwd.command.Command;
import by.htp.jwd.command.CommandField;
import by.htp.jwd.service.ApplicationInfoService;
import by.htp.jwd.service.OderService;
import by.htp.jwd.service.SenderService;
import by.htp.jwd.service.ServiceException;
import by.htp.jwd.service.ServiceProvider;
import by.htp.jwd.service.ValidatorService;

public class CancelOrder implements Command {

	private final Logger LOGGER = LoggerFactory.getLogger(CancelOrder.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ServiceProvider provider = ServiceProvider.getInstance();
		ValidatorService validatorService = provider.getValidatorService();
		OderService oderService = provider.getOderService();
		SenderService senderService = provider.getSenderService();
		ApplicationInfoService applicationInfoService = provider.getApplicationInfoService();

		String userEmail = request.getParameter(CommandField.EMAIL);
		String reason = request.getParameter(CommandField.REASON);

		if (!validatorService.loginationAdminValidator(request, response)) {

			response.sendRedirect(CommandField.PATH_LOGINATION_PAGE);
			return;
		}
		int oderId = Integer.parseInt(request.getParameter(CommandField.ODER_ID));
		try {
			ApplicationInfo applicationInfo = applicationInfoService.getApplicationInfo();
			senderService.send(CommandField.REASON_CANCEL, reason, applicationInfo.getAdressEmail(),
					applicationInfo.getPasswordEmail(), userEmail);
			oderService.cancelOder(oderId, CommandField.ODER_CANCEL);
			response.sendRedirect(CommandField.PATH_REPORT_ODER_PAGE);
		} catch (ServiceException e) {
			LOGGER.error("CancelOrder::ServiceException.");
			request.setAttribute(CommandField.MESSAGE_ERROR, "Cannot cancel oder, please try again later");
			RequestDispatcher requestDispather = request.getRequestDispatcher(CommandField.PATH_PAGE_ERROR);
			requestDispather.forward(request, response);
		}
	}

}
