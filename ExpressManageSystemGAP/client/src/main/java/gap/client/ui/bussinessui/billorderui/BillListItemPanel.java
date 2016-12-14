package gap.client.ui.bussinessui.billorderui;

import gap.client.blcontroller.BillOrderController;
import gap.client.ui.UITools.Default;
import gap.client.ui.UITools.SwingConsole;
import gap.client.ui.gapcomponents.GAPTextField;
import gap.client.vo.BillOrderVO;
import gap.client.vo.BillVO;
import gap.common.po.BillPO;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BillListItemPanel extends JPanel {
	List<ItemPanel> items;
	GridBagLayout gb;
	GridBagConstraints gcons;
	JFrame jf;

	public BillListItemPanel(JFrame jf) {
		this.jf = jf;
		setOpaque(false);
		items = new ArrayList<>();
		gb = new GridBagLayout();
		gcons = new GridBagConstraints();
		setLayout(gb);
		// BillVO billVO = new BillVO();
		// billVO.id = "000000005";
		// billVO.name = "yyf";
		// billVO.money = 50;
		// billVO.date = "2015-02-01";
		// addItem(billVO);
		reFresh();
	}

	public void reFresh() {
		clear();
		List<BillVO> billVO = BillOrderController.getBills(new Date(System
				.currentTimeMillis()).toString());
		for (BillVO bi : billVO) {
			addItem(bi);
		}
		reLayout();
	}

	public BillOrderVO getBillOrderVO() {
		BillOrderVO vo = new BillOrderVO();
		vo.bills = new ArrayList<BillPO>();
		for (ItemPanel item : items) {
			vo.bills.add(item.getBill());
		}
		vo.date = new Date(System.currentTimeMillis());
		return vo;
	}

	private void addItem(BillVO billVO) {
		items.add(new ItemPanel(billVO));
	}

	public void clear() {
		for (ItemPanel item : items) {
			remove(item);
		}
		items.clear();
	}

	private void reLayout() {
		for (int i = 0; i < items.size(); i++) {
			ItemPanel item = items.get(i);
			item.setId(String.valueOf(i + 1));
			SwingConsole.addComponent(gb, gcons, this, item, 0, i, 1, 1, 1, 0);
		}
		jf.validate();
	}

	class ItemPanel extends JPanel {
		BillVO billVO;
		GAPTextField id, delivery_id, name, money, date;

		public ItemPanel(BillVO billVO) {
			this.billVO = billVO;
			setPreferredSize(new Dimension(Default.PANEL_WIDTH, 50));
			setOpaque(false);
			id = new GAPTextField(3);
			delivery_id = new GAPTextField(billVO.id, 7);
			name = new GAPTextField(billVO.name, 5);
			money = new GAPTextField(String.valueOf(billVO.money), 5);
			date = new GAPTextField(billVO.date, 7);

			GridBagLayout gb = new GridBagLayout();
			GridBagConstraints gcons = new GridBagConstraints();
			setLayout(gb);

			gcons.insets = new Insets(10, 0, 10, 0);
			SwingConsole.addComponent(gb, gcons, this, id, 0, 0, 1, 1, 0, 0);
			gcons.insets = new Insets(10, 55, 10, 0);
			SwingConsole.addComponent(gb, gcons, this, delivery_id, 1, 0, 1, 1,
					0, 0);
			SwingConsole.addComponent(gb, gcons, this, name, 2, 0, 1, 1, 0, 0);
			SwingConsole.addComponent(gb, gcons, this, money, 3, 0, 1, 1, 0, 0);
			SwingConsole.addComponent(gb, gcons, this, date, 4, 0, 1, 1, 0, 0);

			closeEdit();
		}

		void closeEdit() {
			id.closeEdit();
			delivery_id.closeEdit();
			name.closeEdit();
			money.closeEdit();
			date.closeEdit();

			id.setHorizontalAlignment(JTextField.CENTER);
			delivery_id.setHorizontalAlignment(JTextField.CENTER);
			name.setHorizontalAlignment(JTextField.CENTER);
			money.setHorizontalAlignment(JTextField.CENTER);
			date.setHorizontalAlignment(JTextField.CENTER);
		}

		public BillPO getBill() {
			return new BillPO(billVO.money, billVO.id);
		}

		public void setId(String id) {
			this.id.setText(id);
		}
	}
}
