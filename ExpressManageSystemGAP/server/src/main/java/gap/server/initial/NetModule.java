package gap.server.initial;

import gap.common.dataservice.Contactor;
import gap.common.dataservice.accountdataservice.AccountDataService;
import gap.common.dataservice.expressorderdataservice.ExpressOrderDataService;
import gap.common.dataservice.initialdata.InitialDataService;
import gap.common.dataservice.inventorydataservice.InventoryDataService;
import gap.common.dataservice.logdataservice.LogDataService;
import gap.common.dataservice.managedataservice.InstitutionDataService;
import gap.common.dataservice.orderdataservice.ArrivedOrderDataService;
import gap.common.dataservice.orderdataservice.DeliveryOrderDataService;
import gap.common.dataservice.orderdataservice.LoadOrderDataService;
import gap.common.dataservice.orderdataservice.StockinOrderDataService;
import gap.common.dataservice.orderdataservice.StockoutOrderDataService;
import gap.common.dataservice.receiptdataservice.BillOrderDataService;
import gap.common.dataservice.receiptdataservice.PaymentdataService;
import gap.common.dataservice.strategydataservice.CityDataService;
import gap.common.dataservice.strategydataservice.PriceDataService;
import gap.common.dataservice.strategydataservice.RentDataService;
import gap.common.dataservice.strategydataservice.SalaryDataService;
import gap.common.dataservice.transFareDataService.TransFareDataService;
import gap.common.dataservice.transdataservice.CarDataService;
import gap.common.dataservice.transdataservice.DriverDataService;
import gap.common.dataservice.userdataservice.UserDataService;
import gap.server.databaseutility.Excutor;

public class NetModule {
	public static Excutor excutor;
	public static AccountDataService accountDataService;
	public static UserDataService userdataservice;
	public static LogDataService logdataservice;
	public static CarDataService cardataservice;
	public static DriverDataService driverdataservice;
	public static ArrivedOrderDataService arrivedOrderdataservice;
	public static ExpressOrderDataService expressorderdataservice;
	public static DeliveryOrderDataService deliveryorderdataservice;
	public static LoadOrderDataService loadorderdataservice;
	public static PriceDataService pricedataservice;
	public static RentDataService rentdataservice;
	public static SalaryDataService salarydataservice;
	public static CityDataService citydataservice;
	public static InstitutionDataService institutiondataservice;
	public static BillOrderDataService billorderdataservice;
	public static PaymentdataService paymentdataService;
	public static InventoryDataService inventorydataservice;
	public static StockinOrderDataService stockinorderdataservice;
	public static StockoutOrderDataService stockoutorderdataservice;
	public static TransFareDataService transFareDataService;
	public static InitialDataService initialDataService;

	public static Contactor contactor;
}
