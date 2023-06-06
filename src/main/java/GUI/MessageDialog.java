package GUI;

import javax.swing.*;
import java.awt.*;

public class MessageDialog {
    public static void showErrorDialog(Component parentComponent, String message) {
        UIManager.put("OptionPane.background", new Color(0, 53, 128));
        UIManager.put("Panel.background", new Color(0, 53, 128));
        UIManager.put("OptionPane.messageForeground", Color.WHITE);
        UIManager.put("OptionPane.messageFont", CustomFont.getPlainCustomFont());
        UIManager.put("Button.background", new Color(0, 108, 228, 255));
        UIManager.put("Button.foreground", Color.WHITE);
        UIManager.put("Button.font", CustomFont.getPlainCustomFont().deriveFont(Font.BOLD, 14));

        JOptionPane.showMessageDialog(parentComponent, message, "Error", JOptionPane.ERROR_MESSAGE);

        UIManager.put("OptionPane.background", null);
        UIManager.put("Panel.background", null);
        UIManager.put("OptionPane.messageForeground", null);
    }
    public static void showSuccessDialog(Component parentComponent, String message) {
        UIManager.put("OptionPane.background", new Color(0, 53, 128));
        UIManager.put("Panel.background", new Color(0, 53, 128));
        UIManager.put("OptionPane.messageForeground", Color.WHITE);
        UIManager.put("OptionPane.messageFont", CustomFont.getBoldCustomFont());
        UIManager.put("Button.background", new Color(0, 108, 228, 255));
        UIManager.put("Button.foreground", Color.WHITE);
        UIManager.put("Button.font", CustomFont.getPlainCustomFont().deriveFont(Font.BOLD, 14));

        String imagePath = "src/main/resources/accept-icon.png";
        ImageIcon originalIcon = new ImageIcon(imagePath);

        int desiredWidth = 32;
        int desiredHeight = 32;

        Image scaledImage = originalIcon.getImage().getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JOptionPane.showMessageDialog(parentComponent, message, "Success!", JOptionPane.PLAIN_MESSAGE, scaledIcon);


        UIManager.put("OptionPane.background", null);
        UIManager.put("Panel.background", null);
        UIManager.put("OptionPane.messageForeground", null);
    }
}
