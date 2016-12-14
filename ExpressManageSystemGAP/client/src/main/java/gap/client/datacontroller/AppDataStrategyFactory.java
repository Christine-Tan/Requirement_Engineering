package gap.client.datacontroller;


import static gap.client.datacontroller.NetModule.arrivedOrderdataservice;
import static gap.client.datacontroller.NetModule.billorderdataservice;
import static gap.client.datacontroller.NetModule.deliveryorderdataservice;
import static gap.client.datacontroller.NetModule.expressorderdataservice;
import static gap.client.datacontroller.NetModule.institutiondataservice;
import static gap.client.datacontroller.NetModule.loadorderdataservice;
import static gap.client.datacontroller.NetModule.paymentdataService;
import static gap.client.datacontroller.NetModule.stockinorderdataservice;
import static gap.client.datacontroller.NetModule.stockoutorderdataservice;
import gap.client.datacontroller.ApprovalDataStrategy.AppStrategy;
import gap.client.datacontroller.ApprovalDataStrategy.ArriveDataStrategy;
import gap.client.datacontroller.ApprovalDataStrategy.BillDataStrategy;
import gap.client.datacontroller.ApprovalDataStrategy.DeliveryDataStrategy;
import gap.client.datacontroller.ApprovalDataStrategy.ExpressDataStrategy;
import gap.client.datacontroller.ApprovalDataStrategy.LoadDataStrategy;
import gap.client.datacontroller.ApprovalDataStrategy.PaymentDataStrategy;
import gap.client.datacontroller.ApprovalDataStrategy.StockInDataStrategy;
import gap.client.datacontroller.ApprovalDataStrategy.StockOutDataStrategy;
import gap.common.dataservice.expressorderdataservice.ExpressOrderDataService;
import gap.common.dataservice.managedataservice.InstitutionDataService;
import gap.common.dataservice.orderdataservice.ArrivedOrderDataService;
import gap.common.dataservice.orderdataservice.DeliveryOrderDataService;
import gap.common.dataservice.orderdataservice.LoadOrderDataService;
import gap.common.dataservice.orderdataservice.StockinOrderDataService;
import gap.common.dataservice.orderdataservice.StockoutOrderDataService;
import gap.common.dataservice.receiptdataservice.BillOrderDataService;
import gap.common.dataservice.receiptdataservice.PaymentdataService;
import gap.common.po.ArrivedOrderPO;
import gap.common.po.BillOrderPO;
import gap.common.po.DeliveryOrderPO;
import gap.common.po.ExpressOrderPO;
import gap.common.po.LoadOrderPO;
import gap.common.po.PaymentListPO;
import gap.common.po.StockinOrderPO;
import gap.common.po.StockoutOrderPO;

import java.util.HashMap;

public class AppDataStrategyFactory {

	HashMap<Class, AppStrategy> strategyMap;
	
	private static AppDataStrategyFactory factory;
	
	protected static AppDataStrategyFactory getInstance(){
		if(factory==null){
			factory = new AppDataStrategyFactory();
		}
		return factory;
	}
	
	private AppDataStrategyFactory(){
		strategyMap = new HashMap<>();
		
		strategyMap.put(ArrivedOrderPO.class, 	new ArriveDataStrategy(this));
		strategyMap.put(BillOrderPO.class, 		new BillDataStrategy(this));
		strategyMap.put(DeliveryOrderPO.class, 	new DeliveryDataStrategy(this));
		strategyMap.put(ExpressOrderPO.class, 	new ExpressDataStrategy(this));
		strategyMap.put(LoadOrderPO.class, 		new LoadDataStrategy(this));
		strategyMap.put(PaymentListPO.class, 	new PaymentDataStrategy(this));
		strategyMap.put(StockinOrderPO.class, 	new StockInDataStrategy(this));
		strategyMap.put(StockoutOrderPO.class, 	new StockOutDataStrategy(this));
	}
	
	
	/**
	 * 获得处理策略
	 * @param object
	 * @return
	 */
	public AppStrategy getStrategy(Object object){
		Class aClass = object.getClass();
		return strategyMap.get(aClass);
	}
	
	public ArrivedOrderDataService getArriveData(){
		return arrivedOrderdataservice;
	}

	public BillOrderDataService getBillData(){
		return billorderdataservice;
	}
	
	public DeliveryOrderDataService getDeliveryData(){
		return deliveryorderdataservice;
	}
	
	public ExpressOrderDataService getExpressData(){
		return expressorderdataservice;
	}
	
	public InstitutionDataService getInstitutionData(){
		return institutiondataservice;
	}
	
	public LoadOrderDataService getLoadDataService(){
		return loadorderdataservice;
	}
	
	public StockinOrderDataService getStockinData(){
		return stockinorderdataservice;
	}
	
	public PaymentdataService getPaymentData(){
		return paymentdataService;
	}
	
	public StockoutOrderDataService getStockOutData(){
		return stockoutorderdataservice;
	}
	
}
