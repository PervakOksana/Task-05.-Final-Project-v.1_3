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

import by.htp.jwd.bean.ApplicationInfo;
import by.htp.jwd.bean.Oder;
import by.htp.jwd.bean.RepairBill;
import by.htp.jwd.command.Command;
import by.htp.jwd.command.CommandField;
import by.htp.jwd.service.ApplicationInfoService;
import by.htp.jwd.service.OderService;
import by.htp.jwd.service.RepairBillService;
import by.htp.jwd.service.SenderService;
import by.htp.jwd.service.ServiceException;
import by.htp.jwd.service.ServiceProvider;
import by.htp.jwd.service.ValidatorService;

public class AddRepairBill implements Command {

	private final Logger LOGGER = LoggerFactory.getLogger(AddRepairBill.class);
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ServiceProvider provider = ServiceProvider.getInstance();
		ValidatorService validatorService = provider.getValidatorService();
		OderService oderService = provider.getOderService();
		RepairBillService repairBillService = provider.getRepairBillService();
		SenderService senderService = provider.getSenderService();
		ApplicationInfoService applicationInfoService = provider.getApplicationInfoService();
		HttpSession session = request.getSession();
		
		String idOderSrc =  (String) session.getAttribute(CommandField.LINK_ID_ODER);
		
		if (!validatorService.loginationAdminValidator(request, response)) {
			response.sendRedirect(CommandField.PATH_LOGINATION_PAGE);
			return;
		}
		
		if (!validatorService.repairValidator(request)) {
			session.setAttribute(CommandField.NOTICE_NUMBER, CommandField.ERROR_NUMBER);
			response.sendRedirect(CommandField.PATH_ADD_REPAIR_ERROR_PAGE+idOderSrc);
			return;
		}

		String dateRepair = request.getParameter(CommandField.DATE_REPAIR);
		String content = request.getParameter(CommandField.CONTENT);
		String costRepair = request.getParameter(CommandField.COST_REPAIR);
		int idOder = Integer.parseInt(idOderSrc);	
		Oder oder = new Oder (idOder);
		RepairBill  repairBill = new RepairBill (dateRepair, content, costRepair, CommandField.STATUS_REPAIR,  oder);		
		
		try {
			repairBillService.addRepairBill(repairBill);
			oder = oderService.findOderById(idOder);
			ApplicationInfo applicationInfo = applicationInfoService.getApplicationInfo();
			senderService.send(applicationInfo.getLetterBill(), applicationInfo.getLetterBill(),
					applicationInfo.getAdressEmail(), applicationInfo.getPasswordEmail(), oder.getUser().getEmail());
			response.sendRedirect(CommandField.TO_REPAIR_BILLREPORT_PAGE);
		} catch (ServiceException e) {
			LOGGER.error("AddRepairBill::ServiceException.");
			request.setAttribute(CommandField.MESSAGE_ERROR, "Cannot save repair bill, please try again later");
			RequestDispatcher requestDispather = request.getRequestDispatcher(CommandField.PATH_PAGE_ERROR);
			requestDispather.forward(request, response);
		}
		
	}

}
