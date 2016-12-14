package gap.client.ui.bussinessui.loadorder;

import gap.client.blcontroller.ExpressorderController;
import gap.client.blcontroller.InstitutionController;
import gap.client.ui.UITools.Default;
import gap.client.ui.UITools.RenderSetter;
import gap.client.ui.UITools.SwingConsole;
import gap.client.ui.gapcomponents.ComponentStyle;
import gap.client.ui.gapcomponents.GAPComboBox;
import gap.client.ui.gapcomponents.GAPLabel;
import gap.client.ui.gapcomponents.GAPTextField;
import gap.client.vo.InstitutionVO;
import gap.common.po.AllAddressPO;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * 目的地地址Panel
 * @author YangYanfei
 *
 */
public class DesAddressPanel extends JPanel {
	JLabel target_add, province, city, comment;
	JComboBox<String> pro_list, city_list;
	JComboBox<InstitutionVO> ins_name;
	JTextField comment_text;
	AllAddressPO allAddress;

	public DesAddressPanel() {
		setOpaque(false);
		setPreferredSize(new Dimension(Default.PANEL_WIDTH, 100));
		target_add = new GAPLabel("目的地：");
		province = new GAPLabel("省");
		city = new GAPLabel("市");
		comment = new GAPLabel("备注：");
		comment_text = new GAPTextField(30);

		initialList();

		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints gcons = new GridBagConstraints();
		setLayout(gb);

		gcons.insets = new Insets(5, 20, 5, 30);
		SwingConsole
				.addComponent(gb, gcons, this, target_add, 0, 0, 1, 1, 0, 0);
		gcons.insets = new Insets(5, 10, 5, 10);
		SwingConsole.addComponent(gb, gcons, this, pro_list, 1, 0, 1, 1, 0, 0);
		SwingConsole.addComponent(gb, gcons, this, province, 2, 0, 1, 1, 0, 0);
		SwingConsole.addComponent(gb, gcons, this, city_list, 3, 0, 1, 1, 0, 0);
		SwingConsole.addComponent(gb, gcons, this, city, 4, 0, 1, 1, 0, 0);
		SwingConsole.addComponent(gb, gcons, this, ins_name, 5, 0, 1, 1, 0, 0);
		gcons.insets = new Insets(5, 20, 5, 30);
		SwingConsole.addComponent(gb, gcons, this, comment, 0, 1, 1, 1, 0, 0);
		gcons.insets = new Insets(5, 10, 5, 10);
		SwingConsole.addComponent(gb, gcons, this, comment_text, 1, 1, 5, 1, 0,
				0);
	}

	public String getTargetIns_id() {
		return ((InstitutionVO) ins_name.getSelectedItem()).getInsId();
	}

	public String getComment() {
		return comment_text.getText();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = RenderSetter.OpenRender(g);
		g2d.setColor(ComponentStyle.light_gray);
		int width = getWidth();
		g2d.drawLine(10, 5, width - 20, 5);
	}

	void initialList() {
		pro_list = new GAPComboBox<String>();
		city_list = new GAPComboBox<String>();
		ins_name = new GAPComboBox<>();

		allAddress = ExpressorderController.getAllAddress();
		for (String str : allAddress.getProvinces()) {
			pro_list.addItem(str);
		}
		pro_list.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO 自动生成的方法存根
				String province = (String) e.getItem();
				setCity(province);
			}
		});
		city_list.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO 自动生成的方法存根
				String city = (String) e.getItem();
				setIns(city);
			}
		});

		setCity((String) pro_list.getSelectedItem());

		setIns((String) city_list.getSelectedItem());
	}

	void setCity(String province) {
		city_list.removeAllItems();
		for (String str : allAddress.getProvince2city().get(province))
			city_list.addItem(str);
	}

	void setIns(String city) {
		ins_name.removeAllItems();
		for (InstitutionVO ins : InstitutionController.findByCity(city)) {
			ins_name.addItem(ins);
		}
	}
}
