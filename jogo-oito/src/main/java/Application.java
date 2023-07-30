import builder.JogoDosOitoBuilder;
import view.JogoDosOito;

import javax.swing.*;

public class Application {

    public static void main(String[] args) {
        new JogoDosOitoBuilder()
                .createButtons()
                .configureMenu()
                .configureInterface()
                .build();
        try {
            UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JogoDosOito.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> new JogoDosOito().setVisible(true));
    }

}
