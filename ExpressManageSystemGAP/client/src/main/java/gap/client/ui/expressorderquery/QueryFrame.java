package gap.client.ui.expressorderquery;

import gap.client.datacontroller.NetModule;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class QueryFrame extends JFrame {
    ResultShowPanel result;
    Image icon;
    String iconPath = "images/pikachu.png";

    public QueryFrame() {
        setUndecorated(true);
        try {
            icon = ImageIO.read(new File(iconPath));
            setIconImage(icon);
        } catch (IOException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
        Dimension dimen = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((dimen.width - 1000) / 2, (dimen.height - 550) / 2, 1000, 550);
        setBackground(new Color(0, 0, 0, 0));
        FrameListener listener = new FrameListener();
        addMouseListener(listener);
        addMouseMotionListener(listener);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        result = new ResultShowPanel();
        setContentPane(result);

    }

    public static void main(String[] args) {
        QueryFrame queriFrame = new QueryFrame();
        NetModule.initial(queriFrame);
        NetModule.connect();
        queriFrame.setVisible(true);
    }

    class FrameListener implements MouseListener, MouseMotionListener {
        int beforeX;
        int beforeY;
        int beforeFrameX;
        int beforeFrameY;
        boolean isPressed;

        @Override
        public void mouseDragged(MouseEvent e) {
            // TODO 自动生成的方法存根
            if (isPressed) {
                int deltaX = e.getXOnScreen() - beforeX;
                int deltaY = e.getYOnScreen() - beforeY;
                setLocation(beforeFrameX + deltaX, beforeFrameY + deltaY);
            }
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            // TODO 自动生成的方法存根

        }

        @Override
        public void mouseClicked(MouseEvent e) {
            // TODO 自动生成的方法存根

        }

        @Override
        public void mousePressed(MouseEvent e) {
            // TODO 自动生成的方法存根
            if (e.getY() <= 40) {
                isPressed = true;
                beforeX = e.getXOnScreen();
                beforeY = e.getYOnScreen();
                beforeFrameX = getX();
                beforeFrameY = getY();
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            // TODO 自动生成的方法存根
            isPressed = false;
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
