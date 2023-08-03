package model;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public abstract class JFrameAbstractCustom extends JFrame implements KeyListener {

    public JFrameAbstractCustom(String title) {
        super(title);
    }

    public abstract void centerFrameInTheScreen(JFrame frame);

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
