package by.htp.jwd.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.htp.jwd.bean.RepairBill;
import by.htp.jwd.dao.DAOException;
import by.htp.jwd.dao.DAOProvider;
import by.htp.jwd.dao.RepairBillDAO;
import by.htp.jwd.service.RepairBillService;
import by.htp.jwd.service.ServiceException;

public class RepairBillServiceImpl implements RepairBillService {

	private final Logger LOGGER = LoggerFactory.getLogger(RepairBillServiceImpl.class);

	@Override
	public boolean addRepairBill(RepairBill repairBill) throws ServiceException {

		LOGGER.info("RepairBillServiceImpl started addRepairBill.");

		DAOProvider proviger = DAOProvider.getInstance();
		RepairBillDAO repairBillDAO = proviger.getRepairBilldao();

		boolean result = false;

		try {
			result = repairBillDAO.addRepairBill(repairBill);

		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return result;
	}

	@Override
	public List<RepairBill> allRepairBill(int numberPage) throws ServiceException {

		LOGGER.info("RepairBillServiceImpl started allRepairBill.");

		DAOProvider proviger = DAOProvider.getInstance();
		RepairBillDAO repairBillDAO = proviger.getRepairBilldao();

		List<RepairBill> repairBills = new ArrayList<RepairBill>();

		try {
			repairBills = repairBillDAO.allRepairBill(numberPage);

		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return repairBills;
	}

	@Override
	public Integer getNumberRows() throws ServiceException {

		LOGGER.info("RepairBillServiceImpl started getNumberRows.");

		DAOProvider proviger = DAOProvider.getInstance();
		RepairBillDAO repairBillDAO = proviger.getRepairBilldao();

		Integer number = 0;
		try {
			number = repairBillDAO.getNumberRows();

		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return number;
	}

	@Override
	public List<RepairBill> repairBillNotPay(int numberPage) throws ServiceException {

		LOGGER.info("RepairBillServiceImpl started repairBillNotPay.");

		DAOProvider proviger = DAOProvider.getInstance();
		RepairBillDAO repairBillDAO = proviger.getRepairBilldao();

		List<RepairBill> repairBills = new ArrayList<RepairBill>();

		try {
			repairBills = repairBillDAO.repairBillNotPay(numberPage);

		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return repairBills;
	}

	@Override
	public List<RepairBill> allRepairBillNotPayByUser(int numberPage, int userId) throws ServiceException {

		LOGGER.info("RepairBillServiceImpl started allRepairBillNotPayByUser.");

		DAOProvider proviger = DAOProvider.getInstance();
		RepairBillDAO repairBillDAO = proviger.getRepairBilldao();

		List<RepairBill> repairBills = new ArrayList<RepairBill>();

		try {
			repairBills = repairBillDAO.allRepairBillNotPayByUser(numberPage, userId);

		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return repairBills;
	}

	@Override
	public List<RepairBill> allRepairBillByUser(int numberPage, int userId) throws ServiceException {

		LOGGER.info("RepairBillServiceImpl started allRepairBillByUser.");

		DAOProvider proviger = DAOProvider.getInstance();
		RepairBillDAO repairBillDAO = proviger.getRepairBilldao();

		List<RepairBill> repairBills = new ArrayList<RepairBill>();

		try {
			repairBills = repairBillDAO.allRepairBillByUser(numberPage, userId);

		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return repairBills;
	}

	@Override
	public void payRepairBill(int repairBillId) throws ServiceException {

		LOGGER.info("RepairBillServiceImpl started payRepairBill.");

		DAOProvider proviger = DAOProvider.getInstance();
		RepairBillDAO repairBillDAO = proviger.getRepairBilldao();

		try {
		repairBillDAO.payRepairBill(repairBillId);

		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
	}

}
