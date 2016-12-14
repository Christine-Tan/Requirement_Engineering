package gap.client.datacontroller;

public class ControllerFactory {
	private static LoginDataController loginDataController;
	private static ExpressOrderDataController expressOrderDataController;
	private static LoadOrderDataController loadorderdatacontroller;
	private static ArrivedOrderDataController arrivedorderdatacontroller;
	private static DeliveryOrderDataController deliveryorderdatacontroller;
	private static InventoryDataController inventoryDataController;
	private static StockinOrderDataController stockinOrderDataController;
	private static StockoutOrderDataController stockoutOrderDataController;
	private static BillOrderDateController billorderdatacontroller;
	private static TransDataController transdatacontroller;
	private static AccountDateController accountDateController;
	private static InstitutionDataController institutionDataController;
	private static CityDataController cityDataController;
	private static PriceDataController priceDataController;
	private static RentDataController rentDataController;
	private static SalaryDataController salaryDataController;
	private static UserDataController userDataController;
	private static ApprovalDataController approvalDataController;
	private static WareHouseDataController wareHouseDataController;
	private static FlexSectorDataController flexSectorDataController;
	private static AccountorReceiptDataController receiptDataController;
	private static TableDataController tableDataController;
	private static LogDataController logDataController;
	private static InitialDataController initialDataController;
	
	public static InitialDataController getInitialDataController(){
		if(initialDataController == null){
			initialDataController = new InitialDataController();
		}
		return initialDataController;
	}

	public static LogDataController getLogDataController(){
		if(logDataController==null){
			logDataController=new LogDataController();
		}
		return logDataController;
	}
	
	public static AccountDateController getAccountDataController() {
		if (accountDateController == null) {
			accountDateController = new AccountDateController();
		}
		return accountDateController;
	}

	public static AccountorReceiptDataController getReceiptDataController() {
		if (receiptDataController == null) {
			receiptDataController = new AccountorReceiptDataController();
		}
		return receiptDataController;
	}

	public static LoginDataController getLoginDataController() {
		if (loginDataController == null)
			loginDataController = new LoginDataController();
		return loginDataController;
	}

	public static ExpressOrderDataController getExpressOrderDataController() {
		if (expressOrderDataController == null)
			expressOrderDataController = new ExpressOrderDataController();
		return expressOrderDataController;
	}

	public static TransDataController getTransDataController() {
		if (transdatacontroller == null)
			transdatacontroller = new TransDataController();
		return transdatacontroller;
	}

	public static InventoryDataController getInventoryDataController() {
		if (inventoryDataController == null)
			inventoryDataController = new InventoryDataController();
		return inventoryDataController;
	}

	public static LoadOrderDataController getLoadOrderDataController() {
		if (loadorderdatacontroller == null)
			loadorderdatacontroller = new LoadOrderDataController();
		return loadorderdatacontroller;
	}

	public static ArrivedOrderDataController getArrivedOrderDataController() {
		if (arrivedorderdatacontroller == null)
			arrivedorderdatacontroller = new ArrivedOrderDataController();
		return arrivedorderdatacontroller;
	}

	public static BillOrderDateController getBillOrderDateController() {
		if (billorderdatacontroller == null)
			billorderdatacontroller = new BillOrderDateController();
		return billorderdatacontroller;
	}

	public static DeliveryOrderDataController getDeliveryOrderDataController() {
		if (deliveryorderdatacontroller == null)
			deliveryorderdatacontroller = new DeliveryOrderDataController();
		return deliveryorderdatacontroller;
	}

	public static StockinOrderDataController getStockinOrderDataController() {
		if (stockinOrderDataController == null)
			stockinOrderDataController = new StockinOrderDataController();
		return stockinOrderDataController;
	}

	public static StockoutOrderDataController getStockoutOrderDataController() {
		if (stockoutOrderDataController == null)
			stockoutOrderDataController = new StockoutOrderDataController();
		return stockoutOrderDataController;
	}

	public static InstitutionDataController getInstitutionDataController() {
		if (institutionDataController == null)
			institutionDataController = new InstitutionDataController();
		return institutionDataController;
	}

	public static CityDataController getCityDataController() {
		if (cityDataController == null)
			cityDataController = new CityDataController();
		return cityDataController;
	}

	public static RentDataController getRentDataController() {
		if (rentDataController == null)
			rentDataController = new RentDataController();
		return rentDataController;
	}

	public static PriceDataController getPriceDataController() {
		if (priceDataController == null)
			priceDataController = new PriceDataController();
		return priceDataController;
	}

	public static SalaryDataController getSalaryDataController() {
		if (salaryDataController == null)
			salaryDataController = new SalaryDataController();
		return salaryDataController;
	}

	public static UserDataController getUserDataController() {
		if (userDataController == null)
			userDataController = new UserDataController();
		return userDataController;
	}

	public static ApprovalDataController getApprovalDataController() {
		if (approvalDataController == null)
			approvalDataController = new ApprovalDataController();
		return approvalDataController;
	}

	public static WareHouseDataController getWareHouseDataController() {
		if (wareHouseDataController == null)
			wareHouseDataController = new WareHouseDataController();
		return wareHouseDataController;
	}

	public static FlexSectorDataController getFlexSectorDataController() {
		if (flexSectorDataController == null)
			flexSectorDataController = new FlexSectorDataController();
		return flexSectorDataController;
	}

	public static TableDataController getTableDataController() {
		if (tableDataController == null) {
			tableDataController = new TableDataController();
		}
		return tableDataController;
	}
}
