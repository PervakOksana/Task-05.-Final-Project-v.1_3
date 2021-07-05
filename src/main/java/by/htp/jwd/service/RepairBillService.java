package by.htp.jwd.service;

import java.util.List;

import by.htp.jwd.bean.RepairBill;
import by.htp.jwd.dao.DAOException;

public interface RepairBillService {

	public boolean addRepairBill(RepairBill repairBill) throws ServiceException;

	public List<RepairBill> allRepairBill(int numberPage) throws ServiceException;

	public Integer getNumberRows() throws ServiceException;

	public List<RepairBill> repairBillNotPay(int numberPage) throws ServiceException;

	public List<RepairBill> allRepairBillNotPayByUser(int numberPage, int userId) throws ServiceException;

	public List<RepairBill> allRepairBillByUser(int numberPage, int userId) throws ServiceException;
	
	public void payRepairBill(int repairBillId) throws ServiceException;
}
