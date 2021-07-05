package by.htp.jwd.dao;

import java.util.List;

import by.htp.jwd.bean.RepairBill;

public interface RepairBillDAO {
	
	
	final String ODER_ID ="oder.Id";
	final String ODER_DATE_START ="oder.date_of_start";
	final String ODER_DATE_END ="oder.date_of_end";
	final String ODER_TINE_START ="oder.time_of_start";
	final String ODER_TINE_END ="oder.time_of_end";
	final String ODER_COST ="oder.cost_oder";
	final String ODER_STATUS ="oder.status_oder";
	final String CAR_ID ="oder.carId";
	final String USER_ID ="oder.userId";
	final String CAR_BRAND ="car.brand";
	final String CAR_BRAND_DESCRIBE ="car.describe_of_brand";
	final String CAR_BODYWORK ="car.bodywork";
	final String CAR_POWER ="car.power";
	final String CAR_TRANSMISSION ="car.transmission";
	final String NUMBER_OF_DOORS = "number_of_doors";
	final String YEAR = "year";
	final String PHOTO_PATH = "photoPath";
	final String PHOTO = "photo";
	final String USER_NAME ="user.name";
	final String USER_SURNAME ="user.surname";
	final String USER_PHONE ="user.phone";
	final String USER_EMAIL ="user.email";
	final String ADRESS_ID_START = "adressId";
	final String ADRESS_ID_END = "adressIdend";
	final String ADRESS_START = "adressStart.adress_all";
	final String ADRESS_END = "adressEnd.adress_all";
	final String REPAIR_ID ="repair_bill.Id";
	final String REPAIR_DATE ="repair_bill.date_repair";
	final String REPAIR_CONTENT ="repair_bill.content";
	final String REPAIR_COST ="repair_bill.cost_repair";
	final String REPAIR_STATUS ="repair_bill.status_repair_bill";
	final String SELECT_COUNT_ID_FROM_REPAIR = "SELECT COUNT(Id) FROM repair_bill";
	final String COUNT_ID = "COUNT(Id)";
	final String ADD_REPAIR_BILL = "INSERT INTO repair_bill (date_repair, content, cost_repair, status_repair_bill, oderId  ) values ( ?, ?, ?, ?, ?)";
	final String ALL_REPAIR_BILL_FIRST ="SELECT * FROM repair_bill LEFT JOIN oder ON oder.Id = repair_bill.oderId LEFT JOIN car ON oder.carId = car.Id LEFT JOIN user ON oder.userId = user.Id order by oder.date_of_start desc LIMIT 0,15";
	final String ALL_REPAIR_BILL_SECOND ="SELECT * FROM repair_bill LEFT JOIN oder ON oder.Id = repair_bill.oderId LEFT JOIN car ON oder.carId = car.Id LEFT JOIN user ON oder.userId = user.Id  order by oder.date_of_start desc LIMIT ?,?";
	final String REPAIR_BILL_NOT_PAY_FIRST ="SELECT * FROM repair_bill LEFT JOIN oder ON oder.Id = repair_bill.oderId LEFT JOIN car ON oder.carId = car.Id LEFT JOIN user ON oder.userId = user.Id WHERE status_repair_bill = 0 order by oder.date_of_start desc LIMIT 0,15";
	final String REPAIR_BILL_NOT_PAY_SECOND ="SELECT * FROM repair_bill LEFT JOIN oder ON oder.Id = repair_bill.oderId LEFT JOIN car ON oder.carId = car.Id LEFT JOIN user ON oder.userId = user.Id WHERE status_repair_bill = 0  order by oder.date_of_start desc LIMIT ?,?";
	final String ALL_REPAIR_BILL_BY_USER_FIRST ="SELECT * FROM repair_bill LEFT JOIN oder ON oder.Id = repair_bill.oderId LEFT JOIN car ON oder.carId = car.Id LEFT JOIN user ON oder.userId = user.Id WHERE user.Id = ? AND status_repair_bill = 1 order by oder.date_of_start desc LIMIT 0,15";
	final String ALL_REPAIR_BILL_BY_USER_SECOND ="SELECT * FROM repair_bill LEFT JOIN oder ON oder.Id = repair_bill.oderId LEFT JOIN car ON oder.carId = car.Id LEFT JOIN user ON oder.userId = user.Id  WHERE user.Id = ? AND status_repair_bill = 1 order by oder.date_of_start desc LIMIT ?,?";
	final String ALL_REPAIR_BILL_NOT_PAY_BY_USER_FIRST ="SELECT * FROM repair_bill LEFT JOIN oder ON oder.Id = repair_bill.oderId LEFT JOIN car ON oder.carId = car.Id LEFT JOIN user ON oder.userId = user.Id WHERE user.Id = ? AND status_repair_bill = 0  order by oder.date_of_start desc LIMIT 0,15";
	final String ALL_REPAIR_BILL_NOT_PAY_BY_USER_SECOND ="SELECT * FROM repair_bill LEFT JOIN oder ON oder.Id = repair_bill.oderId LEFT JOIN car ON oder.carId = car.Id LEFT JOIN user ON oder.userId = user.Id  WHERE user.Id = ? AND status_repair_bill = 0  order by oder.date_of_start desc LIMIT ?,?";
	final String REPAIR_BILL_PAY = "UPDATE repair_bill SET status_repair_bill='1' WHERE Id =?";
	
	public boolean addRepairBill(RepairBill  repairBill) throws DAOException;
	
	public Integer getNumberRows() throws DAOException;
	
	public List<RepairBill> allRepairBill(int numberPage) throws DAOException;
	
	public List<RepairBill> repairBillNotPay(int numberPage) throws DAOException;
	
	public List<RepairBill> allRepairBillNotPayByUser(int numberPage, int userId) throws DAOException;
	
	public List<RepairBill> allRepairBillByUser(int numberPage, int userId) throws DAOException;
	
	public void payRepairBill(int repairBillId) throws DAOException;
	
	
	
}
