package util;

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
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        width = frame.getSize().width;
        height = frame.getSize().height;
        xAxis = (dim.width - width) / 2;
        yAxis = (dim.height - height) / 2;
        frame.setLocation(xAxis, yAxis);
    }
}
