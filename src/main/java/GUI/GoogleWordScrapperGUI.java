package GUI;

import Scrapper.Scrapper;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GoogleWordScrapperGUI extends JFrame implements ActionListener {
    private JLabel queryLabel, pageLabel, directoryLabel;
    private JTextField queryField, pageField, directoryField;
    private JButton saveButton;

    public GoogleWordScrapperGUI() {
        setTitle("Google Word Scraper");
        setSize(500, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        queryLabel = new JLabel("Search Query:");
        pageLabel = new JLabel("Number of Pages:");
        directoryLabel = new JLabel("Save Directory:");

        queryField = new JTextField(20);
        pageField = new JTextField(5);
        directoryField = new JTextField(20);


        saveButton = new JButton("Save to Excel");
        saveButton.addActionListener(this);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JPanel queryPanel = new JPanel();
        queryPanel.add(queryLabel);
        queryPanel.add(queryField);

        JPanel pagePanel = new JPanel();
        pagePanel.add(pageLabel);
        pagePanel.add(pageField);

        JPanel directoryPanel = new JPanel();
        directoryPanel.add(directoryLabel);
        directoryPanel.add(directoryField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(saveButton);

        panel.add(queryPanel);
        panel.add(pagePanel);
        panel.add(buttonPanel);

        add(panel);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveButton) {
            String query = queryField.getText();
            int page = Integer.parseInt(pageField.getText());
            try {
            new Scrapper(query, page);
                JOptionPane.showMessageDialog(this, "Excel file saved successfully!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "An error occurred: " + ex.getMessage());
            }
        }
    }
}
