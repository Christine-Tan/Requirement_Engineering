package gap.client.ui.deliveryui.expressorderinput;

import gap.client.blcontroller.ExpressorderController;
import gap.client.ui.BaseComponents.MainFrame;
import gap.client.ui.UITools.Default;
import gap.client.ui.UITools.RenderSetter;
import gap.client.ui.UITools.SwingConsole;
import gap.client.ui.gapcomponents.ComponentStyle;
import gap.client.ui.gapcomponents.GAPComboBox;
import gap.client.ui.gapcomponents.GAPLabel;
import gap.client.ui.gapcomponents.GAPTextField;
import gap.client.util.MessageType;
import gap.common.po.AllAddressPO;
import gap.common.util.Address;
import gap.common.util.PeopleInfo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;
import javax.swing.JPanel;

/**
 * 收件人、寄件人信息
 * @author YangYanfei
 *
 */
public class PeopleInfoUI extends JPanel {
    GAPLabel title, name, phone, address, province, city, district;
    GAPTextField name_text, phone_text, depart_text;
    JComboBox<String> pro_list, city_list, dis_list;
    GridBagLayout grid;
    GridBagConstraints gcon;
    AllAddressPO allAddress;

    public PeopleInfoUI(String titleName) {

        setBackground(Color.white);
        setPreferredSize(new Dimension(Default.PANEL_WIDTH, 150));

        // 初始化组件
        title = new GAPLabel(titleName);
        title.setFont(ComponentStyle.defaultFont);
        name = new GAPLabel("姓名");
        name.setFont(ComponentStyle.defaultFont);
        name_text = new GAPTextField();

        phone = new GAPLabel("电话");
        phone.setFont(ComponentStyle.defaultFont);
        phone_text = new GAPTextField(15);
        // phone_text.setControl("\\D" + "", 11, 11);

        address = new GAPLabel("住址");

        initialList();

        depart_text = new GAPTextField();

        // 布局
        grid = new GridBagLayout();
        gcon = new GridBagConstraints();
        gcon.insets = new Insets(10, 10, 10, 10);
        gcon.anchor = GridBagConstraints.CENTER;
        setLayout(grid);
        SwingConsole.addComponent(grid, gcon, this, title, 0, 0, 1, 1, 0, 0);
        gcon.fill = GridBagConstraints.HORIZONTAL;
        SwingConsole.addComponent(grid, gcon, this, name, 1, 0, 1, 1, 0, 0);
        SwingConsole
                .addComponent(grid, gcon, this, name_text, 2, 0, 3, 1, 0, 0);
        SwingConsole.addComponent(grid, gcon, this, phone, 8, 0, 1, 1, 0, 0);
        SwingConsole.addComponent(grid, gcon, this, phone_text, 9, 0, 1, 1, 0,
                0);
        SwingConsole.addComponent(grid, gcon, this, address, 1, 1, 1, 1, 0, 0);
        SwingConsole.addComponent(grid, gcon, this, pro_list, 2, 1, 1, 1, 0, 0);
        SwingConsole.addComponent(grid, gcon, this, province, 3, 1, 1, 1, 0, 0);
        SwingConsole
                .addComponent(grid, gcon, this, city_list, 4, 1, 1, 1, 0, 0);
        SwingConsole.addComponent(grid, gcon, this, city, 5, 1, 1, 1, 0, 0);
        SwingConsole.addComponent(grid, gcon, this, dis_list, 6, 1, 1, 1, 0, 0);
        SwingConsole.addComponent(grid, gcon, this, district, 7, 1, 1, 1, 0, 0);
        SwingConsole.addComponent(grid, gcon, this, depart_text, 8, 1, 2, 1, 0,
                0);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = RenderSetter.OpenRender(g);
        g2d.setColor(ComponentStyle.light_gray);
        int width = getWidth(), height = getHeight();
        g2d.drawLine(10, height - 5, width - 20, height - 5);
    }

    /**
     * 初始化下拉列表
     */
    private void initialList() {
        allAddress = ExpressorderController.getAllAddress();

        province = new GAPLabel("省");
        pro_list = new GAPComboBox<String>();

        city = new GAPLabel("市");
        city_list = new GAPComboBox<String>();

        district = new GAPLabel("区");
        dis_list = new GAPComboBox<String>();
        dis_list.setPreferredSize(new Dimension(85, 22));
        // dis_list.setEditable(true);

        for (String str : allAddress.getProvinces())
            pro_list.addItem(str);
        pro_list.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                // TODO 自动生成的方法存根
                String item = (String) e.getItem();
                setCity(item);
            }
        });

        city_list.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                // TODO 自动生成的方法存根
                String city = (String) e.getItem();
                setDistrict(city);
            }
        });
        setCity(pro_list.getItemAt(0));

        setDistrict(city_list.getItemAt(0));

    }

    private void setCity(String province) {
        city_list.removeAllItems();
        for (String str : allAddress.getProvince2city().get(province)) {
            city_list.addItem(str);
        }
    }

    private void setDistrict(String city) {
        dis_list.removeAllItems();
        for (String str : allAddress.getCity2district().get(city)) {
            dis_list.addItem(str);
        }
    }

    public PeopleInfo getInfo() {
        String name = name_text.getText(), depart = depart_text.getText(), phone = phone_text
                .getText();
        if (name.length() == 0) {
            MainFrame.setMessage("请输入姓名", MessageType.alram, 2000);
            name_text.alarm();
            return null;
        }

        if (phone.length() == 0 || !phone.matches("\\d*")) {
            MainFrame.setMessage("请输入电话", MessageType.alram, 2000);
            phone_text.alarm();
            return null;
        }

        if (depart.length() == 0) {
            MainFrame.setMessage("请输入单位信息", MessageType.alram, 2000);
            depart_text.alarm();
            return null;
        }

        return new PeopleInfo(name, getAddress(), depart, phone);
    }

    public Address getAddress() {
        return new Address((String) pro_list.getSelectedItem(),
                (String) city_list.getSelectedItem(),
                (String) dis_list.getSelectedItem());
    }

    public void reSet() {
        name_text.setText("");
        phone_text.setText("");
        depart_text.setText("");
    }

}
