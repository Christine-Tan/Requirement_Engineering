package gap.client.ui.bussinessui.arrivedorder;

import gap.client.blcontroller.ArrivedOrderController;
import gap.client.ui.BaseComponents.MainFrame;
import gap.client.ui.BaseComponents.MainPanel;
import gap.client.ui.UITools.SwingConsole;
import gap.client.ui.gapcomponents.ButtonArea;
import gap.client.ui.gapcomponents.FlushButton;
import gap.client.util.MessageType;
import gap.client.vo.ArrivedOrderVO;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ArrivedOrderPanel extends MainPanel {
    JButton flushButton;
    TitlePanel titlePanel;
    ArrivedOrderListItemPanel listItem;
    CommentPanel comment;
    ButtonArea buttonArea;

    public ArrivedOrderPanel(MainFrame frame) {
        super(frame);
        flushButton = new FlushButton();
        titlePanel = new TitlePanel();
        listItem = new ArrivedOrderListItemPanel(frame);
        comment = new CommentPanel();
        buttonArea = new ButtonArea();
        buttonArea.submit.setText("生成到达单");
        buttonArea.submit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO 自动生成的方法存根
                mainFrame.load(new Runnable() {

                    @Override
                    public void run() {
                        // TODO 自动生成的方法存根
                        for (ArrivedOrderVO vo : listItem.getArrivedOrders()) {
                            vo.comment = comment.getComment();
                            // System.out.println(vo.id);
                            ArrivedOrderController.save(vo);
                            MainFrame.setMessage("生成到达单成功",
                                    MessageType.succeed, 2000);
                            listItem.refresh();
                            mainFrame.validate();
                        }

                    }
                });

            }
        });

        flushButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO 自动生成的方法存根
                mainFrame.load(new Runnable() {

                    @Override
                    public void run() {
                        // TODO 自动生成的方法存根
                        refresh();
                    }
                });

            }
        });

        GridBagLayout gb = new GridBagLayout();
        GridBagConstraints gcons = new GridBagConstraints();
        setLayout(gb);
        JPanel jp = new JPanel();
        jp.setOpaque(false);
        // gcons.anchor = GridBagConstraints.EAST;
        gcons.insets = new Insets(10, 700, 0, 00);
        SwingConsole.addComponent(gb, gcons, this, flushButton, 0, 0, 1, 1, 0,
                0);
        gcons.insets = new Insets(0, 0, 0, 0);
        SwingConsole
                .addComponent(gb, gcons, this, titlePanel, 0, 1, 1, 1, 1, 0);
        SwingConsole.addComponent(gb, gcons, this, listItem, 0, 2, 1, 1, 1, 0);
        SwingConsole.addComponent(gb, gcons, this, jp, 0, 3, 1, 1, 1, 1);
        SwingConsole.addComponent(gb, gcons, this, comment, 0, 4, 1, 1, 0, 0);
        SwingConsole
                .addComponent(gb, gcons, this, buttonArea, 0, 5, 1, 1, 1, 0);

    }

    @Override
    public void refresh() {
        // TODO 自动生成的方法存根
        listItem.refresh();
        mainFrame.validate();
    }
}
