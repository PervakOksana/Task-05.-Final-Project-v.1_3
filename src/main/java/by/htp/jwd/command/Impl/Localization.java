package by.htp.jwd.command.Impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.htp.jwd.bean.PriceList;
import by.htp.jwd.command.Command;
import by.htp.jwd.command.CommandField;
import by.htp.jwd.dao.impl.CarDAOimpl;

public class Localization implements Command {
	
	private final Logger LOGGER = LoggerFactory.getLogger(Localization.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		LOGGER.info("Command started Localization.");
		
		String language = request.getParameter(CommandField.LOCAL);
		String command = request.getParameter(CommandField.PAGE);
		String archive = request.getParameter(CommandField.TAKE);
		String is = request.getParameter(CommandField.ERROR);
		String anyId = request.getParameter(CommandField.ID);
		request.getSession(true).setAttribute(CommandField.LOCAL, language);		
		response.sendRedirect(CommandField.PATH + command+"&take="+archive+"&error="+is+"&id="+anyId);
	}

}
