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
import by.htp.jwd.bean.OderInfo;
import by.htp.jwd.bean.User;
import by.htp.jwd.bean.UserDetail;
import by.htp.jwd.command.Command;
import by.htp.jwd.command.CommandField;
import by.htp.jwd.service.ApplicationInfoService;
import by.htp.jwd.service.OderService;
import by.htp.jwd.service.SenderService;
import by.htp.jwd.service.ServiceException;
import by.htp.jwd.service.ServiceProvider;
import by.htp.jwd.service.UserService;
import by.htp.jwd.service.ValidatorService;

public class AddOder implements Command {

	private final Logger LOGGER = LoggerFactory.getLogger(AddOder.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ServiceProvider provider = ServiceProvider.getInstance();
		OderService oderService = provider.getOderService();
		ValidatorService validatorService = provider.getValidatorService();
		UserService userService = provider.getUserServise();
		SenderService senderService = provider.getSenderService();
		ApplicationInfoService applicationInfoService = provider.getApplicationInfoService();
		HttpSession session = request.getSession();

		UserDetail userDetail = null;

		if (!validatorService.loginationValidator(request, response)) {

			response.sendRedirect(CommandField.PATH_LOGINATION_PAGE);
			return;
		}

		if ("is".equals(request.getParameter("take"))) {
			RequestDispatcher requestDispather = request.getRequestDispatcher(CommandField.PATH_PAY_SUCCESSFUL);
			requestDispather.forward(request, response);
			return;
		}
		OderInfo oderInfo = (OderInfo) session.getAttribute(CommandField.ODER_INFO);
		String carId = (String) session.getAttribute(CommandField.CAR_ID);
		String userId = Integer.toString((int) session.getAttribute(CommandField.USER_ID_LOGIN));

		try {
			oderService.addOder(oderInfo, carId, userId);
			userDetail = userService.findUserViaId(userId);

			if (userDetail.getUser().getEmail() != null) {
				ApplicationInfo applicationInfo = applicationInfoService.getApplicationInfo();
				senderService.send(applicationInfo.getLetterBill(), applicationInfo.getLetterReminder(),
						applicationInfo.getAdressEmail(), applicationInfo.getPasswordEmail(),
						userDetail.getUser().getEmail());
			}

			RequestDispatcher requestDispather = request.getRequestDispatcher(CommandField.PATH_PAY_SUCCESSFUL);
			requestDispather.forward(request, response);

		} catch (ServiceException e) {
			LOGGER.error("AddOder::ServiceException.");
			request.setAttribute(CommandField.MESSAGE_ERROR, "Cannot save oder, please try again later");
			RequestDispatcher requestDispather = request.getRequestDispatcher(CommandField.PATH_PAGE_ERROR);
			requestDispather.forward(request, response);
		}
	}
}
