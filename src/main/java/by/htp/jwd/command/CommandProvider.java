package by.htp.jwd.command;

import java.util.HashMap;
import java.util.Map;

import by.htp.jwd.command.Impl.AddAdress;
import by.htp.jwd.command.Impl.AddNewCar;
import by.htp.jwd.command.Impl.AddNewCarView;
import by.htp.jwd.command.Impl.AddOder;
import by.htp.jwd.command.Impl.AddRepairBill;
import by.htp.jwd.command.Impl.AddRepairBillView;
import by.htp.jwd.command.Impl.AllCarsView;
import by.htp.jwd.command.Impl.CancelOderByUser;
import by.htp.jwd.command.Impl.CancelOrder;
import by.htp.jwd.command.Impl.ChooseCarView;
import by.htp.jwd.command.Impl.CompletedOder;
import by.htp.jwd.command.Impl.DeleteCar;
import by.htp.jwd.command.Impl.ForgotPassword;
import by.htp.jwd.command.Impl.InfoCar;
import by.htp.jwd.command.Impl.Localization;
import by.htp.jwd.command.Impl.Logination;
import by.htp.jwd.command.Impl.Logout;
import by.htp.jwd.command.Impl.LookingCar;
import by.htp.jwd.command.Impl.PayForCar;
import by.htp.jwd.command.Impl.PayForRepairBill;
import by.htp.jwd.command.Impl.RegistrationView;
import by.htp.jwd.command.Impl.ReportAllCar;
import by.htp.jwd.command.Impl.ReportOderByUser;
import by.htp.jwd.command.Impl.ReportRepairBill;
import by.htp.jwd.command.Impl.ReportRepairByUser;
import by.htp.jwd.command.Impl.SaveUser;
import by.htp.jwd.command.Impl.ToAddAdressPage;
import by.htp.jwd.command.Impl.ToCabinetPage;
import by.htp.jwd.command.Impl.ToCancelOderPage;
import by.htp.jwd.command.Impl.ToFindPage;
import by.htp.jwd.command.Impl.ToForgotPasswordPage;
import by.htp.jwd.command.Impl.ToInfoPage;
import by.htp.jwd.command.Impl.ToLoginationPage;
import by.htp.jwd.command.Impl.ToPayPage;
import by.htp.jwd.command.Impl.ToPayRepairBillPage;
import by.htp.jwd.command.Impl.ToPaymaentContactPage;
import by.htp.jwd.command.Impl.ToRepairBillPage;
import by.htp.jwd.command.Impl.ToUpdateCarPage;
import by.htp.jwd.command.Impl.ToUpdateUserPage;
import by.htp.jwd.command.Impl.UpdateCar;
import by.htp.jwd.command.Impl.UpdateInfoPage;
import by.htp.jwd.command.Impl.UpdateUser;



public class CommandProvider {
	
	private Map<CommandName, Command> commands = new HashMap<>();

	public CommandProvider() {
		commands.put(CommandName.LOGINATION, new Logination());	
		commands.put(CommandName.REGISTRATION, new RegistrationView());
		commands.put(CommandName.SAVEUSER, new SaveUser());
		commands.put(CommandName.ADDNEWCAR, new AddNewCar());
		commands.put(CommandName.ADDNEWCARVIEW, new AddNewCarView());
		commands.put(CommandName.ALLCARSVIEW, new AllCarsView());
		commands.put(CommandName.CHOOSECARVIEW, new ChooseCarView());
		commands.put(CommandName.TOLOGINATIONPAGE, new ToLoginationPage());
		commands.put(CommandName.LOOKINGCAR, new LookingCar());
		commands.put(CommandName.LOCALIZATION, new Localization());
		commands.put(CommandName.INFOCAR, new InfoCar());
		commands.put(CommandName.TOPAYPAGE, new ToPayPage());
		commands.put(CommandName.LOGOUT, new Logout());
		commands.put(CommandName.PAYFORCAR, new PayForCar());
		commands.put(CommandName.TOFORGOTPASSWORDPAGE, new ToForgotPasswordPage());
		commands.put(CommandName.FORGOTPASSWORD, new ForgotPassword());
		commands.put(CommandName.TOFINDPAGE, new ToFindPage());
		commands.put(CommandName.TOCABINETPAGE, new ToCabinetPage());
		commands.put(CommandName.ADDODER, new AddOder());
		commands.put(CommandName.ADDREPAIRBILLVIEW, new AddRepairBillView());
		commands.put(CommandName.TOREPAIRBILLPAGE, new ToRepairBillPage());
		commands.put(CommandName.ADDREPAIRBILL, new AddRepairBill());
		commands.put(CommandName.REPORTREPAIRBILL, new ReportRepairBill());
		commands.put(CommandName.TOUPDATEUSERPAGE, new ToUpdateUserPage());
		commands.put(CommandName.UPDATEUSER, new UpdateUser());
		commands.put(CommandName.REPORTODERBYUSER, new ReportOderByUser());
		commands.put(CommandName.CANCELODERBYUSER, new CancelOderByUser());
		commands.put(CommandName.REPORTREPAIRBYUSER, new ReportRepairByUser());
		commands.put(CommandName.REPORTALLCAR, new ReportAllCar());
		commands.put(CommandName.TOUPDATECARPAGE, new ToUpdateCarPage());
		commands.put(CommandName.UPDATECAR, new UpdateCar());
		commands.put(CommandName.TOINFOPAGE , new ToInfoPage());
		commands.put(CommandName.TOPAYREPAIRBILLPAGE , new ToPayRepairBillPage());
		commands.put(CommandName.PAYFORREPAIRBILL , new PayForRepairBill());
		commands.put(CommandName.DELETECAR , new DeleteCar());
		commands.put(CommandName.TOCANCELODERPAGE , new ToCancelOderPage());
		commands.put(CommandName.CANCELODER , new CancelOrder());
		commands.put(CommandName.COMPLETEDORDER , new CompletedOder());
		commands.put(CommandName.TOPAYMAENTCONTACTPAGE , new ToPaymaentContactPage());
		commands.put(CommandName.UPDATEINFOPAGE, new UpdateInfoPage());
		commands.put(CommandName.TOADDADRESSPAGE, new ToAddAdressPage());
		commands.put(CommandName.ADDADRESS, new AddAdress());
	}

	public Command takeCommand(String name) {
		CommandName commandName;
		commandName = CommandName.valueOf(name.toUpperCase());
		return commands.get(commandName);
	}
}