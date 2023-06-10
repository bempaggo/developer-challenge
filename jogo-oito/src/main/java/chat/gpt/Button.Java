package chat.gpt;
import javax.swing.*;

public interface Button extends JButton{

    Button(){
        this.setFont(new Font("Times New Roman", Font.BOLD, 40))
        this.setVisible(true)
    }


}