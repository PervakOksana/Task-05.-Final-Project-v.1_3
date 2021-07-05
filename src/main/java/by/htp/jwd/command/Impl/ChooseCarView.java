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

import by.htp.jwd.bean.Adress;
import by.htp.jwd.bean.OderInfo;
import by.htp.jwd.command.Command;
import by.htp.jwd.command.CommandField;
import by.htp.jwd.service.OderService;
import by.htp.jwd.service.ServiceException;
import by.htp.jwd.service.ServiceProvider;

public class ChooseCarView implements Command {

	private final Logger LOGGER = LoggerFactory.getLogger(ChooseCarView.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ServiceProvider provider = ServiceProvider.getInstance();
		OderService oderService = provider.getOderService();
		HttpSession session = request.getSession();

		if (!"is".equals(request.getParameter("error"))) {
			session.removeAttribute(CommandField.NOTICE_DATE);
			session.removeAttribute(CommandField.NOTICE_TIME);
			session.removeAttribute(CommandField.NOTICE_DATE_WAIT);
		}

		List<Adress> adresses = new ArrayList<Adress>();

		try {
			adresses = oderService.adressCar();
			request.setAttribute(CommandField.ADRESS, adresses);
			RequestDispatcher requestDispather = request.getRequestDispatcher(CommandField.PATH_PAGE_FIRST);
			requestDispather.forward(request, response);

		} catch (ServiceException e) {
			LOGGER.error("ChooseCarView::ServiceException.");
			request.setAttribute(CommandField.MESSAGE_ERROR, "Cannot get vars list, please try again later");
			RequestDispatcher requestDispather = request.getRequestDispatcher(CommandField.PATH_PAGE_ERROR);
			requestDispather.forward(request, response);

		}

	}

}
