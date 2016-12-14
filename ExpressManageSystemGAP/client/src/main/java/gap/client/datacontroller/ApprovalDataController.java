package gap.client.datacontroller;

/**
 * @author seven
 */
import static gap.client.datacontroller.NetModule.arrivedOrderdataservice;
import static gap.client.datacontroller.NetModule.billorderdataservice;
import static gap.client.datacontroller.NetModule.deliveryorderdataservice;
import static gap.client.datacontroller.NetModule.expressorderdataservice;
import static gap.client.datacontroller.NetModule.loadorderdataservice;
import static gap.client.datacontroller.NetModule.paymentdataService;
import static gap.client.datacontroller.NetModule.stockinorderdataservice;
import static gap.client.datacontroller.NetModule.stockoutorderdataservice;
import gap.client.blcontroller.AccountorReceiptController;
import gap.client.datacontroller.ApprovalDataStrategy.AppStrategy;
import gap.common.ListInterface.Order;
import gap.common.po.ArrivedOrderPO;
import gap.common.po.BillOrderPO;
import gap.common.po.DeliveryOrderPO;
import gap.common.po.ExpressOrderPO;
import gap.common.po.LoadOrderPO;
import gap.common.po.PaymentListPO;
import gap.common.po.StockinOrderPO;
import gap.common.po.StockoutOrderPO;
import gap.common.util.ResultMessage;

import java.rmi.RemoteException;
import java.util.List;

public class ApprovalDataController {

	AccountorReceiptController accountorReceiptController;
	
	protected ApprovalDataController() {
		accountorReceiptController = AccountorReceiptController.getInstance();
	}

	public List<ExpressOrderPO> getUnpassedExpressOrder() {
		try {
			return expressorderdataservice.getUnpassedOrder();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<ArrivedOrderPO> getUnpassedArrivedOrder() {
		try {
			return arrivedOrderdataservice.getUnpassedOrders();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<BillOrderPO> getUnpassedBillOrder() {
		try {
			return billorderdataservice.getUnpassedOrders();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<DeliveryOrderPO> getUnpassedDeliveryOrder() {
		try {
			return deliveryorderdataservice.getUnpassedOrders();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<LoadOrderPO> getUnpassedLoadOrder() {
		try {
			return loadorderdataservice.getUnpassedOrders();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<StockinOrderPO> getUnpassedStockinOrder() {
		try {
			return stockinorderdataservice.getUnpassedOrders();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<StockoutOrderPO> getUnpassedStockoutOrder() {
		try {
			return stockoutorderdataservice.getUnpassedOrders();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<PaymentListPO> getUnpassedPaymentListOrder(){
		try {
			return paymentdataService.getNotPassedPayment();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public ResultMessage setPassed(List<Object> orders) {
//		ResultMessage rm;
//		for (Object order : orders) {
//			if (order instanceof ExpressOrderPO) {
//				String targetInsId = ((ExpressOrderPO) order).getCurrentins_id();
//				try {
//					String insname = institutiondataservice.findById(targetInsId).getInsName();
//					String state = insname + "已收件";
//					rm = expressorderdataservice.setPassed(((ExpressOrderPO) order).getID(), state);
//				} catch (RemoteException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//			} else if (order instanceof ArrivedOrderPO) {
//				String targetInsId = ((ArrivedOrderPO) order).getDes_ins_id();
//				try {
//					String insname = institutiondataservice.findById(targetInsId).getInsName();
//					String state = insname + "已收件";
//					rm = arrivedOrderdataservice.setPassed(((ArrivedOrderPO) order).getID(), state);
//				} catch (RemoteException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			} else if (order instanceof BillOrderPO) {
//				
//				BillOrderPO aBillOrderPO = (BillOrderPO)order;
//				rm = accountorReceiptController.handleBillOrder(aBillOrderPO);
//				
//			} else if (order instanceof DeliveryOrderPO) {
//				try {
//					rm = deliveryorderdataservice.setPassed(((DeliveryOrderPO) order).getID(), "");
//				} catch (RemoteException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			} else if (order instanceof LoadOrderPO) {
//				String targetInsId = ((LoadOrderPO) order).getTargetins_id();
//				try {
//					String insname = institutiondataservice.findById(targetInsId).getInsName();
//					String state = "正在发往" + insname;
//					rm = loadorderdataservice.setPassed(((LoadOrderPO) order).getID(), state);
//				} catch (RemoteException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			} else if (order instanceof StockinOrderPO) {
//				try {
//					rm = stockinorderdataservice.setPassed(((StockinOrderPO) order).getID(), "");
//				} catch (RemoteException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			} else if (order instanceof StockoutOrderPO) {
//				try {
//					rm = stockoutorderdataservice.setPassed(((StockoutOrderPO) order).getID(), "");
//				} catch (RemoteException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}else if(order instanceof PaymentListPO){
//				
//				PaymentListPO paymentListPO = (PaymentListPO)order;
//				rm = accountorReceiptController.handlePaymentList(paymentListPO);
//				
//			}
//		}
//		return ResultMessage.FAILED;
		AppDataStrategyFactory factory = AppDataStrategyFactory.getInstance();
		for (Object obj : orders) {
			Order order = (Order)obj;
			AppStrategy strategy = factory.getStrategy(order);
			ResultMessage rm = strategy.setPassed(order);
			if(rm != ResultMessage.SUCCEED){
				System.out.println(strategy.getClass().getName()+"处理"+order.getID()+"出错");
				System.out.println(rm);
			}
		}
		 return ResultMessage.SUCCEED;
	}
}
