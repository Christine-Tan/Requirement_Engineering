package gap.client.ui.expressorderquery;

import gap.client.blcontroller.ExpressorderController;
import gap.client.ui.UITools.RenderSetter;
import gap.client.ui.expressorderquery.components.ArcAndString;
import gap.client.ui.expressorderquery.components.Colors;
import gap.client.ui.expressorderquery.components.IdInputCompo;
import gap.client.ui.expressorderquery.components.SmallCircle;
import gap.client.ui.gapcomponents.CloseIcon;
import gap.client.ui.gapcomponents.GAPBorder;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * 查询结果的显示
 * @author YangYanfei
 *
 */
public class ResultShowPanel extends JPanel {
    int defautinterval = 90, defautstart = 150, defautY = 370, messageY = 220;
    double currentEnd = defautstart;

    private String imagePath = "images\\expressorderquery\\back.png";
    private BufferedImage backImage;

    static boolean show;
    static String message = "";

    ArcAndString[] arcAndStrings;
    SmallCircle[] circles;
    IdInputCompo id_Input;

    CloseIcon close;

    int length;
    int unit_time = 500;
    int unit_rate = 50;
    Thread changeThread;

    public ResultShowPanel() {
        setLayout(null);
        // setOpaque(false);
        setFocusable(true);
        setBorder(new GAPBorder());

        try {
            backImage = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }

        id_Input = new IdInputCompo(this, "请输入订单号");
        id_Input.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                // TODO 自动生成的方法存根
                if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                    String order_id = id_Input.getOrderId();
                    if (order_id == null)
                        return;
                    String[] states = ExpressorderController
                            .getStates(order_id);
                    if (states == null || states.length == 0) {
                        showMessage("订单号不存在，请仔细核对");
                        repaint();
                        return;
                    }
                    setStates(states);
                    change();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // TODO 自动生成的方法存根

            }

            @Override
            public void keyPressed(KeyEvent e) {
                // TODO 自动生成的方法存根

            }
        });

        close = new CloseIcon();
        close.addMouseListener(new MouseListener() {

            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO 自动生成的方法存根

            }

            @Override
            public void mousePressed(MouseEvent e) {
                // TODO 自动生成的方法存根

            }

            @Override
            public void mouseExited(MouseEvent e) {
                // TODO 自动生成的方法存根

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // TODO 自动生成的方法存根

            }

            @Override
            public void mouseClicked(MouseEvent e) {
                // TODO 自动生成的方法存根
                System.exit(0);
            }
        });

        add(close);
        add(id_Input);
        id_Input.submit_bu.addMouseListener(new QueryListener());

        id_Input.setBounds(240, 120, 520, 70);
        close.setBounds(960, 10, 25, 25);
    }

    public ResultShowPanel(String[] states) {
        this();
        setStates(states);

    }

    public static void showMessage(String str) {
        message = str;
        show = false;
    }

    public void setStates(String[] states) {
        length = states.length;
        arcAndStrings = new ArcAndString[length];
        circles = new SmallCircle[length];
        for (int i = 0; i < length; i++) {
            arcAndStrings[i] = new ArcAndString(states[i], defautstart + i
                    * defautinterval, defautY, (i + 1) % 2, this);
            circles[i] = new SmallCircle(defautstart + i * defautinterval,
                    defautY, 7, 5, this);
        }
    }

    public void change() {
        if (changeThread != null && changeThread.isAlive())
            changeThread.interrupt();
        changeThread = new Thread(new ChangeRunnable());
        show = true;
        changeThread.start();
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2d = RenderSetter.OpenRender(g);

        g2d.setColor(Color.white);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // g2d.drawImage(backImage, 0, 0, getWidth(), getHeight(), null);

        g2d.setColor(Colors.blue);
        g2d.setStroke(new BasicStroke(5));

        // g2d.drawLine(defautstart, defautY, currentEnd, defautY);
        if (show) {
            g2d.draw(new Line2D.Double(defautstart - defautinterval / 3,
                    defautY, currentEnd, defautY));
            for (ArcAndString as : arcAndStrings)
                as.draw(g2d);

            for (SmallCircle sc : circles)
                sc.draw(g2d);
        } else {
            Font font = new Font("黑体", Font.PLAIN, 20);
            FontMetrics fontM = getFontMetrics(font);
            g2d.setFont(font);
            g2d.setColor(Colors.red);
            int fontWidth = fontM.stringWidth(message);
            g2d.drawString(message, (900 - fontWidth) / 2, messageY);
        }
    }

    class ChangeRunnable implements Runnable {

        @Override
        public void run() {
            // TODO 自动生成的方法存根
            double deltaX = defautinterval / (double) unit_rate;
            int deltaTime = unit_time / unit_rate;
            currentEnd = defautstart;
            for (int i = 0; i < length; i++) {
                circles[i].change(300, 100);
                arcAndStrings[i].change();
                for (int j = 0; j < unit_rate
                        && (i != (length - 1) || j < unit_rate / 3); j++) {
                    currentEnd += deltaX;
                    repaint();
                    try {
                        Thread.sleep(deltaTime);
                    } catch (InterruptedException e) {
                        // TODO 自动生成的 catch 块
                        e.printStackTrace();
                        return;
                    }
                }
            }
        }
    }

    class QueryListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            // TODO 自动生成的方法存根
            String order_id = id_Input.getOrderId();
            if (order_id == null)
                return;
            String[] states = ExpressorderController.getStates(order_id);
            if (states == null || states.length == 0) {
                showMessage("订单号不存在，请仔细核对");
                repaint();
                return;
            }
            setStates(states);
            change();
        }

        @Override
        public void mousePressed(MouseEvent e) {
            // TODO 自动生成的方法存根

        }

        @Override
        public void mouseReleased(MouseEvent e) {
            // TODO 自动生成的方法存根

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            // TODO 自动生成的方法存根

        }

        @Override
        public void mouseExited(MouseEvent e) {
            // TODO 自动生成的方法存根

        }

    }

}
