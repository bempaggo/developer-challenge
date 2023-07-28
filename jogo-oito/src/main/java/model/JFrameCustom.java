package model;

import javax.swing.*;
import java.awt.*;

public class JFrameCustom extends JFrame {

    private Integer width;
    private Integer height;
    private Integer xAxis;
    private Integer yAxis;

    public JFrameCustom(String title) {
        super(title);
    }

    public void centerFrameInTheScreen(JFrame frame) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        this.width = frame.getSize().width;
        this.height = frame.getSize().height;
        this.xAxis = (dimension.width - width) / 2;
        this.yAxis = (dimension.height - height) / 2;
        frame.setLocation(xAxis, yAxis);
    }

}
