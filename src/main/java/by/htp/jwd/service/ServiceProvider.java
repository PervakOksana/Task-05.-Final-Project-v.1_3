package by.htp.jwd.service;

import by.htp.jwd.service.impl.AdressServiceImpl;
import by.htp.jwd.service.impl.ApplicationInfoServiceImpl;
import by.htp.jwd.service.impl.CarServiceImpl;
import by.htp.jwd.service.impl.OderServiceImpl;
import by.htp.jwd.service.impl.RepairBillServiceImpl;
import by.htp.jwd.service.impl.SenderServiceImpl;
import by.htp.jwd.service.impl.UserServiceImpl;
import by.htp.jwd.service.impl.ValidatorServiceImpl;

public final class ServiceProvider {
	private static final ServiceProvider instance = new ServiceProvider();

	private ServiceProvider() {
	}

	private final UserService userServise = new UserServiceImpl();
	private final CarService carServise = new CarServiceImpl();
	private final ValidatorService validatorService = new ValidatorServiceImpl();
	private final OderService oderService = new OderServiceImpl();
	private final SenderService senderService = new SenderServiceImpl();
	private final RepairBillService repairBillService = new RepairBillServiceImpl();
	private final ApplicationInfoService applicationInfoService = new ApplicationInfoServiceImpl();
	private final AdressService adressService = new AdressServiceImpl();
	
	public static ServiceProvider getInstance() {
		return instance;
	}

	public UserService getUserServise() {
		return userServise;
	}

	public CarService getCarServise() {
		return carServise;
	}

	public ValidatorService getValidatorService() {
		return validatorService;
	}
	
	public OderService getOderService() {
		return oderService;
	}
	public SenderService getSenderService() {
		return senderService;
	}
	
	public RepairBillService getRepairBillService() {
		return repairBillService;
	}

	public ApplicationInfoService getApplicationInfoService() {
		return applicationInfoService;
	}

	public AdressService getAdressService() {
		return adressService;
	}
	
	
}
