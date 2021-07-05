package by.htp.jwd.command.Impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.jwd.command.Command;
import by.htp.jwd.command.CommandField;

public class ToLoginationPage implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher requestDispather = request.getRequestDispatcher(CommandField.PATH_LOGINATION);
		requestDispather.forward(request, response);
	}

}
