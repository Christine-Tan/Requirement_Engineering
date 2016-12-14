package gap.common.po;

import gap.common.ListInterface.Receipt;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

public class BillOrderPO implements Receipt {
	// 快递员id和所收运费
	private List<BillPO> bills;
	private double totalMoney;
	// 收款单id
	private String id;

	// 创建日期
	private Date billDate;

	public BillOrderPO() {

	}

	public BillOrderPO(List<BillPO> bills, String id, Date billDate) {
		super();
		this.bills = bills;
		this.id = id;
		this.billDate = billDate;
		totalMoney = 0;
		for (BillPO po : bills) {
			totalMoney += po.getMoney();
		}
	}

	public List<BillPO> getBills() {
		return bills;
	}

	public void setBills(List<BillPO> bills) {
		this.bills = bills;
	}

	public double getTotal() {
		return totalMoney;
	}

	public void setTotalMoney(double totalMoney) {
		this.totalMoney = totalMoney;
	}

	public String getID() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Calendar getDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(billDate);
		return calendar;
	}

	public Date getBillDate() {
		return billDate;
	}

	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}

}
