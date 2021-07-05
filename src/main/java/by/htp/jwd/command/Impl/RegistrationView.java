package by.htp.jwd.command.Impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.htp.jwd.command.Command;
import by.htp.jwd.command.CommandField;

public class RegistrationView implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		if (!CommandField.ERROR_IS.equals(request.getParameter(CommandField.ERROR))) {
			session.removeAttribute(CommandField.NOTICE_DATE);
			session.removeAttribute(CommandField.NOTICE_PASSWORD);
			session.removeAttribute(CommandField.NOTICE_EMAIL);
			session.removeAttribute(CommandField.NOTICE_EMPTY);
		}
		if (CommandField.TAKE_IS.equals(request.getParameter(CommandField.TAKE))) {
			RequestDispatcher requestDispather = request.getRequestDispatcher(CommandField.PATH_REGISTRATION_ADMIN);
			requestDispather.forward(request, response);
			return;
		}
		RequestDispatcher requestDispather = request.getRequestDispatcher(CommandField.PATH_REGISTRATION);
		requestDispather.forward(request, response);
	}

}
