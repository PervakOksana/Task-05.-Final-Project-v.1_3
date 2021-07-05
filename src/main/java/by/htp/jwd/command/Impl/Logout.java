package by.htp.jwd.command.Impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.htp.jwd.command.Command;
import by.htp.jwd.command.CommandField;

public class Logout implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		if (session != null) {
			session.removeAttribute(CommandField.AUTH);
			session.removeAttribute(CommandField.ROLE_LOGIN);
			session.removeAttribute(CommandField.MESSAGE);
			session.removeAttribute(CommandField.USER_ID_LOGIN);
		}
		response.sendRedirect(CommandField.PATH_CHOOSE_CAR);
	}

}
