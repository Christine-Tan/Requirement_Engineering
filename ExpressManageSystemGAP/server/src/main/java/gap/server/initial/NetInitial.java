package gap.server.initial;

import static gap.server.initial.NetModule.accountDataService;
import static gap.server.initial.NetModule.arrivedOrderdataservice;
import static gap.server.initial.NetModule.billorderdataservice;
import static gap.server.initial.NetModule.cardataservice;
import static gap.server.initial.NetModule.citydataservice;
import static gap.server.initial.NetModule.contactor;
import static gap.server.initial.NetModule.deliveryorderdataservice;
import static gap.server.initial.NetModule.driverdataservice;
import static gap.server.initial.NetModule.expressorderdataservice;
import static gap.server.initial.NetModule.initialDataService;
import static gap.server.initial.NetModule.institutiondataservice;
import static gap.server.initial.NetModule.inventorydataservice;
import static gap.server.initial.NetModule.loadorderdataservice;
import static gap.server.initial.NetModule.logdataservice;
import static gap.server.initial.NetModule.paymentdataService;
import static gap.server.initial.NetModule.pricedataservice;
import static gap.server.initial.NetModule.rentdataservice;
import static gap.server.initial.NetModule.salarydataservice;
import static gap.server.initial.NetModule.stockinorderdataservice;
import static gap.server.initial.NetModule.stockoutorderdataservice;
import static gap.server.initial.NetModule.transFareDataService;
import static gap.server.initial.NetModule.userdataservice;
import gap.common.dataservice.ServiceName;
import gap.common.netconfig.RMIConfig;
import gap.server.data.ContactorImpl;
import gap.server.data.accountdata.AccountDataServiceImpl;
import gap.server.data.expressorder.ExpressOrderDataServiceImpl;
import gap.server.data.initial.InitialDataServiceImpl;
import gap.server.data.inventorydata.InventoryDataServiceImpl;
import gap.server.data.logdata.LogDataServiceImpl;
import gap.server.data.managedata.InstitutionDataServiceImpl;
import gap.server.data.order.ArrivedOrderDataServiceImpl;
import gap.server.data.order.DeliveryOrderDataServiceImpl;
import gap.server.data.order.LoadOrderDataServiceImpl;
import gap.server.data.order.StockinOrderDataServiceImpl;
import gap.server.data.order.StockoutOrderDataServiceImpl;
import gap.server.data.receiptdata.BillOrderDataServiceImpl;
import gap.server.data.receiptdata.PaymentDataServiceImpl;
import gap.server.data.strategydata.CityDataServiceImpl;
import gap.server.data.strategydata.PriceDataServiceImpl;
import gap.server.data.strategydata.RentDataServiceImpl;
import gap.server.data.strategydata.SalaryDataServiceImpl;
import gap.server.data.transFareData.TransFareDataImpl;
import gap.server.data.transdata.CarDataServiceImpl;
import gap.server.data.transdata.DriverDataServiceImpl;
import gap.server.data.userdata.UserDataServiceImpl;
import gap.server.databaseutility.DataBaseLancher;
import gap.server.ui.ServerMainFrame;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class NetInitial {
	private static HashMap<String, Object> serviceMap;
	private static ServerMainFrame serverMainFrame;
	public static void initial() throws RemoteException {
		NetModule.excutor = DataBaseLancher.lanch();
		accountDataService = AccountDataServiceImpl.getInstance();
		userdataservice = UserDataServiceImpl.getInstance();
		logdataservice = LogDataServiceImpl.getInstance();
		cardataservice = CarDataServiceImpl.getInstance();
		driverdataservice = DriverDataServiceImpl.getInstance();
		arrivedOrderdataservice = ArrivedOrderDataServiceImpl.getInstance();
		expressorderdataservice = ExpressOrderDataServiceImpl.getInstance();
		deliveryorderdataservice = DeliveryOrderDataServiceImpl.getInstance();
		loadorderdataservice = LoadOrderDataServiceImpl.getInstance();
		pricedataservice = PriceDataServiceImpl.getInstance();
		rentdataservice = RentDataServiceImpl.getInstance();
		salarydataservice = SalaryDataServiceImpl.getInstance();
		citydataservice = CityDataServiceImpl.getInstance();
		institutiondataservice = InstitutionDataServiceImpl.getInstance();
		billorderdataservice = BillOrderDataServiceImpl.getInstance();
		paymentdataService = PaymentDataServiceImpl.getInstance();
		inventorydataservice = InventoryDataServiceImpl.getInstance();
		stockinorderdataservice = StockinOrderDataServiceImpl.getInstance();
		stockoutorderdataservice = StockoutOrderDataServiceImpl.getInstance();
		transFareDataService = TransFareDataImpl.getInstance();
		initialDataService = InitialDataServiceImpl.getInstance();

		contactor = new ContactorImpl();

		serviceMap = new HashMap<>(ServiceName.serviceNumber + 10);

		serviceMap.put(ServiceName.ACCOUNT_DATA_SERVICE, accountDataService);
		serviceMap.put(ServiceName.USER_DATA_SERVICE, userdataservice);
		serviceMap.put(ServiceName.LOG_DATA_SERVICE, logdataservice);
		serviceMap.put(ServiceName.CAR_DATA_SERVICE, cardataservice);
		serviceMap.put(ServiceName.DRIVER_DATA_SERVICE, driverdataservice);
		serviceMap.put(ServiceName.ARRIVEDORDER_DATA_SERVICE,
				arrivedOrderdataservice);
		serviceMap.put(ServiceName.EXPRESSORDER_DATA_SERVICE,
				expressorderdataservice);
		serviceMap.put(ServiceName.DELIVERYPORDER_DATA_SERVICE,
				deliveryorderdataservice);
		serviceMap
				.put(ServiceName.LOADORDER_DATA_SERVICE, loadorderdataservice);
		serviceMap
				.put(ServiceName.BILLORDER_DATA_SERVICE, billorderdataservice);
		serviceMap.put(ServiceName.PRICE_DATA_SERVICE, pricedataservice);
		serviceMap.put(ServiceName.RENT_DATA_SERVICE, rentdataservice);
		serviceMap.put(ServiceName.SALARY_DATA_SERVICE, salarydataservice);
		serviceMap.put(ServiceName.CITY_DATA_SERVICE, citydataservice);
		serviceMap.put(ServiceName.INSTITUTION_DATA_SERVICE,
				institutiondataservice);
		serviceMap.put(ServiceName.PAYMENT_DATA_SERVICE, paymentdataService);
		serviceMap
				.put(ServiceName.INVENTORY_DATA_SERVICE, inventorydataservice);
		serviceMap.put(ServiceName.STOCKINORDER_DATA_SERVICE,
				stockinorderdataservice);
		serviceMap.put(ServiceName.STOCKOUTORDER_DATA_SERVICE,
				stockoutorderdataservice);
		serviceMap.put(ServiceName.TRANSFARE_DATA_SERVICE,
				NetModule.transFareDataService);
		serviceMap.put(ServiceName.INITIAL_DATA_SERVICE, initialDataService);
	}

	public static void main(String[] args) {
		try {
			initial();
			LocateRegistry.createRegistry(RMIConfig.RMI_port);

			for (Map.Entry<String, Object> entry : serviceMap.entrySet()) {
				Naming.bind(RMIConfig.url + entry.getKey(),
						(UnicastRemoteObject) entry.getValue());
			}
			Naming.bind(RMIConfig.url + ServiceName.CONTACTOR, contactor);
			
			serverMainFrame = new ServerMainFrame();
			System.out.println("Service started");
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (AlreadyBoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

	}
}
