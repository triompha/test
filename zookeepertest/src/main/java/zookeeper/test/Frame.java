package zookeeper.test;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Frame extends JFrame implements MouseListener, Runnable {
    int width = Toolkit.getDefaultToolkit().getScreenSize().width;
    int height = Toolkit.getDefaultToolkit().getScreenSize().height;

    public Frame() {
        setTitle("五子棋");
        setSize(500, 500);
        setLocation((width - 500) / 2, (height - 500) / 2);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        addMouseListener(this);
    }

    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    public void run() {
        // TODO Auto-generated method stub
    }
}