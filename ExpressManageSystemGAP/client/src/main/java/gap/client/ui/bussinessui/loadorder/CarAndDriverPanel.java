package gap.client.ui.bussinessui.loadorder;

import gap.client.blcontroller.CarManageController;
import gap.client.blcontroller.DriverManageController;
import gap.client.ui.UITools.Default;
import gap.client.ui.UITools.RenderSetter;
import gap.client.ui.UITools.SwingConsole;
import gap.client.ui.gapcomponents.ComponentStyle;
import gap.client.ui.gapcomponents.GAPComboBox;
import gap.client.ui.gapcomponents.GAPLabel;
import gap.client.vo.CarVO;
import gap.client.vo.DriverVO;
import gap.common.util.Gender;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CarAndDriverPanel extends JPanel {
	DriverPanel driver;
	CarPanel car;

	public CarAndDriverPanel() {
		// TODO 自动生成的构造函数存根
		setOpaque(false);
		setPreferredSize(new Dimension(Default.PANEL_WIDTH, 120));

		BoxLayout box = new BoxLayout(this, BoxLayout.X_AXIS);
		setLayout(box);
		driver = new DriverPanel();
		car = new CarPanel();
		add(driver);
		add(Box.createHorizontalGlue());
		add(car);

	}

	public void refresh() {
		List<DriverVO> drivers = DriverManageController.getAll();
		List<CarVO> cars = CarManageController.getAll();
		driver.refresh(drivers);
		car.refresh(cars);
	}

	public DriverVO getDriver() {
		return driver.current_driver;
	}

	public CarVO getCar() {
		return car.current_car;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = RenderSetter.OpenRender(g);
		g2d.setColor(ComponentStyle.light_gray);
		int width = getWidth(), height = getHeight();
		g2d.drawLine(10, height - 5, width - 20, height - 5);
	}

	class DriverPanel extends JPanel {
		DriverVO current_driver;
		JComboBox<DriverVO> drivers_list;
		JLabel title, name, gender, license_due, driver_id, name_text,
				gender_text, license_due_text;

		public DriverPanel() {
			super();
			setOpaque(false);
			title = new GAPLabel("司机");
			driver_id = new GAPLabel("司机编号：");
			name = new GAPLabel("姓名：");
			gender = new GAPLabel("性别：");
			license_due = new GAPLabel("行驶证期限：");
			name_text = new GAPLabel();
			gender_text = new GAPLabel();
			license_due_text = new GAPLabel();
			drivers_list = new GAPComboBox<>();

			GridBagLayout gb = new GridBagLayout();
			GridBagConstraints gcons = new GridBagConstraints();
			setLayout(gb);
			gcons.insets = new Insets(5, 10, 0, 10);
			SwingConsole.addComponent(gb, gcons, this, title, 0, 0, 1, 4, 0, 0);
			SwingConsole.addComponent(gb, gcons, this, driver_id, 1, 0, 1, 1,
					0, 0);
			SwingConsole.addComponent(gb, gcons, this, drivers_list, 2, 0, 1,
					1, 0, 0);
			SwingConsole.addComponent(gb, gcons, this, name, 1, 1, 1, 1, 0, 0);
			SwingConsole.addComponent(gb, gcons, this, name_text, 2, 1, 1, 1,
					0, 0);
			SwingConsole
					.addComponent(gb, gcons, this, gender, 1, 2, 1, 1, 0, 0);
			SwingConsole.addComponent(gb, gcons, this, gender_text, 2, 2, 1, 1,
					0, 0);
			SwingConsole.addComponent(gb, gcons, this, license_due, 1, 3, 1, 1,
					0, 0);
			SwingConsole.addComponent(gb, gcons, this, license_due_text, 2, 3,
					1, 1, 0, 0);
		}

		public void refresh(List<DriverVO> list) {
			// drivers_list.setFocusable(false);
			drivers_list.removeAllItems();
			for (DriverVO driver : list) {
				drivers_list.addItem(driver);
			}
			if (drivers_list.getItemCount() != 0)
				setDriver((DriverVO) drivers_list.getSelectedItem());
			drivers_list.addItemListener(new ItemListener() {

				@Override
				public void itemStateChanged(ItemEvent e) {
					// TODO 自动生成的方法存根
					setDriver((DriverVO) e.getItem());
				}
			});
			// drivers_list.setFocusable(true);
		}

		public void setDriver(DriverVO driver) {
			this.current_driver = driver;
			name_text.setText(driver.getName());
			gender_text.setText(Gender.toChinese(driver.getGender()));
			license_due_text.setText(driver.getDriving_license_due());
			validate();
		}

	}

	class CarPanel extends JPanel {
		CarVO current_car;
		JLabel title, car_id, car_num, server_time, car_num_text,
				server_time_text;
		List<CarVO> cars;
		JComboBox<CarVO> cars_list;

		public CarPanel() {
			super();
			setOpaque(false);
			setPreferredSize(new Dimension(300, 100));
			title = new GAPLabel("车辆");
			car_id = new GAPLabel("车辆编号：");
			car_num = new GAPLabel("车牌号：");
			server_time = new GAPLabel("服役时间（年）：");
			car_num_text = new GAPLabel();
			server_time_text = new GAPLabel();
			cars_list = new GAPComboBox<CarVO>();

			GridBagLayout gb = new GridBagLayout();
			GridBagConstraints gcons = new GridBagConstraints();
			setLayout(gb);
			gcons.insets = new Insets(0, 5, 0, 5);
			SwingConsole.addComponent(gb, gcons, this, title, 0, 0, 1, 3, 0, 0);

			SwingConsole
					.addComponent(gb, gcons, this, car_id, 1, 0, 1, 1, 0, 0);
			SwingConsole.addComponent(gb, gcons, this, cars_list, 2, 0, 1, 1,
					0, 0);
			gcons.insets = new Insets(17, 5, 17, 5);
			SwingConsole.addComponent(gb, gcons, this, car_num, 1, 1, 1, 1, 0,
					0);
			SwingConsole.addComponent(gb, gcons, this, car_num_text, 2, 1, 1,
					1, 0, 0);
			gcons.insets = new Insets(0, 5, 0, 5);
			SwingConsole.addComponent(gb, gcons, this, server_time, 1, 2, 1, 1,
					0, 0);
			SwingConsole.addComponent(gb, gcons, this, server_time_text, 2, 2,
					1, 1, 0, 0);
		}

		public void refresh(List<CarVO> cars) {
			// cars_list.setFocusable(false);
			cars_list.removeAllItems();
			for (CarVO car : cars) {
				cars_list.addItem(car);
			}
			if (cars_list.getItemCount() != 0)
				setCar((CarVO) cars_list.getSelectedItem());
			cars_list.addItemListener(new ItemListener() {

				@Override
				public void itemStateChanged(ItemEvent e) {
					// TODO 自动生成的方法存根
					setCar((CarVO) e.getItem());
				}
			});
			// cars_list.setFocusable(true);
		}

		void setCar(CarVO car) {
			current_car = car;
			car_num_text.setText(car.getCar_num());
			server_time_text.setText("" + car.getServe_time());
			validate();
		}

	}
}
