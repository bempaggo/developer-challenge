package util;

import javax.swing.JOptionPane;

public final class MessagePopUp {

    private MessagePopUp() {
    }

    public static void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }
    
}