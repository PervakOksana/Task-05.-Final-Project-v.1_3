package by.htp.jwd.command.Impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.jwd.command.Command;
import by.htp.jwd.command.CommandField;
import by.htp.jwd.service.ServiceProvider;
import by.htp.jwd.service.ValidatorService;

public class ToCabinetPage implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ServiceProvider provider = ServiceProvider.getInstance();
		ValidatorService validatorService = provider.getValidatorService();

		if (!validatorService.loginationValidator(request, response)) {
			response.sendRedirect(CommandField.PATH_CHOOSE_CAR);
			return;
		}
		RequestDispatcher requestDispather = request.getRequestDispatcher(CommandField.PATH_CABINET);
		requestDispather.forward(request, response);
	}
}
