package gap.client.ui.expressorderquery.components;

import gap.client.ui.expressorderquery.ResultShowPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class IdInputCompo extends JPanel {
    int defautWidth = 650, defantHeight = 70;
    int borderWidth = 3;
    JTextField id_input;
    public SubmitButton submit_bu;
    JLabel icon;
    String iconPath = "images\\expressorderquery\\search.png";
    JPanel jp;

    String tip;

    public IdInputCompo(JPanel jp, String tipMess) {
        setLayout(null);
        this.tip = tipMess;
        this.jp = jp;
        setBackground(Colors.blue);

        icon = new JLabel(new ImageIcon(iconPath));
        icon.setSize(60, 60);
        icon.setLocation(10, 5);

        id_input = new JTextField(20);
        id_input.setBackground(Color.white);
        id_input.setFont(new Font("微软雅黑", Font.BOLD, 34));
        id_input.setBorder(BorderFactory.createEmptyBorder());
        id_input.setForeground(Colors.drakblue);
        id_input.setLocation(90, 10);
        id_input.setSize(260, 50);
        id_input.setText(tip);

        id_input.addFocusListener(new FocusListener() {

            @Override
            public void focusLost(FocusEvent e) {
                // TODO 自动生成的方法存根
                if (id_input.getText().length() == 0)
                    id_input.setText(tip);
            }

            @Override
            public void focusGained(FocusEvent e) {
                // TODO 自动生成的方法存根
                if (id_input.getText().equals(tip))
                    id_input.setText("");
            }
        });

        submit_bu = new SubmitButton();
        submit_bu.setBounds(370, 0, 150, 70);

        add(icon);
        add(id_input);
        add(submit_bu);
    }

    public void addKeyListener(KeyListener key) {
        id_input.addKeyListener(key);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Colors.blue);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.white);
        g.fillRect(borderWidth, borderWidth, getWidth() - 2 * borderWidth,
                getHeight() - 2 * borderWidth);
    }

    public String getOrderId() {
        String str = id_input.getText();
        if (!str.matches("\\d{10}")) {
            ResultShowPanel.showMessage("订单号格式错误，请输入10位数字");
            jp.repaint();
            return null;
        }
        return str;
    }

    public class SubmitButton extends JLabel {
        public SubmitButton() {
            super("search");
            setOpaque(true);
            setFont(new Font("微软雅黑", Font.BOLD, 25));
            setForeground(Color.white);
            setBackground(Colors.blue);
            setHorizontalAlignment(JLabel.CENTER);
            addMouseListener(new MouseListener() {

                @Override
                public void mouseReleased(MouseEvent e) {
                    // TODO 自动生成的方法存根
                    setBackground(Colors.drakblue);
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    // TODO 自动生成的方法存根
                    setBackground(Colors.blue);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    // TODO 自动生成的方法存根
                    setBackground(Colors.blue);
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    // TODO 自动生成的方法存根
                    setBackground(Colors.drakblue);
                }

                @Override
                public void mouseClicked(MouseEvent e) {
                    // TODO 自动生成的方法存根

                }
            });
        }

    }
}
