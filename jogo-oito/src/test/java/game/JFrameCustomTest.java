package game;

import model.JFrameCustom;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.assertj.core.api.Assertions.assertThat;

class JFrameCustomTest {

    @Test
    public void testCenterFrameInTheScreen() {
        JFrameCustom frame = new JFrameCustom("Test Frame");
        frame.setSize(800, 600);

        frame.centerFrameInTheScreen(frame);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Integer expectedXAxis = (screenSize.width - frame.getWidth()) / 2;
        Integer expectedYAxis = (screenSize.height - frame.getHeight()) / 2;

        assertThat(expectedXAxis).isEqualTo(frame.getX());
        assertThat(expectedXAxis).isEqualTo(283);

        assertThat(expectedYAxis).isEqualTo( frame.getY());
        assertThat(expectedXAxis).isEqualTo(283);
    }

}
