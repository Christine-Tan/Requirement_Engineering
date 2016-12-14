package gap.client.bl.approval;

import gap.client.blcontroller.LogController;
import gap.client.blservice.approvalblservice.ApprovalService;
import gap.client.datacontroller.ApprovalDataController;
import gap.client.datacontroller.ControllerFactory;
import gap.client.util.LocalInfo;
import gap.common.po.ArrivedOrderPO;
import gap.common.po.BillOrderPO;
import gap.common.po.DeliveryOrderPO;
import gap.common.po.ExpressOrderPO;
import gap.common.po.LoadOrderPO;
import gap.common.po.LogPO;
import gap.common.po.PaymentListPO;
import gap.common.po.StockinOrderPO;
import gap.common.po.StockoutOrderPO;
import gap.common.util.ResultMessage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Approval implements ApprovalService {
    ApprovalDataController controller;
    List<Object> orders;

    /**
     *
     */
    public Approval() {
        controller = ControllerFactory.getApprovalDataController();
    }

    @Override
    public List<Object> getUnpassedOrder() {
        // TODO Auto-generated method stub
        orders = new ArrayList<Object>();
        orders.addAll(controller.getUnpassedArrivedOrder());
        orders.addAll(controller.getUnpassedBillOrder());
        orders.addAll(controller.getUnpassedDeliveryOrder());
        orders.addAll(controller.getUnpassedExpressOrder());
        orders.addAll(controller.getUnpassedLoadOrder());
        orders.addAll(controller.getUnpassedStockinOrder());
        orders.addAll(controller.getUnpassedStockoutOrder());
        orders.addAll(controller.getUnpassedPaymentListOrder());
        return orders;
    }

    @Override
    public ResultMessage approve(List<Object> orders) {
        // TODO Auto-generated method stub
        controller.setPassed(orders);
        this.createLog();
        return ResultMessage.SUCCEED;
    }

    /**
     *
     */
    public void createLog() {
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = format.format(date);
        for (Object order : orders) {
            LogPO log;
            if (order instanceof ArrivedOrderPO) {
                ArrivedOrderPO arrivedOrder = (ArrivedOrderPO) order;
                log = new LogPO(LocalInfo.localuser.toUserPO(), time, "到达单"
                        + arrivedOrder.getID() + "审批通过");
                LogController.addLog(log);
            } else if (order instanceof BillOrderPO) {
                BillOrderPO billOrder = (BillOrderPO) order;
                log = new LogPO(LocalInfo.localuser.toUserPO(), time, "付款单"
                        + billOrder.getID() + "审批通过");
                LogController.addLog(log);
            } else if (order instanceof DeliveryOrderPO) {
                DeliveryOrderPO deliveryOrder = (DeliveryOrderPO) order;
                log = new LogPO(LocalInfo.localuser.toUserPO(), time, "派件单"
                        + deliveryOrder.getID() + "审批通过");
                LogController.addLog(log);
            } else if (order instanceof ExpressOrderPO) {
                ExpressOrderPO expressOrder = (ExpressOrderPO) order;
                log = new LogPO(LocalInfo.localuser.toUserPO(), time, "寄件单"
                        + expressOrder.getID() + "审批通过");
                LogController.addLog(log);
            } else if (order instanceof LoadOrderPO) {
                LoadOrderPO loadOrder = (LoadOrderPO) order;
                log = new LogPO(LocalInfo.localuser.toUserPO(), time, "装车单"
                        + loadOrder.getID() + "审批通过");
                LogController.addLog(log);
            } else if (order instanceof StockinOrderPO) {
                StockinOrderPO stockinOrder = (StockinOrderPO) order;
                log = new LogPO(LocalInfo.localuser.toUserPO(), time, "入库单"
                        + stockinOrder.getID() + "审批通过");
                LogController.addLog(log);
            } else if (order instanceof StockoutOrderPO) {
                StockoutOrderPO stockoutOrder = (StockoutOrderPO) order;
                log = new LogPO(LocalInfo.localuser.toUserPO(), time, "出库单"
                        + stockoutOrder.getID() + "审批通过");
                LogController.addLog(log);
            } else if (order instanceof PaymentListPO) {
                PaymentListPO paymentList = (PaymentListPO) order;
                log = new LogPO(LocalInfo.localuser.toUserPO(), time, "付款单"
                        + paymentList.getID() + "审批通过");
                LogController.addLog(log);
            } else {
                System.out.println("no corresponding ordertype");
            }
        }
    }
}
