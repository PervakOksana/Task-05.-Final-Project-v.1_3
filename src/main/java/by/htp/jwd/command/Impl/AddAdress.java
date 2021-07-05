package by.htp.jwd.command.Impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.htp.jwd.bean.Adress;
import by.htp.jwd.command.Command;
import by.htp.jwd.command.CommandField;
import by.htp.jwd.service.AdressService;
import by.htp.jwd.service.OderService;
import by.htp.jwd.service.ServiceException;
import by.htp.jwd.service.ServiceProvider;
import by.htp.jwd.service.ValidatorService;

public class AddAdress implements Command{
	
	private final Logger LOGGER = LoggerFactory.getLogger(AddAdress.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ServiceProvider provider = ServiceProvider.getInstance();
		ValidatorService validatorService = provider.getValidatorService();
		AdressService adressService = provider.getAdressService();
	
		String country = request.getParameter(CommandField.COUNTRY);
		String city = request.getParameter(CommandField.CITY);
		String street = request.getParameter(CommandField.STREET);
		String house = request.getParameter(CommandField.HOUSE);
		
		if (!validatorService.loginationAdminValidator(request, response)) {
			response.sendRedirect(CommandField.PATH_LOGINATION_PAGE);
			return;
		}
		Adress adress = new Adress(city, country, street, house, city+ ", " +street+ ", " +house, "1");		

		try {
			adressService.addAdress(adress);
			response.sendRedirect(CommandField.PATH_CABINET_PAGE);
			
		} catch (ServiceException e) {
			LOGGER.error("AddAdress::ServiceException.");
			request.setAttribute(CommandField.MESSAGE_ERROR, "Cannot get adress list, please try again later");
			RequestDispatcher requestDispather = request.getRequestDispatcher(CommandField.PATH_PAGE_ERROR);
			requestDispather.forward(request, response);

		}
	}
}